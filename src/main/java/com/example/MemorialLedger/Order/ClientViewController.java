package com.example.MemorialLedger.Order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clients")
public class ClientViewController {
    private final ClientRepository repo;

    public ClientViewController(ClientRepository clientRepo){
        this.repo = clientRepo;
    }
    //get all clients
    @GetMapping
    public String index(@RequestParam(required = false) String name,
            Model model){
        List<Client> result = repo.findAll().stream()
                        .filter(c -> name == null || name.isBlank()
                        || (c.getName() != null
                        && c.getName().toLowerCase().contains(name.trim().toLowerCase())))
                                .collect(Collectors.toList());
        model.addAttribute("clients", result);
        model.addAttribute("fName", name);
        return "client/clients";
    }
}
