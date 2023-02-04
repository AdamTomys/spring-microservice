package com.amigo.customer.service;

import com.amigo.customer.client.FraudClient;
import com.amigo.customer.client.FraudResponse;
import com.amigo.customer.dto.CustomerRequest;
import com.amigo.customer.dao.Customer;
import com.amigo.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        FraudClient fraudClient) {

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudResponse fraudResponse = fraudClient.checkFraud(customer.getId());
        if (fraudResponse.isFraudster()) {
            throw new IllegalStateException("Fraud alert");
        }
    }
}
