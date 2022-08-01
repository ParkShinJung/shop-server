package com.example.shop.dto.info;

import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class ResponseNoticeListDto extends ResponseListDto {
    private List<NoticeListItem> noticeItem;

    @Data
    @SuperBuilder
    public static class NoticeListItem {
        private String notId;

        private String notWriter;

        private String notPw;

        private String notTitle;

        private String notContent;

        private String notFile;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime notRegDate;
    }
}
