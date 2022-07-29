package com.example.shop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterProductDto {
    private String productTitle;
    private String productSubtitle;
    private Integer productPrice;
    private Integer productStock;
    private Integer productCount;
    private Integer productWeight;
    private String productMainImg;
    private String productSubImg;
}
