package com.example.shop.dto.accont;

import com.example.shop.dto.common.ResponseListDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
public class ResponseMemberListDto extends ResponseListDto {
    private List<MemberListItem> memItems;

    @Data
    @SuperBuilder
    public static class MemberListItem {
        private String memberNo;

        private String memId;

        private String memPw;

        private String memName;

        private String memAddress1;

        private String memAddress2;

        private String memNumber;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate memBirthday;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime memRegDate;
    }
}
