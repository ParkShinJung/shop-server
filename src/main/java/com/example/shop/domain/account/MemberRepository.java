package com.example.shop.domain.account;

import com.example.shop.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Page<Member>  findAll(Specification<Member> specification, Pageable pageable);

    Optional<Member> findByMemId(String memId);

    Optional<Member> findByMemberNo(String memberNo);

    Optional<Member> findMemberByMemberNo(String memberNo);
}
