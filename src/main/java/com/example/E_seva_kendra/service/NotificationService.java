package com.example.E_seva_kendra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_seva_kendra.model.Notification;
import com.example.E_seva_kendra.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(Long userId,
                                   Long orderId,
                                   String title,
                                   String message,
                                   String type) {

        Notification notification = new Notification();

        notification.setUserId(userId);
        notification.setOrderId(orderId);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setRead(false);

        notificationRepository.save(notification);
    }
}