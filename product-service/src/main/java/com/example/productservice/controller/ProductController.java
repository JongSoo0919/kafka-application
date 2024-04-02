package com.example.productservice.controller;

import com.example.productservice.domain.Products;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/product/detail")
    public List<Products> getProducts() {
        return productService.getProducts();
    }
}
