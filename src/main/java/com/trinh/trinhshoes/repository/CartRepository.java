package com.trinh.trinhshoes.repository;

import com.trinh.trinhshoes.entity.Carts;
import com.trinh.trinhshoes.entity.Customers;
import com.trinh.trinhshoes.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Carts, Integer> {
    List<Carts> findByCustomerId(Customers customer);
    Carts findByCustomerIdAndProductId(Customers customer, Products product);
}
