package com.example.E_seva_kendra.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") // or "contact" — you can choose one
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Fields from first version
    private String service;
    private String mobile;
    private String message;
    private String status = "Unread";
    private LocalDateTime createdAt = LocalDateTime.now();

    // Fields from second version
    private String phone;
    private String password;

    public User() {}

    // ID
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Email
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // First version fields
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Second version fields
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}