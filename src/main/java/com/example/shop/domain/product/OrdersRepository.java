package com.example.shop.domain.product;

import com.example.shop.dto.product.OrdersItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, String> {

    Page<Orders> findAll(Specification<Orders> ordersSpecificationSpecification, Pageable pageable);

    Optional<Orders> findByOrdId(String ordId);

    Page<Orders> findOrdersByMember_MemberId(String memberId, Pageable pageable);

    @Query(value = "SELECT " +
            "    od.ord_id AS ordId, " +
            "    od.address1 AS address1, " +
            "    od.address2 AS address2, " +
            "    od.contact AS contact, " +
            "    od.name AS name, " +
            "    od.order_date AS ordDate, " +
            "    od.payment AS payment, " +
            "    od.status AS status, " +
            "    od.zipcode AS zipcode, " +
            "    od.member_id AS memberId, " +
            "    odp.id AS id, " +
            "    odp.amount AS amount, " +
            "    odp.product_id AS productId " +
            "FROM orders od " +
            "JOIN orders_product odp ON od.ord_id = odp.ord_id " +
            "WHERE odp.product_id = ?1 " +
            "ORDER BY od.order_date ",
            nativeQuery = true)
    List<OrdersItem> getOrdersListByProductId(String productId);

}
