package com.amigo.notifications.ampq;

import com.amigo.clients.notifications.NotificationRequest;
import com.amigo.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationConsumer {

    final NotificationService service;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request) {
       log.info("Consuming message from queue");
       service.sendNotification(request);
    }
}
