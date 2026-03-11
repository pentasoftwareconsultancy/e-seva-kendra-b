package com.example.E_seva_kendra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.E_seva_kendra.model.Payment;
import com.example.E_seva_kendra.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Confirm Payment + Documents Upload
    @PostMapping("/confirm")
    public String confirmPayment(

            @RequestParam Long userId,
            @RequestParam String name,
            @RequestParam String mobile,
            @RequestParam String serviceName,
            @RequestParam String extraData,
            @RequestParam Double amount,

            @RequestParam("screenshot") MultipartFile screenshot,

            @RequestParam("documents") MultipartFile[] documents
    ) {

        return paymentService.confirmPayment(
                userId,
                name,
                mobile,
                serviceName,
                extraData,
                amount,
                screenshot,
                documents
        );
    }

    // Admin Panel - Get All Payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Today Earnings
    @GetMapping("/today-earnings")
    public Double getTodayEarnings() {
        return paymentService.getTodayEarnings();
    }
}