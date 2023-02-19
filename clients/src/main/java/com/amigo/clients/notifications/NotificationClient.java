package com.amigo.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("notifications") works only with eureka-server
@FeignClient(
        name = "notifications",
        url = "${clients.notifications.url}"
)
public interface NotificationClient {

    @PostMapping("/api/v1/notifications/send")
    NotificationResponse sendNotification(@RequestBody NotificationRequest request);
}
