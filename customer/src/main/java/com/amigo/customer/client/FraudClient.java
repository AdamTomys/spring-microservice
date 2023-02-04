package com.amigo.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "fraudCheck",
        url = "http://localhost:8081/api/v1/fraud/"
)
public interface FraudClient {

    @GetMapping("/check/{customerId}")
    FraudResponse checkFraud(@PathVariable("customerId") Integer customerId);
}
