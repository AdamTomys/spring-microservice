package com.amigo.clients.fraud;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false)
public class FraudResponse {
    Boolean isFraudster;
}
