package com.example.MemorialLedger;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/svg-library")
public class SvgLibraryController {
    private final SvgStorage svgStorage;

    public SvgLibraryController(SvgStorage svgStorage) {
        this.svgStorage = svgStorage;
    }
    @GetMapping
    public String index(Model model) throws IOException {
        model.addAttribute("files", svgStorage.list());
        return "svg-library";
    }
    //upload the svg files
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> upload(@RequestParam("files") List<MultipartFile> files) {
        try {
            for (MultipartFile f : files) {
                svgStorage.save(f);
            }
            return ResponseEntity.ok("ok");
        } catch (IllegalArgumentException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/delete")
    public String delete(@RequestParam String file) throws IOException {
        svgStorage.delete(file);
        return "redirect:/svg-library";
    }
}
