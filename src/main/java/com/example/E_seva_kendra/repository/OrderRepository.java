package com.example.E_seva_kendra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_seva_kendra.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	   long countByStatus(String status);
	   List<Order> findByUserId(Long userId);
}