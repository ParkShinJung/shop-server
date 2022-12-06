package com.example.shop.domain.common;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {


    Optional<Category> findByCategoryId(String categoryId);
}
