package com.example.MemorialLedger.Order;

import com.example.MemorialLedger.SvgStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderViewController {
    private final OrderRepository repo;
    private final ClientRepository clientRepo;
    private final AdditionalServicesRepository additionalServicesRepo;
    private final SvgStorage svgStorage;

    public OrderViewController(OrderRepository repo, ClientRepository clientRepo,
                               AdditionalServicesRepository additionalServicesRepo,
                               SvgStorage svgStorage) {
        this.repo = repo;
        this.clientRepo = clientRepo;
        this.additionalServicesRepo = additionalServicesRepo;
        this.svgStorage = svgStorage;
    }

    // get all
    @GetMapping
    public String index(Model model) {
        model.addAttribute("orders", repo.findAll());
        return "order/orders";
    }

    // empty form
    @GetMapping("/new")
    public String newForm(Model model) throws IOException{
        model.addAttribute("order", new Order());
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("additionalServices", additionalServicesRepo.findAll());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("files", svgStorage.list());
        return "order/form";
    }

    // add new order to db
    @PostMapping
    public String create(
            @ModelAttribute Order order,
            @RequestParam String clientName,
            @RequestParam String clientPhone,
            @RequestParam(required = false) List<String> additionalServiceIds) {

        Client client = new Client();
        client.Name = clientName;            // unchanged: Client still has public fields
        client.PhoneNumber = clientPhone;
        clientRepo.save(client);

        order.setClient(client);
        order.setAdditionalServices(
                additionalServicesRepo.findAllById(toUuids(additionalServiceIds)));

        repo.save(order);
        return "redirect:/orders";
    }

    // edit form
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") UUID id, Model model) throws IOException {
        model.addAttribute("order", repo.findById(id).orElseThrow());
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("additionalServices", additionalServicesRepo.findAll());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("files", svgStorage.list());
        return "order/form";
    }

    // update existing order
    @PostMapping("/{id}")
    public String update(
            @PathVariable UUID id,
            @ModelAttribute Order form,
            @RequestParam String clientId,
            @RequestParam String clientName,
            @RequestParam String clientPhone,
            @RequestParam(required = false) List<String> additionalServiceIds) {

        Order existing = repo.findById(id).orElseThrow();

        Client client = clientRepo.findById(UUID.fromString(clientId)).orElseThrow();
        client.Name = clientName;
        client.PhoneNumber = clientPhone;
        clientRepo.save(client);

        existing.setClient(client);
        existing.setDeceased(form.getDeceased());
        existing.setTombstoneId(form.getTombstoneId());
        existing.setCemetery(form.getCemetery());
        existing.setStatus(form.getStatus());
        existing.setCeramicPhotoId(form.getCeramicPhotoId());
        existing.setTotalPrice(form.getTotalPrice());
        existing.setDateOfOrder(form.getDateOfOrder());
        existing.setDeadline(form.getDeadline());
        existing.setAdditionalServices(
                additionalServicesRepo.findAllById(toUuids(additionalServiceIds)));

        repo.save(existing);
        return "redirect:/orders";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") UUID id) {
        repo.deleteById(id);
        return "redirect:/orders";
    }

    // helper: removes the duplicated stream code
    private List<UUID> toUuids(List<String> ids) {
        if (ids == null) return List.of();
        return ids.stream()
                .filter(s -> s != null && !s.isBlank())
                .map(UUID::fromString)
                .toList();
    }
    //preview readonly order
    @GetMapping("/{id}/preview")
    public String preview(@PathVariable("id") UUID id,Model model){
        model.addAttribute("order", repo.findById(id).orElseThrow());
        return "order/preview";
    }
}