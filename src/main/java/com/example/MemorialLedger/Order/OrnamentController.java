package com.example.MemorialLedger.Order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ornaments")
public class OrnamentController {
    private final OrnamentRepository repo;
    public OrnamentController(OrnamentRepository repo){
        this.repo = repo;
    }
    @GetMapping
    public List<Ornament> getAll(){
        return repo.findAll();
    }
}
