package com.example.shop.dto.common;

import com.example.shop.common.type.ProductStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ResponseCategoryListDto {

    public List<CategoryItems> categoryItems;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryItems {

        private String categoryId;
        private String categoryName;
    }
}
