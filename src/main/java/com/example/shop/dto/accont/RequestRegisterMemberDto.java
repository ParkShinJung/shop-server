package com.example.shop.dto.accont;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterMemberDto {
    private String memId;
    private String memPw;
    private String memName;
    private String memAddress1;
    private String memAddress2;
    private String memNumber;
    private String memBirthday;
}
