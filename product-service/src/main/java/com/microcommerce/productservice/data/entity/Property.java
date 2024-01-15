package com.microcommerce.productservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "product")
    private Set<ProductProperty> products;
}
