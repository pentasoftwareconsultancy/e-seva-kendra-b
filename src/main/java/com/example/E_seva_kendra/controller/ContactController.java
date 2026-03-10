package com.example.E_seva_kendra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.E_seva_kendra.model.Contact;
import com.example.E_seva_kendra.repository.ContactRepository;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    public Contact saveMessage(@RequestBody Contact contact) {
        contact.setStatus("Unread");
        return contactRepository.save(contact);
    }

    @GetMapping
    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    @PutMapping("/{id}/read")
    public Contact markAsRead(@PathVariable Long id) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        contact.setStatus("Read");

        return contactRepository.save(contact);
    }
}