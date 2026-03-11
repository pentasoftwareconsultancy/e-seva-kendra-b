package com.example.E_seva_kendra.model;

import jakarta.persistence.*;

@Entity
@Table(name="documents")
public class Document {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private Long orderId;

 private String documentType;

 private String fileName;

 public Long getId() { return id; }

 public Long getOrderId() { return orderId; }

 public String getDocumentType() { return documentType; }

 public String getFileName() { return fileName; }

 public void setId(Long id) { this.id = id; }

 public void setOrderId(Long orderId) { this.orderId = orderId; }

 public void setDocumentType(String documentType) { this.documentType = documentType; }

 public void setFileName(String fileName) { this.fileName = fileName; }
}