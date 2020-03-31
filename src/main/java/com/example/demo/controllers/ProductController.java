package com.example.demo.controllers;

import com.example.demo.models.PagedProductsResponse;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<PagedProductsResponse> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                                         @RequestParam(defaultValue = "id") String sortBy) {
        return productService.findAll(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/test")
    public Page findPage(@RequestParam(defaultValue = "0") Integer pageNumber,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "id") String sortBy) {
        return productService.getPage(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/products/{id}")
    public Product updateById(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateById(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
