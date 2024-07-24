package com.trinh.trinhshoes.services;

import com.trinh.trinhshoes.entity.Categories;
import com.trinh.trinhshoes.entity.Products;
import com.trinh.trinhshoes.models.dto.ProductsDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.List;

@Service
public interface ProductService {
    List<ProductsDto> findAllProduct();
    Page<Products> findAllProduct(Integer pageNo);
    List<Products> searchProduct(String keyword);
    Page<Products> searchProduct(String keyword, Integer pageNo);
    Page<Products> searchProductsByCategoryId(Categories categoryId, Integer pageNo);
    Page<Products> searchProductsByDesc(Page<Products> products, Integer pageNo);
    Page<Products> searchProductsByAsc(Page<Products> products, Integer pageNo);
}
