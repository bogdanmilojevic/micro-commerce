package com.microcommerce.userservice.data.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "genders")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "value", nullable = false)
    private String value;

}
