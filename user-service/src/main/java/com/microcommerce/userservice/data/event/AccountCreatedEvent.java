package com.microcommerce.userservice.data.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreatedEvent {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String activationToken;
}
