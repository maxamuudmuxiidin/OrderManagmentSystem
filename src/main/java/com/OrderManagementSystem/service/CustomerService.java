package com.OrderManagementSystem.service;

import com.OrderManagementSystem.model.Customer;
import com.OrderManagementSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
