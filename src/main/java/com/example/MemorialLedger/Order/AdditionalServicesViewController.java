package com.example.MemorialLedger.Order;

import com.example.MemorialLedger.SvgStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

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
    //get add. service by id
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("additionalservice", repo.findById(id).orElseThrow());
        return "additionalservice/form";
    }
    //update existing add. service
    @PostMapping("/{id}")
    public String update(@PathVariable UUID id,
                         @ModelAttribute AdditionalService form){
        AdditionalService existing = repo.findById(id).orElseThrow();
        existing.setName(form.getName());
        existing.setPrice(form.getPrice());
        existing.setDescription(form.getDescription());

        repo.save(existing);
        return "redirect:/additionalservices";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id){
        repo.deleteById(id);
        return "redirect:/additionalservices";
    }
}
