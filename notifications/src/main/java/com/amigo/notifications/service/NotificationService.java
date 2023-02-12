package com.amigo.notifications.service;

import com.amigo.clients.notifications.NotificationRequest;
import com.amigo.clients.notifications.NotificationResponse;
import com.amigo.notifications.dao.Notification;
import com.amigo.notifications.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    final NotificationRepository notificationRepository;

    public NotificationResponse sendNotification(NotificationRequest request) {
        createNotificationLogInDb(request);
        //TODO sending email to customer
        return new NotificationResponse(true);
    }

    private void createNotificationLogInDb(NotificationRequest request) {
        Notification notification = Notification.builder()
                .toCustomerId(request.getCustomerId())
                .message(request.getMessage())
                .toCustomerEmail(request.getCustomerEmail())
                .sentAt(LocalDateTime.now())
                .sender("Customer app")
                .build();
        notificationRepository.saveAndFlush(notification);
    }
}
