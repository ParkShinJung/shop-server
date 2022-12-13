package com.example.shop.dto;

import com.example.shop.common.type.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ResponseCheckAccountDto {
    @Builder.Default
    private boolean isAccountIdMatched = false;
    @Builder.Default
    private boolean isAccountPasswordMatched = false;
    @Builder.Default
    private boolean isAdminAccess = false;

    private long id;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
}
