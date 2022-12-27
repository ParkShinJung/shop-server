package com.example.shop.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, String> {

    Page<Orders> findAll(Specification<Orders> ordersSpecificationSpecification, Pageable pageable);
}
