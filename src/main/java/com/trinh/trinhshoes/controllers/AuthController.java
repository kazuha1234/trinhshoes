package com.trinh.trinhshoes.controllers;

import com.trinh.trinhshoes.entity.Customers;
import com.trinh.trinhshoes.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/register")
    public String showRegister() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String address,
                               @RequestParam String phoneNumber,
                               Model model) {
        if (authService.registerCustomer(fullName, email, password, address, phoneNumber)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Email already exists!");
            return "registration";
        }
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        if (authService.loginCustomer(email, password)) {
            session.setAttribute("email", email);
            session.setAttribute("customerId", authService.getCustomerByEmail(email).getId());
            session.setAttribute("fullName", authService.getCustomerByEmail(email).getFullName());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session to log out the user
        return "redirect:/home"; // Redirect to home page after logout
    }
}
