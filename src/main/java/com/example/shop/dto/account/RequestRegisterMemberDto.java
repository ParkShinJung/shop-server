package com.example.shop.dto.account;

import com.example.shop.common.type.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestRegisterMemberDto {

    private String memberId;
    private String password;
    private String name;
    private String zipCode;
    private String address1;
    private String address2;
    private String contact;
    private String email;
    private AccountType accountType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;



}
