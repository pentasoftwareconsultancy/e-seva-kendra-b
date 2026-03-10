package com.example.E_seva_kendra.controller;
import java.io.File;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.nio.file.*;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api/payment")
public class PaymentQRController {

    private final String uploadDir = "uploads/";

  
    @PostMapping("/upload-qr")
    public ResponseEntity<?> uploadQR(@RequestParam("file") MultipartFile file) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            // File type validation
            String type = file.getContentType();

            if (!(type.equals("image/png") || type.equals("image/jpeg") || type.equals("image/jpg"))) {
                return ResponseEntity.badRequest().body("Only PNG/JPG images allowed");
            }

            // File size limit (5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest().body("Image must be less than 5MB");
            }

            String uploadDir = "uploads/upi-qr/";
            File folder = new File(uploadDir);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Delete previous QR
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }

            // Save new QR
            String filename = "qr.png";
            Path path = Paths.get(uploadDir + filename);

            Files.write(path, file.getBytes());

            return ResponseEntity.ok("QR Uploaded Successfully");

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Upload Failed");

        }
    }
    
    // Get QR
    @GetMapping("/qr")
    public String getQR() {

        File file = new File("uploads/upi-qr/qr.png");

        if (file.exists()) {
            return "/uploads/upi-qr/qr.png";
        }

        return "";

    }

}