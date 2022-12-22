package com.example.shop.dto.product;

import com.example.shop.domain.account.Member;
import com.example.shop.domain.product.Product;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterReviewDto {

    private String memberId;
    private String title;
    private String content;
    private String image;
    private String productId;
    private Integer starRating;
}
