package com.example.E_seva_kendra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_seva_kendra.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // From first branch
    List<User> findByStatus(String status);

    // From second branch
    Optional<User> findByEmail(String email);

}