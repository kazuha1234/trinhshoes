package com.trinh.trinhshoes.repository;

import com.trinh.trinhshoes.entity.Categories;
import com.trinh.trinhshoes.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, String> {
    @Query("SELECT p FROM Products p WHERE p.productName like %?1%")
    List<Products> searchProducts(String keyword);

    Page<Products> searchProductsByCategoryId(Categories categoryId, Pageable pageable);
}
