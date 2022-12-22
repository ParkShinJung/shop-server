package com.example.shop.domain.info;

import com.example.shop.dto.common.RequestListDto;
import org.springframework.data.jpa.domain.Specification;

public class QnaSpecification {

    public static Specification<Qna> getQnaSpecification(RequestListDto requestListDto) {
        Specification<Qna> specifications = Specification.where(null);

        return specifications;
    }
}
