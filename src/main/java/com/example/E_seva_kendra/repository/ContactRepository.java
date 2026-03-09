package com.example.E_seva_kendra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_seva_kendra.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}