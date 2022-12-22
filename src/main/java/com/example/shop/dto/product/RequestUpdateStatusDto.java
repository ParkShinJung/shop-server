package com.example.shop.dto.product;

import com.example.shop.common.type.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateStatusDto {

    private ProductStatus productStatus;
}
