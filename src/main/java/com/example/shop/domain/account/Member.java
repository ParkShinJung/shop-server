package com.example.shop.domain.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @Column
    private String memId;

    @Column
    private String memPw;

    @Column
    private String memName;

    @Column
    private String memAddress1;

    @Column
    private String memAddress2;

    @Column
    private String memNumber;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memRegDate;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate memBirthday;
}
