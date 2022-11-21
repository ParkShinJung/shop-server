package com.example.shop.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterCompanyDto {

    private String companyName;
    private String ceoName;
    private String contact;
    private String address1;
    private String address2;
    private String email;
}
