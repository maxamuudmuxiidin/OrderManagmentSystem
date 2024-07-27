package com.OrderManagementSystem.repository;

import com.OrderManagementSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
