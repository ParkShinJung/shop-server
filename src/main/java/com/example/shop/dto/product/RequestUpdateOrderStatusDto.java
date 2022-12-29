package com.example.shop.dto.product;

import com.example.shop.common.type.OrderStatus;
import com.example.shop.common.type.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateOrderStatusDto {

    private OrderStatus status;

}
