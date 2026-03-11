package com.example.E_seva_kendra.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
 
import com.example.E_seva_kendra.dto.OrderRequest;

import com.example.E_seva_kendra.dto.StatusRequest;

import com.example.E_seva_kendra.model.Order;

import com.example.E_seva_kendra.service.OrderService;
 
@RestController

@RequestMapping("/api/orders")

@CrossOrigin("*")

public class OrderController {
 
    @Autowired

    private OrderService orderService;
 
    // Create Order

    @PostMapping

    public Order createOrder(@RequestBody OrderRequest request){

        return orderService.createOrder(request);

    }
 
    // Get All Orders (Admin Panel)

    @GetMapping

    public List<Order> getAllOrders(){

        return orderService.getAllOrders();

    }
 
    @PutMapping("/{id}/status")

    public String updateStatus(@PathVariable Long id, @RequestBody StatusRequest request){

        return orderService.updateOrderStatus(id, request.getStatus());

    }

    // Get Orders by User ID
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

}
 