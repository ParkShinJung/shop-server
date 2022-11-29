package com.example.shop.dto.product;

import com.example.shop.dto.common.RequestListDto;
import lombok.*;

@EqualsAndHashCode(callSuper=false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductListDto extends RequestListDto {

    private String categoryId;
    private String productId;
}
