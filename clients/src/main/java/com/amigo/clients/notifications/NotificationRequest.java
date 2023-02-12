package com.amigo.clients.notifications;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    Integer customerId;
    String customerEmail;
    String message;
}
