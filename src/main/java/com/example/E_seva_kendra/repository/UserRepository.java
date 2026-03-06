package com.example.E_seva_kendra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_seva_kendra.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByStatus(String status);

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

}