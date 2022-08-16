package com.example.shop.domain.info;

import com.example.shop.domain.account.Member;
import com.example.shop.dto.accont.RequestMemberListDto;
import com.example.shop.dto.info.RequestQnaListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class QnaSpecification {

    public static Specification<Qna> getQnaListSpecification(RequestQnaListDto requestDto) {
        Specification<Qna> specifications = Specification.where(null);

        if (StringUtils.isNotEmpty(requestDto.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("qnaTitle"), "%" + requestDto.getKeyword() + "%")
            );
        }

        if (StringUtils.isNotEmpty(requestDto.getProdId())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("product").get("prodId"), requestDto.getProdId())
            );
        }

        if (StringUtils.isNotEmpty(requestDto.getMemId())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("member").get("memId"), requestDto.getMemId())
            );
        }

        return specifications;
    }
}
