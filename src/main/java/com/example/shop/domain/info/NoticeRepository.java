package com.example.shop.domain.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, String> {

    Page<Notice> findAll(Specification<Notice> noticeSpecification, Pageable pageable);

    Optional<Notice> findByNoticeId(String noticeId);

}
