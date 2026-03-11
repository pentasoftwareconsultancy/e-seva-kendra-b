package com.example.E_seva_kendra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_seva_kendra.model.Document;

public interface DocumentRepository extends JpaRepository<Document,Long>{

 List<Document> findByOrderId(Long orderId);

}