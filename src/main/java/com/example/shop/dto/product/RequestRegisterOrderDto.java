package com.example.shop.dto.product;

import com.example.shop.common.type.YesNo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterOrderDto {
    private String memberId;
    private String name;
    private String contact;
    private String address1;
    private String address2;
    private String zipcode;
    private Long totalPayment;

    private List<ProductItems> productItem;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductItems {
        private String product;
        private Long amount;
        private Long price;
        private Long totalPrice;
        private YesNo discountOver3;
    }
}
