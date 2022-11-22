package com.example.shop.domain.product;

import com.example.shop.domain.account.Member;
import com.example.shop.dto.common.RequestListDto;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> getProductSpecification(RequestListDto requestListDto) {
        Specification<Product> specifications = Specification.where(null);

        return specifications;
    }
}
