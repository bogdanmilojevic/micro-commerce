package com.microcommerce.productservice.data.entity;

import com.microcommerce.productservice.data.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product extends AuditMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "sku_code",unique = true)
    private String skuCode;

    @Column(name = "name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "product")
    private Set<ProductProperty> properties;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "image_url")
    private String imageUrl;


    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "rating", nullable = false)
    private BigDecimal rating;

    @Column(name = "num_of_reviews", nullable = false)
    private int numOfReviews;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    public Product() {
        this.id = UUID.randomUUID();
    }

}
