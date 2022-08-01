package com.example.shop.domain.product;

import com.example.shop.dto.product.RequestProductListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> getProductListSpecification(RequestProductListDto requestProductListDto) {
        Specification<Product> specifications = Specification.where(null);

        if (StringUtils.isNotEmpty(requestProductListDto.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("prodTitle"), "%" + requestProductListDto.getKeyword() + "%")
            ).or(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("prodSubTitle"), "%" + requestProductListDto.getKeyword() + "%")
            );
        }

        return specifications;
    }
}
