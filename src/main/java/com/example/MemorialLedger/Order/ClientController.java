package com.example.MemorialLedger.Order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientRepository repo;

    public ClientController(ClientRepository repo){
        this.repo = repo;
    }
    @GetMapping
    public List<Client> getAll(){
        return repo.findAll();
    }
}
