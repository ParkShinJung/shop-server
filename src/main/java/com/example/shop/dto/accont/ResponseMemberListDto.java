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
    private List<MemberListItem> memberItems;

    @Data
    @SuperBuilder
    public static class MemberListItem {
        private String memberId;

        private String memberPw;

        private String memberName;

        private String memberAddress1;

        private String memberAddress2;

        private String memberNumber;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate memberBirthday;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime memberRegDate;
    }
}
