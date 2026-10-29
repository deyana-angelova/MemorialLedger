package com.example.MemorialLedger.Tombstone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/tombstones")
public class TombstoneController {
    private final TombstoneRepository repo;

    public TombstoneController(TombstoneRepository repo) {
        this.repo = repo;
    }
    @GetMapping
    public List<Tombstone> getAll(){
        return repo.findAll();
    }
}
