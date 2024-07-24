package com.trinh.trinhshoes.models.mapper;

import com.trinh.trinhshoes.models.dto.ProductsDto;
import com.trinh.trinhshoes.entity.Products;

public class ProductMapper {
    public static ProductsDto mapToProductsDto(Products products) {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setProductId(products.getProductId());
        productsDto.setProductName(products.getProductName());
        productsDto.setPrice(products.getPrice());
        productsDto.setCategoryId(products.getCategoryId());

        return productsDto;
    }
}
