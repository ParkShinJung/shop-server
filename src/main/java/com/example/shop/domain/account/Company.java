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
    private String companyId;

    @Column
    private String companyPw;

    @Column
    private String companyName;

    @Column
    private String companyCeo;

    @Column
    private String companyNumber;

    @Column
    private String companyAddress1;

    @Column
    private String companyAddress2;

    @Column
    private String companyEmail;
}
