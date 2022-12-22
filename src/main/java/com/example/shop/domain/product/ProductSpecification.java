package com.example.shop.domain.product;

import com.example.shop.domain.account.Member;
import com.example.shop.dto.common.RequestListDto;
import com.example.shop.dto.product.RequestProductListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> getProductSpecification(RequestProductListDto requestProductListDto) {
        Specification<Product> specifications = Specification.where(null);

        if (StringUtils.isNotEmpty(requestProductListDto.getCategoryId())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("category").get("categoryId"), requestProductListDto.getCategoryId())
            );
        }

        if (StringUtils.isNotEmpty(requestProductListDto.getProductId())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productId"), requestProductListDto.getProductId())
            );
        }

        if (StringUtils.isNotEmpty(requestProductListDto.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + requestProductListDto.getKeyword() + "%")
            )
                    .or(
                            (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("subTitle"), "%" + requestProductListDto.getKeyword() + "%")
                    );
        }

        return specifications;
    }
}
