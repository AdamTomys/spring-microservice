package com.amigo.customer.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    String firstName;
    String lastName;
    String email;
}
