package com.amigo.customer.service;

import com.amigo.amqp.kafka.KafkaCustomerRequest;
import com.amigo.amqp.rabbitmq.RabbitMQMessageProducer;
import com.amigo.clients.fraud.FraudClient;
import com.amigo.clients.fraud.FraudResponse;
import com.amigo.clients.notifications.NotificationClient;
import com.amigo.clients.notifications.NotificationRequest;
import com.amigo.customer.dao.Customer;
import com.amigo.customer.dto.CustomerRequest;
import com.amigo.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(makeFinal = false)
public class CustomerService {

    @Value("${rabbitmq.exchanges.internal}")
    String exchangeName;
    @Value("${rabbitmq.routing-keys.internal-notification}")
    String routingKey;
    final CustomerRepository customerRepository;
    final FraudClient fraudClient;
    final KafkaTemplate<String, KafkaCustomerRequest> kafkaTemplate;
    final NotificationClient notificationClient;//not used since rabbitmq implemented
    final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = saveCustomer(request);
        sendCustomerReportToKafka(customer);
        FraudResponse fraudResponse = fraudClient.checkFraud(customer.getId());
        if (fraudResponse.getIsFraudster()) {
            throw new IllegalStateException("Fraud alert");
        }
        sendNotificationMessageToRabbitMq(customer);
    }

    private void sendCustomerReportToKafka(Customer customer) {
        log.info("Publishing message to kafka MQ");
        kafkaTemplate.send(
                "reports",
                KafkaCustomerRequest.builder()
                        .message("new customer registration")
                        .customerId(customer.getId())
                        .createdAt(LocalDateTime.now().toString())
                        .build()
        );
    }

    private void sendNotificationMessageToRabbitMq(Customer customer) {
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .customerId(customer.getId())
                .customerEmail(customer.getEmail())
                .message("Registration finished successfully")
                .build();

        rabbitMQMessageProducer.publish(
                notificationRequest,
                exchangeName,
                routingKey
        );
    }

    private Customer saveCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        return customer;
    }
}
