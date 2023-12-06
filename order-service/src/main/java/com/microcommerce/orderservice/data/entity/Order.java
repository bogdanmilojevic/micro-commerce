package com.microcommerce.orderservice.data.entity;

import com.microcommerce.orderservice.data.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

//    @Column(name = "order_status")
//    @Enumerated(EnumType.STRING)
//    private OrderStatus orderStatus;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

//    @Embedded
//    private AuditableEntity auditableEntity;
}
