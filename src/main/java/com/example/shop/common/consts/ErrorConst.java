package com.example.shop.common.consts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ErrorConst {
    // Common Error
    public static final ErrorCode INVALID_REQUEST_DATA = ErrorCode.builder()
            .code("ERR400")
            .message("Invalid request.")
            .build();

    public static final ErrorCode NOT_FOUND_USER = ErrorCode.builder()
            .code("ERR401")
            .message("User information not found.")
            .build();

    public static final ErrorCode  NO_PERMISSION = ErrorCode.builder()
            .code("ERR403")
            .message("You do not have permission.")
            .build();

    public static final ErrorCode DUPLICATE_REQUEST_DATA = ErrorCode.builder()
            .code("ERR408")
            .message("Duplicate request.")
            .build();

    public static final ErrorCode NOT_FOUND_DATA = ErrorCode.builder()
            .code("ERR404")
            .message("No information found.")
            .build();

    public static final ErrorCode NOT_FOUND_FARM = ErrorCode.builder()
            .code("ERR700")
            .message("No farm information found.")
            .build();

    public static final ErrorCode NOT_FOUND_LOT = ErrorCode.builder()
            .code("ERR701")
            .message("Zone information not found.")
            .build();

    public static final ErrorCode NOT_FOUND_CROP = ErrorCode.builder()
            .code("ERR702")
            .message("No crop information found.")
            .build();

    public static final ErrorCode NOT_FOUND_PLANTATION = ErrorCode.builder()
            .code("ERR720")
            .message("No plantation information found.")
            .build();

    public static final ErrorCode NOT_FOUND_FARM_LOG = ErrorCode.builder()
            .code("ERR710")
            .message("No log information found.")
            .build();

    public static final ErrorCode NOT_FOUND_FERTILIZER = ErrorCode.builder()
            .code("ERR711")
            .message("No fertilizer information found.")
            .build();

    public static final ErrorCode NOT_FOUND_PESTICIDE = ErrorCode.builder()
            .code("ERR712")
            .message("No pesticide information found.")
            .build();

    public static final ErrorCode NOT_FOUND_CURRICULUM = ErrorCode.builder()
            .code("ERR800")
            .message("There is no curriculum.")
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
