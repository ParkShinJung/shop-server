package com.example.shop.domain.account;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Company {

    @Id
    @Column
    private String comId;

    @Column
    private String comPw;

    @Column
    private String comName;

    @Column
    private String comCeo;

    @Column
    private String comNumber;

    @Column
    private String comAddress1;

    @Column
    private String comAddress2;

    @Column
    private String comEmail;
}
