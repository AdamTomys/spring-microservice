package com.amigo.customer.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = false)
@Data
public class CustomerRequest {
    String firstName;
    String lastName;
    String email;
}
