package com.example.demo.services;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService  {

    private CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category create(Category category){
        return categoryRepo.save(category);
    }

    public Category addProduct(Long id, Product product){
        return categoryRepo.findById(id).map(category ->{
            category.addProduct(product);
            return categoryRepo.save(category);
        }).orElseThrow(() -> new CategoryNotFoundException(id));

    }

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }
}
