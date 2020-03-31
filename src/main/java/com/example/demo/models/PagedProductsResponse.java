package com.example.demo.models;

import lombok.Data;

import java.util.List;

@Data
public class PagedProductsResponse {
    private List<Product> products;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalRecords;
}