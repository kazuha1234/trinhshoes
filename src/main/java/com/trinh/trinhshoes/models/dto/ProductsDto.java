package com.trinh.trinhshoes.models.dto;

import com.trinh.trinhshoes.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
    private String productId;
    private String productName;
    private float price;
    private Categories categoryId;
    private String imageUrl;
}
