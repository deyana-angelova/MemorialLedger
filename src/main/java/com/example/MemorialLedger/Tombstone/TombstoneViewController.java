package com.example.MemorialLedger.Tombstone;

import com.example.MemorialLedger.SvgStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/tombstones")
public class TombstoneViewController {
    private final TombstoneRepository repo;
    private final SvgStorage svgStorage;

    public TombstoneViewController(TombstoneRepository repo, SvgStorage svgStorage)
    {
        this.repo = repo;
        this.svgStorage = svgStorage;
    }
    //hardcoded sizing
    @ModelAttribute("sizes")
    public List<String> sizes() {
        return List.of("50X70", "60X80", "70X90", "80X100", "80X120");
    }
    private static final Path UPLOAD_DIR = Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploads");
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tombstones", repo.findAll());
        return "tombstones/index";
    }
    // GET /tombstones/new  -> show the empty form
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("tombstone", new Tombstone());
        return "tombstones/form";
    }

    // POST /tombstones  -> save, then go back to the list
    @PostMapping
    public String create(@ModelAttribute Tombstone tombstone, @RequestParam("image") MultipartFile image,
                         @RequestParam(value = "svg", required = false) MultipartFile svg) throws IOException {
        if(!image.isEmpty()){
            String fileName= UUID.randomUUID() + ".jpg";
            Path dir = Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploads");
            Files.createDirectories(dir);
            Files.copy(image.getInputStream(), dir.resolve(fileName));
            tombstone.setImageFileName(fileName);
        }
        tombstone.getPrices().values().removeIf(Objects::isNull);
        if (svg != null && !svg.isEmpty()) {
            tombstone.setSvgFileName(svgStorage.save(svg));
        }
        repo.save(tombstone);
        return "redirect:/tombstones";
    }
    // GET /tombstones/{id}/edit -> same form, filled with existing data
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        model.addAttribute("tombstone", repo.findById(id).orElseThrow());
        return "tombstones/form";
    }
    @PostMapping("/{id}")
    public String update(@PathVariable UUID id,
                         @ModelAttribute Tombstone form,
                         @RequestParam("image") MultipartFile image,
                         @RequestParam(value = "svg", required = false) MultipartFile svg) throws IOException{
        Tombstone existing = repo.findById(id).orElseThrow();

        existing.setCatalogueNum(form.getCatalogueNum());
        existing.setName(form.getName());

        form.getPrices().values().removeIf(Objects::isNull);
        existing.getPrices().clear();
        existing.getPrices().putAll(form.getPrices());

        if(!image.isEmpty()){
            Files.createDirectories(UPLOAD_DIR);
            String fileName = UUID.randomUUID() + ".jpg";
            Files.copy(image.getInputStream(), UPLOAD_DIR.resolve(fileName));
            if (existing.getImageFileName() != null) {
                Files.deleteIfExists(UPLOAD_DIR.resolve(existing.getImageFileName()));
            }
            existing.setImageFileName(fileName);
        }
        if (svg != null && !svg.isEmpty()) {
            svgStorage.delete(existing.getSvgFileName());
            existing.setSvgFileName(svgStorage.save(svg));
        }
        repo.save(existing);
        return "redirect:/tombstones";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id) throws IOException
    {
        Tombstone t = repo.findById(id).orElseThrow();
        if(t.getImageFileName() != null){
            Files.deleteIfExists(UPLOAD_DIR.resolve(t.getImageFileName()));
        }
        svgStorage.delete(t.getSvgFileName());
        repo.deleteById(id);
        return "redirect:/tombstones";
    }
}
