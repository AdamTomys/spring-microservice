package com.amigo.notifications.ampq;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@FieldDefaults(makeFinal = false)
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    String internalExchange;

    @Value("${rabbitmq.queues.notification}")
    String notificationQueue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    String notificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueue);
    }

    @Bean
    public Binding internalNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.notificationRoutingKey);
    }
}
