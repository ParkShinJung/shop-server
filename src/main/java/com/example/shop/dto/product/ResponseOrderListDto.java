package com.example.shop.dto.product;

import com.example.shop.common.type.OrderStatus;
import com.example.shop.domain.product.OrdersProduct;
import com.example.shop.domain.product.Product;
import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ResponseOrderListDto extends ResponseListDto {

    private List<OrdersItems> ordersItemsList;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrdersItems {

        private String ordId;
        private String memberId;
        private String memberName;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime orderDate;
        private OrderStatus status;
        private String name;
        private String contact;
        private String address1;
        private String address2;
        private String zipcode;
        private String payment;
        private List<OrdersProduct> ordersProducts;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrdersProduct {
        private Long id;
        private String productId;
        private String productName;
        private Long amount;
    }
}
