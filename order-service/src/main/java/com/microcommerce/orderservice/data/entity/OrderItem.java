package com.microcommerce.orderservice.data.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne // bidirectional, OrderItem is owning side
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "sku_code", nullable = false)
    private String skuCode;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;
}
