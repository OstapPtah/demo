package com.example.demo.services;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.PagedProductsResponse;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ResponseEntity<PagedProductsResponse> findAll(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

        Page<Product> pagedResult = productRepo.findAll(paging);

        PagedProductsResponse response = new PagedProductsResponse();
        response.setProducts(pagedResult.getContent());
        response.setPageNumber(pagedResult.getPageable().getPageNumber());
        response.setPageSize(pagedResult.getPageable().getPageSize());
        response.setTotalRecords(pagedResult.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public Page getPage(Integer pageNumber, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return productRepo.findAll(paging);
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException());
    }

    public Product create(Product product) {
        return productRepo.save(product);
    }

    public Product updateById(Long id, Product updatedProduct) {
        return productRepo
                .findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    return productRepo.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException());
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }


}

