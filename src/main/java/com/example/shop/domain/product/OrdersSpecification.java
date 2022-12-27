package com.example.shop.domain.product;

import com.example.shop.dto.product.RequestOrderListDto;
import com.example.shop.dto.product.RequestProductListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class OrdersSpecification {
    public static Specification<Orders> getOrdersSpecification(RequestOrderListDto requestOrderListDto) {
        Specification<Orders> specifications = Specification.where(null);


        return specifications;
    }
}
