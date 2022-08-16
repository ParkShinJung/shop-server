package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class RequestRegisterReviewDto extends ResponseListDto {

    private String reviewId;

    private String reviewWriter;

    private String reviewQw;

    private String reviewTitle;

    private String reviewContent;

    private String reviewImg;

    // prod_id varchar(15) REFERENCES product(prod_id),
    // ord_id varchar(15) REFERENCES orders(ord_id),


}
