package com.trinh.trinhshoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carts {
    @Id
    @GeneratedValue
    private int cartId;

    @Column(nullable = false)
    private int quantity;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customerId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products productId;

    public Carts(Products product, int quantity) {
        this.productId = product;
        this.quantity = quantity;
    }
}
