package com.example.E_seva_kendra.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.User;
import com.example.E_seva_kendra.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // REGISTER API
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            return "Email already exists";
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody User user){

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){

            User dbUser = existingUser.get();

            if(passwordEncoder.matches(user.getPassword(), dbUser.getPassword())){
                return "Login Successful";
            }
        }

        return "Invalid Email or Password";
    }

}