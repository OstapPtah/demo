package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    public void addProduct(Product product){

        if (products == null){
            products= new ArrayList<>();
        }
        product.setCategory(this);
        products.add(product);
    }


}
