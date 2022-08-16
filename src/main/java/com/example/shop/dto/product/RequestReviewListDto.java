package com.example.shop.dto.product;

import com.example.shop.dto.common.RequestListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestReviewListDto extends RequestListDto {

    private String prodId;
}
