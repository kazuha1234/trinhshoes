package com.trinh.trinhshoes.services.imp;

import com.trinh.trinhshoes.entity.Customers;
import com.trinh.trinhshoes.repository.CustomerRepository;
import com.trinh.trinhshoes.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthServiceImp implements AuthService {
    private final CustomerRepository customerRepository;

    @Autowired
    public AuthServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean registerCustomer(String fullName, String email, String password, String address, String phoneNumber) {
        if (customerRepository.findByEmail(email) != null) {
            return false;
        }
        Customers user = new Customers();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        customerRepository.save(user);
        return true;
    }

    @Override
    public boolean loginCustomer(String email, String password) {
        Customers user = customerRepository.findByEmail(email);
        return user != null && password.equals(user.getPassword());
    }

    @Override
    public Customers getCustomerByEmail(String email) {
        Customers user = customerRepository.findByEmail(email);

        return user;
    }
}
