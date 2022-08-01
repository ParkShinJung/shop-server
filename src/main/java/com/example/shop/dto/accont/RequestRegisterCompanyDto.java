package com.example.shop.dto.accont;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterCompanyDto {
    private String comId;
    private String comPw;
    private String comName;
    private String comCeo;
    private String comNumber;
    private String comAddress1;
    private String comAddress2;
    private String comEmail;
}
