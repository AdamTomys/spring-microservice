package com.amigo.customer.service;

import com.amigo.customer.controller.CustomerRequest;
import com.amigo.customer.dao.Customer;
import com.amigo.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.save(customer);
    }
}
