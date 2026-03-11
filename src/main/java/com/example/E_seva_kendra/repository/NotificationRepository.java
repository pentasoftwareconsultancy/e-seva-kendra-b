package com.example.E_seva_kendra.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_seva_kendra.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

	long countByUserIdAndIsReadFalse(Long userId);

	void deleteByCreatedAtBefore(LocalDateTime date);

}