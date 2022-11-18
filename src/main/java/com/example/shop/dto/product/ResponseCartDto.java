package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCartDto {

    private String cartId;
    private String member;
    private List<ProductItem> productItems;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductItem {
        private String prodId;
        private String prodTitle;
        private Integer cartQuantity;
        private Integer prodPice;
        private String prodMainImg;
        private Integer prodCount;
    }
}
