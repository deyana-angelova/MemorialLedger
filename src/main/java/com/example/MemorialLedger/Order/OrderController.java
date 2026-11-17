package com.example.MemorialLedger.Order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repo;
    public OrderController(OrderRepository repo){
        this.repo = repo;
    }
    @GetMapping
    public List<Order> getAll(){
        return repo.findAll();
    }
}
