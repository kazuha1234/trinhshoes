package com.trinh.trinhshoes.repository;

import com.trinh.trinhshoes.entity.Customers;
import com.trinh.trinhshoes.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByCustomerId(Customers customerId);
    Orders findByCustomerId(Customers customerId);
}
