package com.example.MemorialLedger.Dashboard;
import com.example.MemorialLedger.Order.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class DashboardController {
    private final OrderRepository orders;

    public DashboardController(OrderRepository orders) {
        this.orders = orders;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("orders", orders.findAll());
        model.addAttribute("active", "dashboard");   // your layout checks ${active == 'dashboard'}
        return "dashboard/dashboard";
    }
}
