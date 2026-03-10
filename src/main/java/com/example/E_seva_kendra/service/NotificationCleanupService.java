package com.example.E_seva_kendra.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.E_seva_kendra.repository.NotificationRepository;

@Service
public class NotificationCleanupService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Run every day at 2 AM
    @Scheduled(cron = "0 0 2 * * ?")
    public void deleteOldNotifications(){

        LocalDateTime fifteenDaysAgo = LocalDateTime.now().minusDays(15);

        notificationRepository.deleteByCreatedAtBefore(fifteenDaysAgo);

        System.out.println("Old notifications older than 15 days deleted");
    }
}




