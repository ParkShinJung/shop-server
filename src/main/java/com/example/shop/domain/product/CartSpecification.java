package com.example.shop.domain.product;

import com.example.shop.dto.product.RequestCartListDto;
import com.example.shop.dto.product.RequestReviewListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class CartSpecification {

    public static Specification<Cart> getCartListSpecification(RequestCartListDto requestCartListDto) {
        Specification<Cart> specifications = Specification.where(null);

/*        if (StringUtils.isNotEmpty(requestCartListDto.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("reviewTitle"), "%" + requestCartListDto.getKeyword() + "%")
            ).or(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("reviewContent"), "%" + requestCartListDto.getKeyword() + "%")
            );
        }*/

        if (StringUtils.isNotEmpty(requestCartListDto.getCartId())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cartId"),requestCartListDto.getCartId())
            );
        }

        return specifications;
    }
}
