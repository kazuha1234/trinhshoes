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
    private String cartId;

    @Column(nullable = false)
    private float price;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customerId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products productId;


}
