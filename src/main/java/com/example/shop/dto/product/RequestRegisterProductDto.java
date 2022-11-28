package com.example.shop.dto.product;

import com.example.shop.common.type.ProductStatus;
import com.example.shop.domain.common.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterProductDto {

    private String title;
    private String subtitle;
    private Long price;
    private Long stock;
    private Long count;
    private String weight;
    private String mainImg;
    private String subImg;
    private Long discountRate;
    private Long discountPrice;
    private ProductStatus productStatus;
    private String categoryId;

}
