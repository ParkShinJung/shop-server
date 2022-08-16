package com.example.shop.dto.info;

import com.example.shop.common.type.YesNo;
import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class ResponseQnaListDto extends ResponseListDto {
    private List<QnaListItem> qnaItems;

    @Data
    @SuperBuilder
    public static class QnaListItem {

        private String qnaId;

        private String prodId;

        private String qnaWriter;

        private String qnaPw;

        private String qnaTitle;

        private String qnaContent;

        private String qnaFile;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime qnaRegDate;

        private YesNo qnaSecret;

    }
}
