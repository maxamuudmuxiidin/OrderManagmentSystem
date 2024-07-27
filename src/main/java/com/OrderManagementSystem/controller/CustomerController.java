package com.OrderManagementSystem.controller;

import com.OrderManagementSystem.model.Customer;
import com.OrderManagementSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAllCustomers());
        return "customers";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customer.setId(id);  // Ensure the customer ID is set to avoid creating a new customer
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customers";
    }
}
