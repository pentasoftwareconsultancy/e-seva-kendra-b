package com.example.E_seva_kendra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_seva_kendra.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    long countByUserIdAndIsReadFalse(Long userId);

}