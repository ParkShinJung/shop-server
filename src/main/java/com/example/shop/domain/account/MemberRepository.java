package com.example.shop.domain.account;

import com.example.shop.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Page<Member> findAll(Specification<Member> memberSpecification, Pageable pageable);

    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findMemberIdByMemberId(String memberId);

    List<Member> findAll();

}
