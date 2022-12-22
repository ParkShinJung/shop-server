package com.example.shop.domain.info;

import com.example.shop.dto.common.RequestListDto;
import org.springframework.data.jpa.domain.Specification;

public class NoticeSpecification {

    public static Specification<Notice> getNoticeSpecification(RequestListDto requestListDto) {
        Specification<Notice> specifications = Specification.where(null);

        return specifications;
    }
}
