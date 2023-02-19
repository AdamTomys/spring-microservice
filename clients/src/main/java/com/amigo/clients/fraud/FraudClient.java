package com.amigo.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient("fraud") works only with eureka-server
@FeignClient(
        name = "fraud",
        url = "${clients.fraud.url}"
)
public interface FraudClient {

    @GetMapping("/api/v1/fraud/check/{customerId}")
    FraudResponse checkFraud(@PathVariable("customerId") Integer customerId);
}