package com.example.shop.dto.product;

import com.example.shop.common.type.ProductStatus;
import com.example.shop.dto.common.ResponseListDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ResponseProductDto {

    private String productId;
    private String title;
    private String subtitle;
    private Long price;
    private Long stock;
    private Long count;
    private LocalDateTime regDateTime;
    private LocalDateTime modDate;
    private String weight;
    private String mainImg;
    private String subImg;
    private Long discountRate;
    private Long totalPrice;
    private ProductStatus productStatus;
    private String categoryId;
    private String categoryName;

}
