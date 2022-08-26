package com.example.shop.domain.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaRepository  extends JpaRepository<Qna, String> {

    Page<Qna> findAll(Specification<Qna> specification, Pageable pageable);

    Optional<Qna> findQnaByQnaId(String qnaId);

}
