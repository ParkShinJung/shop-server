package com.example.shop.common.consts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ErrorCodeConst {

    // Account
    public static final ErrorCode NOT_FOUND_ACCOUNT = ErrorCode.builder()
            .code("E00006")
            .message("No user or accounts data.")
            .build();

    public static final ErrorCode NOT_FOUND_CROP = ErrorCode.builder()
            .code("E00007")
            .message("농장 정보를 찾을 수 없습니다.")
            .build();

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorCode {
        private String code;
        private String message;

        public String toErrorString() {
            return "[" + this.getCode() + "] " + this.getMessage().trim();
        }
    }
}
