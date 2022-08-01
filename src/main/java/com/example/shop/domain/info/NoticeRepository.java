package com.example.shop.domain.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, String> {

    Page<Notice> findAll(Specification<Notice> specification, Pageable pageable);
}
