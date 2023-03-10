package com.amigo.notifications;

import com.amigo.amqp.rabbitmq.RabbitMQMessageProducer;
import com.amigo.notifications.ampq.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.amigo.notifications",
                "com.amigo.amqp",
        }
)
@EnableFeignClients(basePackages = "com.amigo.clients")
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class NotificationsApp {
    public static void main(String[] args) {
        SpringApplication.run(NotificationsApp.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer rabbitMQMessageProducer, NotificationConfig notificationConfig) {
        return args -> {
            rabbitMQMessageProducer.publish(
                    //any type of object
                    "Queue check during app start",
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getNotificationRoutingKey());
        };
    }
}
