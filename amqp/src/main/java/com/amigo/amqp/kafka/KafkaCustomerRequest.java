package com.amigo.amqp.kafka;

import lombok.*;

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
