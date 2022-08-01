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
    private String prodTitle;
    private String prodSubtitle;
    private Integer prodPrice;
    private Integer prodStock;
    private Integer prodCount;
    private Integer prodWeight;
    private String prodMainImg;
    private String prodSubImg;
}
