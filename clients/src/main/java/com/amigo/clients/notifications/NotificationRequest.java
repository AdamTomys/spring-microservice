package com.amigo.clients.notifications;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false)
public class NotificationRequest {
    Integer customerId;
    String customerEmail;
    String message;
}
