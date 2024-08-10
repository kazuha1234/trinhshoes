package com.trinh.trinhshoes.services;

import com.trinh.trinhshoes.entity.Customers;

public interface AuthService {
    boolean registerCustomer(String fullName, String email, String password, String address, String phoneNumber);
    boolean loginCustomer(String email, String password);
    Customers getCustomerByEmail(String email);
}
