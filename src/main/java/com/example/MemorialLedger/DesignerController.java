package com.example.MemorialLedger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/designer")
public class DesignerController {
    private final SvgStorage svgStorage;

    public DesignerController(SvgStorage svgStorage) {
        this.svgStorage = svgStorage;
    }

    @GetMapping
    public String index(Model model) throws IOException {
        model.addAttribute("files", svgStorage.list());
        return "designer";
    }
}
