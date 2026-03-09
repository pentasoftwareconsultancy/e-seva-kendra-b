package com.example.E_seva_kendra.service;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.E_seva_kendra.model.Order;
import com.example.E_seva_kendra.model.Payment;
import com.example.E_seva_kendra.repository.OrderRepository;
import com.example.E_seva_kendra.repository.PaymentRepository;
import com.example.E_seva_kendra.service.NotificationService;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private NotificationService notificationService;

    public String confirmPayment(String name,
                                 String mobile,
                                 String serviceName,
                                 String extraData,
                                 Double amount,
                                 MultipartFile screenshot) {

        try {

            // ===== Upload Folder =====
            String uploadDir = "uploads/";
            File folder = new File(uploadDir);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            // ===== Save Screenshot =====
            String fileName = screenshot.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);

            Files.write(filePath, screenshot.getBytes());

            // ===== SAVE ORDER =====
            Order order = new Order();
            order.setName(name);
            order.setMobile(mobile);
            order.setServiceName(serviceName);
            order.setExtraData(extraData);
            order.setStatus("Order Placed");

            Order savedOrder = orderRepository.save(order);

            // ===== SAVE PAYMENT =====
            Payment payment = new Payment();
            payment.setOrderId(savedOrder.getId());
            payment.setName(name);
            payment.setServiceName(serviceName);
            payment.setAmount(amount);
            payment.setScreenshot(fileName);
            payment.setPaymentStatus("Success");

            paymentRepository.save(payment);

            // ===== CREATE NOTIFICATION =====
            notificationService.createNotification(
                    savedOrder.getId(),
                    savedOrder.getId(),
                    "Payment Successful",
                    "Your payment for " + serviceName + " was successful. Your order has been placed.",
                    "PAYMENT_SUCCESS"
            );

            return "Order Placed Successfully";

        } catch (IOException e) {
            return "Payment Failed";
        }
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Double getTodayEarnings() {

        Double total = paymentRepository.getTodayEarnings();

        if (total == null) {
            return 0.0;
        }

        return total;
    }
}