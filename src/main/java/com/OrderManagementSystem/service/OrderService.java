package com.OrderManagementSystem.service;

import com.OrderManagementSystem.model.Order;
import com.OrderManagementSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
