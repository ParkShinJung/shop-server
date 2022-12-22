package com.example.shop.dto.Info;

import com.example.shop.dto.account.ResponseMemberListDto;
import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ResponseNoticeListDto extends ResponseListDto {

    private List<NoticeItems> memberItemsList;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeItems {

        private String noticeId;
        private String writer;
        private String password;
        private String title;
        private String content;
        private String file;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime regDateTime;

    }
}
