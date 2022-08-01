package com.example.shop.dto.accont;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterMemberDto {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberAddress1;
    private String memberAddress2;
    private String memberNumber;
    private String memberBirthday;
}
