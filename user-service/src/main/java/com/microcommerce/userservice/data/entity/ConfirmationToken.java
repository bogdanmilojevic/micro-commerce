package com.microcommerce.userservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token")
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private UUID token;

    private OffsetDateTime expirationDate;

    @OneToOne(mappedBy = "confirmationToken")
    private User user;

    private boolean used;

    public ConfirmationToken() {
        this.token = UUID.randomUUID();
        this.expirationDate = OffsetDateTime.now().plusHours(2);
    }

    public boolean isExpired() {
        return expirationDate.isBefore(OffsetDateTime.now());
    }
}
