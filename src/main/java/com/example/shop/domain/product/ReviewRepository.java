package com.example.shop.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {
    Page<Review> findAll(Specification<Review> specification, Pageable pageable);

    Optional<Review> findReviewByReviewId(String reviewId);
}
