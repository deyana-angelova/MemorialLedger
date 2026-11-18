package com.example.MemorialLedger.Order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderViewController {
    private final OrderRepository repo;
    private final ClientRepository clientRepo;
    private final AdditionalServicesRepository additionalServicesRepo;

    public OrderViewController(OrderRepository repo, ClientRepository clientRepo,
                               AdditionalServicesRepository additionalServicesRepo){
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.additionalServicesRepo = additionalServicesRepo;
    }
    //get all
    @GetMapping
    public String index(Model model) {
        model.addAttribute("orders", repo.findAll());
        return "order/orders";
    }

    //get data from form
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("additionalServices", additionalServicesRepo.findAll());
        return "order/form";
    }
    //add new order to db
    @PostMapping
    public String create(
            @ModelAttribute Order order,
            @RequestParam String clientName,
            @RequestParam String clientPhone) {

        Client client = new Client();
        client.Name = clientName;
        client.PhoneNumber = clientPhone;

        clientRepo.save(client);

        order.client = client;
        repo.save(order);

        return "redirect:/orders";
    }
    //get order by id
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("order", repo.findById(id).orElseThrow());
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("additionalServices", additionalServicesRepo.findAll());
        return "order/form";
    }
    //update existing order
    @PostMapping("/{id}")
    public String update(
            @PathVariable UUID id,
            @ModelAttribute Order form,
            @RequestParam String clientId,
            @RequestParam String clientName,
            @RequestParam String clientPhone) {

        Order existing = repo.findById(id).orElseThrow();

        Client client = clientRepo.findById(UUID.fromString(clientId))
                .orElseThrow();

        client.Name = clientName;
        client.PhoneNumber = clientPhone;
        clientRepo.save(client);

        existing.client = client;
        existing.Deceased = form.Deceased;
        existing.TombstoneId = form.TombstoneId;
        existing.Cemetery = form.Cemetery;
        existing.status = form.status;
        existing.CeramicPhotoId = form.CeramicPhotoId;
        existing.TotalPrice = form.TotalPrice;
        existing.DateOfOrder = form.DateOfOrder;
        existing.Deadline = form.Deadline;

        repo.save(existing);

        return "redirect:/orders";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id){
        repo.deleteById(id);
        return "redirect:/orders";
    }
}
