package com.example.E_seva_kendra.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.Admin;
import com.example.E_seva_kendra.model.LoginRequest;
import com.example.E_seva_kendra.repository.AdminRepository;
import com.example.E_seva_kendra.model.ChangePasswordRequest;
import com.example.E_seva_kendra.model.CreateAdminRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public String adminLogin(@RequestBody LoginRequest request) {

        Optional<Admin> admin = adminRepository.findByAdminEmail(request.getEmail());

        if (admin.isPresent()) {

            if (encoder.matches(request.getPassword(), admin.get().getPassword())) {
                return "Login Successful";
            }
        }

        return "Invalid Email or Password";
    }

    // Temporary API to generate encrypted password
    @GetMapping("/generate")
    public String generatePassword() {
        return encoder.encode("admin123");
    }
    
    
    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequest request) {

        Optional<Admin> admin = adminRepository.findByAdminEmail(request.getEmail());

        if (admin.isPresent()) {

            // check old password
            if (encoder.matches(request.getOldPassword(), admin.get().getPassword())) {

                // encode new password
                String newHashedPassword = encoder.encode(request.getNewPassword());

                admin.get().setPassword(newHashedPassword);
                adminRepository.save(admin.get());

                return "Password Updated Successfully";
            }

            return "Old Password Incorrect";
        }

        return "Admin Not Found";
    }
    
    @PostMapping("/create")
    public String createAdmin(@RequestBody CreateAdminRequest request) {

        Optional<Admin> existingAdmin = adminRepository.findByAdminEmail(request.getEmail());

        if (existingAdmin.isPresent()) {
            return "Admin with this email already exists";
        }

        Admin newAdmin = new Admin();

        newAdmin.setAdminEmail(request.getEmail());

        // encrypt password before saving
        String hashedPassword = encoder.encode(request.getPassword());
        newAdmin.setPassword(hashedPassword);

        adminRepository.save(newAdmin);

        return "New Admin Created Successfully";
    }
}