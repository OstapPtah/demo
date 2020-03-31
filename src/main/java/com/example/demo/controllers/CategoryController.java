package com.example.demo.controllers;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping("/categories/{id}/products")
    public Category addProduct(@PathVariable Long id, @RequestBody Product product){
        return categoryService.addProduct(id, product);
    }

    @GetMapping("/categories")
    public List<Category> findAll(){
      return categoryService.findAll();
    }

}
