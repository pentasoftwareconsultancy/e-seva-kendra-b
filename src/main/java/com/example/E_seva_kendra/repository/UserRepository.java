package com.example.E_seva_kendra.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_seva_kendra.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
