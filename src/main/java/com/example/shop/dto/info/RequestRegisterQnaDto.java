package com.example.shop.dto.info;

import com.example.shop.common.type.YesNo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterQnaDto {
    private String qnaWriter;
    private String qnaPw;
    private String qnaTitle;
    private String qnaContent;
    private String qnaFile;
    private String qnaRegDate;
    private YesNo qnaSecret;
    private String prodId;
}
