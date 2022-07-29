package com.example.shop.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestListDto {

    private int page = 1;
    private int pageSize = 10;
    private int start = 0;

    private String sort = "regDate";
    private Sort.Direction direction = Sort.Direction.DESC;

    private String searchType = "";
    private String keyword = "";

    private String param;

    public int getPage() {
        page = page - 1;
        if (page < 0) {
            page = 0;
        }
        return page;
    }

    public int getStart() {
        start = (page - 1) * pageSize;
        return start;
    }
}
