package com.amigo.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notifications")
public interface NotificationClient {

    @PostMapping("/api/v1/notifications/send")
    NotificationResponse sendNotification(@RequestBody NotificationRequest request);
}
