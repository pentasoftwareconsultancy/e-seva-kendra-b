package com.example.E_seva_kendra.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_seva_kendra.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}