package com.amigo.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReportsApp {
    public static void main(String[] args) {
        SpringApplication.run(ReportsApp.class, args);
    }
}
