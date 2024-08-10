package com.trinh.trinhshoes.services;

import com.trinh.trinhshoes.entity.Carts;
import com.trinh.trinhshoes.entity.Products;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ShoppingCartService {
    void add(Carts item);
    void remove(Products product);
    void clear();
    Collection<Carts> getItems();
    int getCount();
    double getTotal();
    Carts update(Products productId, int quantity);
}
