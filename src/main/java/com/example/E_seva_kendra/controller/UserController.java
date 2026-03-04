package com.example.E_seva_kendra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.User;
import com.example.E_seva_kendra.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userRepository.save(user);
        return "User Registered Successfully!";
    }
}