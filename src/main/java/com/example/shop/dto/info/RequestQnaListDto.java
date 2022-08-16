package com.example.shop.dto.info;

import com.example.shop.dto.common.RequestListDto;
import lombok.*;

@EqualsAndHashCode(callSuper=false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestQnaListDto extends RequestListDto {

    private String prodId;
    private String memId;
}
