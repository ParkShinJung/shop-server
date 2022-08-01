package com.example.shop.domain.account;

import com.example.shop.dto.accont.RequestMemberListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.Collection;

public class MemberSpecification {

    public static Specification<Member> getMemberListSpecification(RequestMemberListDto requestMemberListDto) {
        Specification<Member> specifications = Specification.where(null);

        if (StringUtils.isNotEmpty(requestMemberListDto.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("memId"), "%" + requestMemberListDto.getKeyword() + "%")
            ).or(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("memberName"), "%" + requestMemberListDto.getKeyword() + "%")
            );
        }

        return specifications;
    }
}
