package com.example.shop.dto.Info;

import com.example.shop.common.type.QnaStatus;
import com.example.shop.common.type.YesNo;
import com.example.shop.domain.info.Notice;
import com.example.shop.domain.product.Product;
import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ResponseQnaListDto extends ResponseListDto {

    private List<QnaItems> noticeItems;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QnaItems {
        private String productId;
        private String productName;
        private String memberId;
        private String memberName;
        private String password;
        private String title;
        private String content;
        private String file;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime regDateTime;
        private YesNo secret;
        private QnaStatus qnaStatus;
    }
}
