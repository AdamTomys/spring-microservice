package com.amigo.reports.kafka;

import com.amigo.amqp.kafka.KafkaCustomerRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaConsumer {

    @KafkaListener(topics = "reports", groupId = "foo", containerFactory = "listenerFactory")
    void listener(KafkaCustomerRequest data) {
        log.info("Data received customer request from kafka: {}", data);
    }

}
