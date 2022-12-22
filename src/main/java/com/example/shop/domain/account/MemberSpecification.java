package com.example.shop.domain.account;

import com.example.shop.dto.common.RequestListDto;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecification {
    public static Specification<Member> getMemberSpecification(RequestListDto requestListDto) {
        Specification<Member> specifications = Specification.where(null);

        return specifications;
    }
}
