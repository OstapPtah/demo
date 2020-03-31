package com.example.demo.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) {

        super("Category not found! id: " + id);
    }
}
