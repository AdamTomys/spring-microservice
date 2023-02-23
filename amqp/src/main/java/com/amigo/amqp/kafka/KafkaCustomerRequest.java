package com.amigo.amqp.kafka;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KafkaCustomerRequest {
    String message;
    Integer customerId;
    String createdAt;
}
