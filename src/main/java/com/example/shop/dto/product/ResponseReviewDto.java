package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ResponseReviewDto extends ResponseListDto {

    private String reviewId;
    private String memberId;
    private String memberName;
    private String title;
    private String content;
    private String image;
    private String productId;
    private String productName;
    private LocalDateTime regDateTime;
    private LocalDateTime modDateTime;
}
