package com.example.shop.dto.product;

import com.example.shop.domain.account.Member;
import com.example.shop.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterReviewDto {

    private Long memberId;
    private String title;
    private String content;
    private String image;
    private String productId;
}
