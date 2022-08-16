package com.example.shop.domain.product;

import com.example.shop.dto.product.RequestReviewListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {

    public static Specification<Review> getReviewListSpecification(RequestReviewListDto requestReviewListDto) {
        Specification<Review> specifications = Specification.where(null);

        if (StringUtils.isNotEmpty(requestReviewListDto.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("reviewTitle"), "%" + requestReviewListDto.getKeyword() + "%")
            ).or(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("reviewContent"), "%" + requestReviewListDto.getKeyword() + "%")
            );
        }

        if (StringUtils.isNotEmpty(requestReviewListDto.getProdId())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("prodId"),requestReviewListDto.getProdId())
            );
        }

        return specifications;
    }
}
