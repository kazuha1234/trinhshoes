package com.trinh.trinhshoes.services.imp;

import com.trinh.trinhshoes.entity.Categories;
import com.trinh.trinhshoes.models.dto.ProductsDto;
import com.trinh.trinhshoes.models.mapper.ProductMapper;
import com.trinh.trinhshoes.entity.Products;
import com.trinh.trinhshoes.repository.ProductRepository;
import com.trinh.trinhshoes.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductsDto> findAllProduct() {
        List<Products> products = productRepository.findAll();

        return products.stream().map(ProductMapper::mapToProductsDto).collect(Collectors.toList());
    }

    @Override
    public Page<Products> findAllProduct(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 6);

        return this.productRepository.findAll(pageable);
    }

    @Override
    public List<Products> searchProduct(String keyword) {
        return this.productRepository.searchProducts(keyword);
    }

    @Override
    public Page<Products> searchProduct(String keyword, Integer pageNo) {
        List<Products> list = this.searchProduct(keyword);

        Pageable pageable = PageRequest.of(pageNo-1, 6);

        int start = (int) pageable.getOffset();
        int end = Math.min((int) (pageable.getOffset() + pageable.getPageSize()), list.size());

        list = list.subList(start, end);

        return new PageImpl<>(list, pageable, this.searchProduct(keyword).size());
    }

    @Override
    public Page<Products> searchProductsByCategoryId(Categories categoryId, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 6);

        return this.productRepository.searchProductsByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Products> searchProductsByDesc(Page<Products> products, Integer pageNo) {
        List<Products> productsList = new ArrayList<>(products.getContent());
        productsList.sort(Comparator.comparing(Products::getPrice).reversed());

        return new PageImpl<>(productsList, PageRequest.of(pageNo - 1, products.getSize()), products.getTotalElements());
    }

    @Override
    public Page<Products> searchProductsByAsc(Page<Products> products, Integer pageNo) {
        List<Products> productsList = new ArrayList<>(products.getContent());
        productsList.sort(Comparator.comparing(Products::getPrice));

        return new PageImpl<>(productsList, PageRequest.of(pageNo - 1, products.getSize()), products.getTotalElements());
    }

    @Override
    public Products findById(String id) {
        Products rs = this.productRepository.findById(id).orElse(null);

        return rs;
    }
}
