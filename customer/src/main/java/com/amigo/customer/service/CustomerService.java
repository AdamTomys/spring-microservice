package com.amigo.customer.service;

import com.amigo.clients.fraud.FraudClient;
import com.amigo.clients.fraud.FraudResponse;
import com.amigo.customer.dto.CustomerRequest;
import com.amigo.customer.dao.Customer;
import com.amigo.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    CustomerRepository customerRepository;
    FraudClient fraudClient;

    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudResponse fraudResponse = fraudClient.checkFraud(customer.getId());
        if (fraudResponse.getIsFraudster()) {
            throw new IllegalStateException("Fraud alert");
        }
    }
}
