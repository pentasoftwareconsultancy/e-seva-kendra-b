package com.example.E_seva_kendra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.Notification;
import com.example.E_seva_kendra.repository.NotificationRepository;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // GET USER NOTIFICATIONS
    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId);
    }

    // MARK NOTIFICATION AS READ
    @PatchMapping("/read/{id}")
    public Notification markAsRead(@PathVariable Long id) {

        Notification notification = notificationRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);

        return notificationRepository.save(notification);
    }

    // GET UNREAD COUNT
    @GetMapping("/unread-count/{userId}")
    public long getUnreadNotificationCount(@PathVariable Long userId) {

        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

}