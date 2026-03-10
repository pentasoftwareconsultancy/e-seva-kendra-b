package com.example.E_seva_kendra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.E_seva_kendra.model.Notification;
import com.example.E_seva_kendra.repository.NotificationRepository;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("*")
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
    
    //MARK ALL AS READ
    @PatchMapping("/read-all/{userId}")
    public String markAllAsRead(@PathVariable Long userId) {

        List<Notification> notifications =
                notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);

        for (Notification notification : notifications) {
            notification.setRead(true);
        }

        notificationRepository.saveAll(notifications);

        return "All notifications marked as read";
    }

    // GET UNREAD COUNT
    @GetMapping("/unread-count/{userId}")
    public long getUnreadNotificationCount(@PathVariable Long userId) {

        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

}