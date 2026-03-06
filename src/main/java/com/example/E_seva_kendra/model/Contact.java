package com.example.E_seva_kendra.model;

import jakarta.persistence.*;

@Entity
<<<<<<< HEAD:src/main/java/com/example/E_seva_kendra/model/User.java
@Table(name = "users")
public class User {
=======
@Table(name = "contact")
public class Contact {
>>>>>>> 56b21aa883656ec109512d60a15afc7acbb006aa:src/main/java/com/example/E_seva_kendra/model/Contact.java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
<<<<<<< HEAD:src/main/java/com/example/E_seva_kendra/model/User.java
    private String phone;
    private String password;

    public User() {
    }
=======
    private String service;
    private String mobile;
    private String message;
    private String status;

    public Contact() {}
>>>>>>> 56b21aa883656ec109512d60a15afc7acbb006aa:src/main/java/com/example/E_seva_kendra/model/Contact.java

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

<<<<<<< HEAD:src/main/java/com/example/E_seva_kendra/model/User.java
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
=======
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
>>>>>>> 56b21aa883656ec109512d60a15afc7acbb006aa:src/main/java/com/example/E_seva_kendra/model/Contact.java
    }
}