package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class ResponseCartListDto extends ResponseListDto {

    private List<CartListItem> cartListItems;

    @Data
    @Builder
    public static class CartListItem {
        private String cartId;
        private String member;
        private String product;
        private Integer cartQuantity;
    }
}
