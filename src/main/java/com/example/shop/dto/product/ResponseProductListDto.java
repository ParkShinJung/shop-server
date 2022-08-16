package com.example.shop.dto.product;

import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class ResponseProductListDto extends ResponseListDto {
    private List<ProductListItem> productItems;

    @Data
    @SuperBuilder
    public static class ProductListItem {
        private String prodId;

        private String prodTitle;


        private String prodSubtitle;


        private Integer prodPrice;


        private Integer prodStock;


        private Integer prodCount;


        private Integer prodWeight;


        private String prodMainImg;


        private String prodSubImg;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime prodRegDate;
    }

}
