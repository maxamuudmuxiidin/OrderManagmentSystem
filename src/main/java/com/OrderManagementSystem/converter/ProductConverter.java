package com.OrderManagementSystem.converter;

import com.OrderManagementSystem.model.Product;
import com.OrderManagementSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<String, Product> {

    private final ProductService productService;

    @Autowired
    public ProductConverter(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Product convert(String source) {
        Long id = Long.valueOf(source);
        return productService.findProductById(id);
    }
}
