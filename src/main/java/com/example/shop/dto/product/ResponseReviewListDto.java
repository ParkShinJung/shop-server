package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ResponseReviewListDto extends ResponseListDto {

    public List<ReviewItems> reviewItems;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewItems {
        private String reviewId;
        private String memberId;
        private String memberName;
        private String title;
        private String content;
        private String image;
        private String productId;
        private String productName;
        private Integer starRating;
        private LocalDateTime regDateTime;
        private LocalDateTime modDateTime;
    }
}
