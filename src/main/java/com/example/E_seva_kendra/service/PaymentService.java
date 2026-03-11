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

import com.example.E_seva_kendra.model.Document;
import com.example.E_seva_kendra.model.Order;
import com.example.E_seva_kendra.model.Payment;
import com.example.E_seva_kendra.repository.DocumentRepository;
import com.example.E_seva_kendra.repository.OrderRepository;
import com.example.E_seva_kendra.repository.PaymentRepository;
import com.example.E_seva_kendra.service.NotificationService;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DocumentRepository documentRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private NotificationService notificationService;

    public String confirmPayment(Long userId,
            String name,
            String mobile,
            String serviceName,
            String extraData,
            Double amount,
            MultipartFile screenshot,
            MultipartFile[] documents) {

try {

String uploadDir = "uploads/";
File folder = new File(uploadDir);

if(!folder.exists()){
folder.mkdirs();
}

// SAVE SCREENSHOT

String screenshotName = screenshot.getOriginalFilename();
Path screenshotPath = Paths.get(uploadDir + screenshotName);
Files.write(screenshotPath, screenshot.getBytes());

// SAVE ORDER

Order order = new Order();
order.setUserId(userId);
order.setName(name);
order.setMobile(mobile);
order.setServiceName(serviceName);
order.setExtraData(extraData);
order.setStatus("Pending");

Order savedOrder = orderRepository.save(order);

// SAVE PAYMENT

Payment payment = new Payment();
payment.setOrderId(savedOrder.getId());
payment.setUserId(userId);
payment.setName(name);
payment.setServiceName(serviceName);
payment.setAmount(amount);
payment.setScreenshot(screenshotName);
payment.setPaymentStatus("Success");

paymentRepository.save(payment);

// SAVE DOCUMENTS

for(MultipartFile file : documents){

String fileName = file.getOriginalFilename();

Path path = Paths.get(uploadDir + fileName);
Files.write(path,file.getBytes());

Document doc = new Document();

doc.setOrderId(savedOrder.getId());
doc.setDocumentType(fileName);
doc.setFileName(fileName);

documentRepository.save(doc);

}

// CREATE NOTIFICATION
notificationService.createNotification(
    userId,
    savedOrder.getId(),
    "Payment Successful",
    "Your payment for " + serviceName + " has been received successfully.",
    "payment"
);

return "Order Placed Successfully";

} catch(Exception e){

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