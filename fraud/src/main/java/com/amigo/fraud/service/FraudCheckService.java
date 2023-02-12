package com.amigo.fraud.service;

import com.amigo.fraud.dao.FraudCheckHistory;
import com.amigo.fraud.repository.FraudCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    final FraudCheckRepository fraudCheckRepository;

    @Autowired
    public FraudCheckService(FraudCheckRepository fraudCheckRepository) {
        this.fraudCheckRepository = fraudCheckRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
