package com.example.shop.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterNoticeDto {

    private  String notWriter;
    private  String notPw;
    private  String notTitle;
    private  String notContent;
    private  String notFile;
    private  String notRegDate;
}
