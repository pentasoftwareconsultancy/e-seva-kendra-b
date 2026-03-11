package com.example.E_seva_kendra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.Document;
import com.example.E_seva_kendra.repository.DocumentRepository;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/{orderId}/documents")
    public List<Document> getDocumentsByOrderId(@PathVariable Long orderId){
        return documentRepository.findByOrderId(orderId);
    }
}