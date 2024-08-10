package com.trinh.trinhshoes.controllers;

import com.trinh.trinhshoes.entity.Customers;
import com.trinh.trinhshoes.entity.Orders;
import com.trinh.trinhshoes.repository.CustomerRepository;
import com.trinh.trinhshoes.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrackingController {
    CustomerRepository customerRepository;
    OrderRepository orderRepository;

    @Autowired
    public TrackingController(CustomerRepository repository, OrderRepository orderRepository) {
        this.customerRepository = repository;
        this.orderRepository = orderRepository;
    }
    @GetMapping("/tracking")
    public String tracking() {
        return "tracking";
    }

    @GetMapping("/tracking-order")
    public String tracking(Model model, @RequestParam(name = "email") String email) {
        // Fetch the customer entity by email
        List<Customers> customers = (List<Customers>) customerRepository.findAllByEmail(email);

        List<Orders> allOrders = new ArrayList<>();
        for (Customers customer : customers) {
            // Retrieve the orders for each customer
            List<Orders> orders = (List<Orders>) orderRepository.findAllByCustomerId(customer);
            allOrders.addAll(orders);
        }

        // Add the orders to the model to display them in the view
        model.addAttribute("orders", allOrders);

        return "tracking";
    }
}
