package com.trinh.trinhshoes.controllers;

import com.trinh.trinhshoes.entity.Carts;
import com.trinh.trinhshoes.entity.Products;
import com.trinh.trinhshoes.services.ProductService;
import com.trinh.trinhshoes.services.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Qualifier("productServiceImp")
    @Autowired
    private ProductService ProductService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        List<Carts> cart = (List<Carts>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("items", cart);
        model.addAttribute("total", calculateTotal(cart));
        return "cart";
    }

    @GetMapping("/cart/add/{productId}")
    public String addToCart(HttpSession session, @PathVariable String productId) {
        List<Carts> cart = (List<Carts>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        // You should fetch the product from your product repository
        Products product = ProductService.findById(productId);

        boolean found = false;
        for (Carts item : cart) {
            if (item.getProductId().getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new Carts(product, 1));
        }

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(HttpSession session, @RequestParam("id") String productId) {
        List<Carts> cart = (List<Carts>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(item -> item.getProductId().getProductId().equals(productId));
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(HttpSession session, @RequestParam("id") String productId, @RequestParam("qty") int quantity) {
        List<Carts> cart = (List<Carts>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        boolean productFound = false;

        for (Carts c : cart) {
            if (c.getProductId().getProductId().equals(productId)) {
                c.setQuantity(quantity);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            System.out.println("Product not found in cart: " + productId);
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
        //        System.out.println("Product ID: " + productId + " Quantity: " + quantity);
//        Carts cart = new Carts();
//
//        cart = shoppingCartService.update(productId, quantity);
//        session.setAttribute("cart", cart);

        //return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }

    private double calculateTotal(List<Carts> cart) {
        return cart.stream().mapToDouble(item -> item.getProductId().getPrice() * item.getQuantity()).sum();
    }
}
