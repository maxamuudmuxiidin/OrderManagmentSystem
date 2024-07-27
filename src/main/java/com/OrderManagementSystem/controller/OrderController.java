package com.OrderManagementSystem.controller;

import com.OrderManagementSystem.model.Order;
import com.OrderManagementSystem.model.Product;
import com.OrderManagementSystem.service.OrderService;
import com.OrderManagementSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "orders";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("order", new Order());
        model.addAttribute("products", products);
        return "order-form";
    }

    @PostMapping
    public String addOrder(@ModelAttribute Order order) {
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Order order = orderService.findOrderById(id);
        List<Product> products = productService.findAllProducts();
        model.addAttribute("order", order);
        model.addAttribute("products", products);
        return "order-form";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        order.setId(id);
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
