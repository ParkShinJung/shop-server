package com.example.shop.dto.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSelectorDto {
    private List<SelectorOption> options;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SelectorOption {
        private String value;
        private String text;
    }

}
