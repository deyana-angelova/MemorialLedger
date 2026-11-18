package com.example.MemorialLedger.Order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clients")
public class ClientViewController {
    private final ClientRepository repo;

    public ClientViewController(ClientRepository clientRepo){
        this.repo = clientRepo;
    }
    //get all clients
    @GetMapping
    public String index(Model model){
        model.addAttribute("clients", repo.findAll());
        return "client/clients";
    }
}
