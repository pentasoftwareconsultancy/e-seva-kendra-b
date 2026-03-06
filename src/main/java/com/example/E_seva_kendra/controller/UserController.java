package com.example.E_seva_kendra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.User;
import com.example.E_seva_kendra.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contact")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Save contact message
    @PostMapping
    public User saveContact(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get all contacts
    @GetMapping
    public List<User> getAllContacts() {
        return userRepository.findAll();
    }

    // Get only unread messages
    @GetMapping("/unread")
    public List<User> getUnreadMessages() {
        return userRepository.findByStatus("Unread");
    }

    
    // Mark message as read
    @PutMapping("/{id}/read")
    public User markAsRead(@PathVariable Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setStatus("Read");
            return userRepository.save(user);
        }

        return null;
    }

    // Delete message
    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id) {

        userRepository.deleteById(id);

        return "Contact message deleted successfully";
    }

}