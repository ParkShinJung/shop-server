package com.example.shop.domain.info;

import com.example.shop.domain.account.Member;
import com.example.shop.dto.accont.RequestMemberListDto;
import com.example.shop.dto.info.RequestNoticeListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class NoticeSpecification {

    public static Specification<Notice> getNoticeListSpecification(RequestNoticeListDto requestNoticeListDto) {
        Specification<Notice> specifications = Specification.where(null);

        if (StringUtils.isNotEmpty(requestNoticeListDto.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("notWriter"), "%" + requestNoticeListDto.getKeyword() + "%")
            ).or(
                    (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("notTitle"), "%" + requestNoticeListDto.getKeyword() + "%")
            );
        }

        return specifications;
    }

}
