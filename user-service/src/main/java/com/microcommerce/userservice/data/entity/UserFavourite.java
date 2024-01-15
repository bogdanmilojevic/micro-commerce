package com.microcommerce.userservice.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_favourites")
public class UserFavourite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "favourite_id", nullable = false)
    private Favourite favourite;
}
