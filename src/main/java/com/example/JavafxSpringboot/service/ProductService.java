package com.example.JavafxSpringboot.service;

import com.example.JavafxSpringboot.model.Product;
import com.example.JavafxSpringboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProduct(){
        return repository.findAll();
    }

    public void deleteProduct(Product product){
        repository.delete(product);
    }

    public void addProduct(Product product){
        repository.save(product);
    }

    public boolean isExist(String string){
        return repository.existsByProductId(string);
    }


}
