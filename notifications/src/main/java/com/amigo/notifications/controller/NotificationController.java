package com.amigo.notifications.controller;

import com.amigo.clients.notifications.NotificationRequest;
import com.amigo.clients.notifications.NotificationResponse;
import com.amigo.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications/")
@RequiredArgsConstructor
@Log4j2
public class NotificationController {

    NotificationService notificationService;

    @PostMapping("send")
    public NotificationResponse sendNotification(@RequestBody NotificationRequest request) {
        log.info("Notification request received for customer id {}", request.getCustomerId());
        return notificationService.sendNotification(request);
    }
}
