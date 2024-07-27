package com.OrderManagementSystem.service;

import com.OrderManagementSystem.model.Product;
import com.OrderManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
