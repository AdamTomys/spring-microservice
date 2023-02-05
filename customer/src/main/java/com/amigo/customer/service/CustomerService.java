package com.amigo.customer.service;

import com.amigo.clients.fraud.FraudClient;
import com.amigo.clients.fraud.FraudResponse;
import com.amigo.clients.notifications.NotificationClient;
import com.amigo.clients.notifications.NotificationRequest;
import com.amigo.clients.notifications.NotificationResponse;
import com.amigo.customer.dto.CustomerRequest;
import com.amigo.customer.dao.Customer;
import com.amigo.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerService {

    CustomerRepository customerRepository;
    FraudClient fraudClient;
    NotificationClient notificationClient;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = saveCustomer(request);
        FraudResponse fraudResponse = fraudClient.checkFraud(customer.getId());
        if (fraudResponse.getIsFraudster()) {
            throw new IllegalStateException("Fraud alert");
        }
        NotificationResponse notificationResponse = notificationClient.sendNotification(
                NotificationRequest.builder()
                        .customerId(customer.getId())
                        .customerEmail(customer.getEmail())
                        .message("Registration finished successfully")
                        .build()
        );
        log.info("New customer registered. Is confirmation sent: 2{}, email address: 1{}", customer.getEmail(), notificationResponse.getIsSent());
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
