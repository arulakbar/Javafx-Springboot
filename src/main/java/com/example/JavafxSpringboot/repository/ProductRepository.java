package com.example.JavafxSpringboot.repository;

import com.example.JavafxSpringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByProductId(String id);
}
