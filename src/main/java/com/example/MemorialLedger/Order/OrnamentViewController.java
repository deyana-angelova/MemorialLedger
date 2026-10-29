package com.example.MemorialLedger.Order;

import com.example.MemorialLedger.SvgStorage;
import com.example.MemorialLedger.Tombstone.Tombstone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/ornaments")
public class OrnamentViewController {
    private final OrnamentRepository repo;
    private final SvgStorage svgStorage;

    public OrnamentViewController(OrnamentRepository repo, SvgStorage svgStorage)
    {
        this.repo = repo;
        this.svgStorage = svgStorage;
    }

    private static final Path UPLOAD_DIR = Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploadsOrnaments");

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ornaments", repo.findAll());
        return "ornament/ornaments";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("ornament", new Ornament());
        return "ornament/form";
    }

    @PostMapping
    public String create(@ModelAttribute Ornament ornament, @RequestParam("image") MultipartFile image,
                         @RequestParam(value = "svg", required = false) MultipartFile svg) throws IOException {
        if (!image.isEmpty()) {
            String fileName = UUID.randomUUID() + ".jpg";
            Path dir = Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploadsOrnaments");
            //if folder doesnt exist it creates it
            Files.createDirectories(dir);
            Files.copy(image.getInputStream(), dir.resolve(fileName));
            ornament.setOrnamentImageFileName(fileName);
        }
        if (svg != null && !svg.isEmpty()) {
            ornament.setSvgFileName(svgStorage.save(svg));
        }
        repo.save(ornament);
        return "redirect:/ornaments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID Id, Model model) {
        model.addAttribute("ornament", repo.findById(Id).orElseThrow());
        return "ornament/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable UUID id,
                         @ModelAttribute Ornament form,
                         @RequestParam("image") MultipartFile image,
                         @RequestParam(value = "svg", required = false) MultipartFile svg) throws IOException {
        Ornament existing = repo.findById(id).orElseThrow();

        existing.setCatalogueNum(form.getCatalogueNum());
        existing.setName(form.getName());
        existing.setPrice(form.getPrice());

        if (!image.isEmpty()) {
            Files.createDirectories(UPLOAD_DIR);
            String fileName = UUID.randomUUID() + ".jpg";
            Files.copy(image.getInputStream(), UPLOAD_DIR.resolve(fileName));
            if (existing.getOrnamentImageFileName() != null) {
                Files.deleteIfExists(UPLOAD_DIR.resolve(existing.getOrnamentImageFileName()));
            }
        }
        if (svg != null && !svg.isEmpty()) {
            svgStorage.delete(existing.getSvgFileName());
            existing.setSvgFileName(svgStorage.save(svg));
        }
        repo.save(existing);
        return "redirect:/ornaments";
    }
}
