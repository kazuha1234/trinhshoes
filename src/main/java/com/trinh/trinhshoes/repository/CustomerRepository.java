package com.trinh.trinhshoes.repository;

import com.trinh.trinhshoes.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customers, String> {
    List<Customers> findAllByEmail(String email);
    Customers findByEmail(String email);
    Customers findById(int id);
}
