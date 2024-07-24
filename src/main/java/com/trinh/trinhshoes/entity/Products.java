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
public class Products {

    @Id
    @GeneratedValue
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private float price;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories categoryId;
}
