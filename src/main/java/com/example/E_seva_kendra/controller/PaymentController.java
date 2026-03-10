package com.example.E_seva_kendra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.E_seva_kendra.model.Payment;
import com.example.E_seva_kendra.repository.PaymentRepository;
import com.example.E_seva_kendra.service.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private PaymentRepository paymentRepository;


    // Save Payment
    @PostMapping("/confirm")
    public String confirmPayment(
            @RequestParam String name,
            @RequestParam String mobile,
            @RequestParam String serviceName,
            @RequestParam String extraData,
            @RequestParam Double amount,
            @RequestParam("screenshot") MultipartFile screenshot){

        return paymentService.confirmPayment(name, mobile, serviceName, extraData, amount, screenshot);
    }

    // Get All Payments (Admin Panel)
    @GetMapping
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }
    @GetMapping("/today-earnings")
    public Double getTodayEarnings(){
        return paymentService.getTodayEarnings();
    }
    
    @GetMapping("/user/{userId}")
    public List<Payment> getUserPayments(@PathVariable Long userId) {

        return paymentRepository.findByUserId(userId);
    }

}