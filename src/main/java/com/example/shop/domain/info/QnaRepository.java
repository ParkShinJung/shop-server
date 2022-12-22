package com.example.shop.domain.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaRepository  extends JpaRepository<Qna, String> {

    Page<Qna> findAll(Specification<Qna> qnaSpecification, Pageable pageable);

    Optional<Qna> findByQnaId(String qnaId);

    Page<Qna> findAllByProduct_ProductId(String productId, Pageable pageable);
    Page<Qna> findAllByMember_MemberId(String memberId, Pageable pageable);
}
