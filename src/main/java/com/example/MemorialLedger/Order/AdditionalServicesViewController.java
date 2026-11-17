package com.example.MemorialLedger.Order;

import com.example.MemorialLedger.SvgStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/additionalservices")
public class AdditionalServicesViewController {
    private final AdditionalServicesRepository repo;
    public AdditionalServicesViewController(AdditionalServicesRepository repo){
        this.repo = repo;
    }
    //get all
    @GetMapping
    public String index(Model model) {
        model.addAttribute("additionalServices", repo.findAll());
        return "additionalservice/additionalservices";
    }
    //get data from form
    @GetMapping("/new")
    public String newForm(Model model){
        model.addAttribute("additionalservice", new AdditionalService());
        return "additionalservice/form";
    }
    //add new add. service to repo
    @PostMapping
    public String create(@ModelAttribute AdditionalService additionalService) throws IOException{
        repo.save(additionalService);
        return "redirect:/additionalservices";
    }
}
