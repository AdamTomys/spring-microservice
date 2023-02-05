package com.amigo.fraud.controller;

import com.amigo.clients.fraud.FraudResponse;
import com.amigo.fraud.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fraud/")
@Log4j2
public class FraudController {

    FraudCheckService fraudCheckService;

    @GetMapping("check/{customerId}")
    public FraudResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Fraud check for customer id {}", customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudResponse(isFraudulentCustomer);
    }
}
