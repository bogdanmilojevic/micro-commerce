package com.microcommerce.userservice.data.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "favourites")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "sku_code", nullable = false)
    private String skuCode;

    @OneToMany(mappedBy = "favourite")
    private List<UserFavourite> userFavourites;
}
