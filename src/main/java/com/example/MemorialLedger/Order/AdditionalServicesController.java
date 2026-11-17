package com.example.MemorialLedger.Order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesController {
    private final AdditionalServicesRepository repo;

    public AdditionalServicesController (AdditionalServicesRepository repo) {
        this.repo = repo;
    }
    @GetMapping
    public List<AdditionalService> getAll(){
        return repo.findAll();
    }
}
