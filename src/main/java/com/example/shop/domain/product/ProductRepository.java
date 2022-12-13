package com.example.shop.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByProductId(String productId);

    Page<Product> findByCategory_CategoryId(String categoryId, Pageable pageable);


    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);
}
