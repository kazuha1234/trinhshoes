package com.trinh.trinhshoes.services.imp;

import com.trinh.trinhshoes.entity.Carts;
import com.trinh.trinhshoes.entity.Products;
import com.trinh.trinhshoes.services.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
@Component
public class ShoppingCartServiceImp implements ShoppingCartService {
    Map<Products, Carts> maps = new HashMap<>();

    public void add(Carts item) {
        Carts cart = maps.get(item.getProductId());
        if(cart == null) {
            maps.put(item.getProductId(), item);
        } else {
            cart.setQuantity(cart.getQuantity() + item.getQuantity());
        }
    }

    @Override
    public Carts update(Products productId, int quantity) {
        Carts cart = maps.get(productId);
        cart.setQuantity(quantity);

        return cart;
    }

    public void remove(Products product) {
        maps.remove(product);
    }

    public void clear() {
        maps.clear();
    }

    public Collection<Carts> getItems() {
        return maps.values();
    }

    public int getCount() {
        return maps.size();
    }

    public double getTotal() {
        double total = 0;
        for(Carts cart : maps.values()) {
            total += cart.getProductId().getPrice() * cart.getQuantity();
        }

        return total;
    }
}
