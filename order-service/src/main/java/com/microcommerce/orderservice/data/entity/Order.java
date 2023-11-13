package com.microcommerce.orderservice.data.entity;

import com.microcommerce.orderservice.data.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "t_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

//    @Column(name = "order_status")
//    @Enumerated(EnumType.STRING)
//    private OrderStatus orderStatus;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "order_number")
    private String orderNumber;

//    @Embedded
//    private AuditableEntity auditableEntity;
}
