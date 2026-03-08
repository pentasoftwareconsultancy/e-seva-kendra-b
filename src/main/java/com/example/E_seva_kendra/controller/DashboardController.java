package com.example.E_seva_kendra.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.service.service.OrderService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/stats")
    public Map<String, Long> getStats(){

        Map<String, Long> stats = new HashMap<>();

        stats.put("totalOrders", orderService.getTotalOrders());
        stats.put("pendingOrders", orderService.getPendingOrders());
        stats.put("inProgressOrders", orderService.getInProgressOrders());
        stats.put("completedOrders", orderService.getCompletedOrders());

        return stats;
    }
}