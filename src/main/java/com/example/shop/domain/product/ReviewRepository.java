package com.example.shop.domain.product;

import com.example.shop.domain.info.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findAll(Pageable pageable);

    Page<Review> findAllByProduct_ProductId(String productId, Pageable pageable);

    Page<Review> findAllByMember_MemberId(String memberId, Pageable pageable);

    Optional<Review> findByReviewId(String reviewId);

}
