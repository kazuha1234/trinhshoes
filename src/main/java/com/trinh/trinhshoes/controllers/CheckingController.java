package com.trinh.trinhshoes.controllers;

import com.trinh.trinhshoes.entity.Carts;
import com.trinh.trinhshoes.entity.Customers;
import com.trinh.trinhshoes.entity.Orders;
import com.trinh.trinhshoes.entity.Products;
import com.trinh.trinhshoes.repository.CartRepository;
import com.trinh.trinhshoes.repository.CustomerRepository;
import com.trinh.trinhshoes.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CheckingController {
    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    private CartRepository cartRepository;

    @Autowired
    public CheckingController(OrderRepository orderRepository, CustomerRepository customerRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session) {
        // Retrieve the customer ID from the session and cast it to an Integer
        Integer customerId = (Integer) session.getAttribute("customerId");

        if (customerId == null) {
            // Handle case where the customer is not logged in
            return "redirect:/login"; // Redirect to the login page
        }

        // Fetch the customer entity from the database using the ID
        Customers customer = customerRepository.findById(customerId);
        System.out.println("Customer ID: " + customer.getId());

        // Retrieve the cart items for this customer
        List<Carts> cartItems = (List<Carts>) session.getAttribute("cart");
        System.out.println("Cart items: " + cartItems);

        if (cartItems == null || cartItems.isEmpty()) {
            // Handle case where the cart is empty
            return "redirect:/cart"; // Redirect back to the cart page
        }

        // Calculate total amount
        float totalAmount = 0;
        for (Carts cart : cartItems) {
            totalAmount += cart.getProductId().getPrice() * cart.getQuantity();
        }

        // Create and save the orders for each item in the cart
        for (Carts cart : cartItems) {
            Orders order = new Orders();
            order.setCustomerId(customer);
            order.setProductId(cart.getProductId());
            order.setTotalAmount(cart.getQuantity()); // Assuming `totalAmount` is quantity-based
            order.setTotal(cart.getProductId().getPrice() * cart.getQuantity());

            // Persist the order in the database
            orderRepository.save(order);
        }

        // Optionally, clear the cart after checkout
        cartRepository.deleteAll(cartItems);

        // Redirect to an order confirmation page
        return "redirect:/cart";
    }
}
