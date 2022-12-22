package com.example.shop.dto.Info;

import com.example.shop.common.type.YesNo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterQnaDto {

    private String productId;
    private Long memberId;
    private String password;
    private String title;
    private String content;
    private String file;
    private YesNo secret;

}
