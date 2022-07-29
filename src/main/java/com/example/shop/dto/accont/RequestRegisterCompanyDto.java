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
    private String companyId;
    private String companyPw;
    private String companyName;
    private String companyCeo;
    private String companyNumber;
    private String companyAddress1;
    private String companyAddress2;
    private String companyEmail;
}
