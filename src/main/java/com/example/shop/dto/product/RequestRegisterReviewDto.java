package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterReviewDto {

    private String member;

    private String reviewQw;

    private String reviewTitle;

    private String reviewContent;

    private String reviewImg;

    private String order;

    private String prodId;

    // prod_id varchar(15) REFERENCES product(prod_id),
    // ord_id varchar(15) REFERENCES orders(ord_id),


}
