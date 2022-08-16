package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class ResponseReviewListDto extends ResponseListDto {

    private List<ReviewListItem> reviewListItems;

    @Data
    @Builder
    public static class ReviewListItem {


        private String reviewId;

        private String reviewWriter;

        private String reviewQw;

        private String reviewTitle;

        private String reviewContent;

        private String reviewImg;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime reviewRegDate;
    }
}
