package com.amigo.customer.controller;

import com.amigo.customer.dto.CustomerRequest;
import com.amigo.customer.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers/")
@Log4j2
public record CustomerController(
        CustomerService customerService
) {

    @PostMapping("register")
    public void registerCustomer(@RequestBody CustomerRequest request) {
        log.info("new customer registration {}", request);
        customerService.registerCustomer(request);
    }
}
