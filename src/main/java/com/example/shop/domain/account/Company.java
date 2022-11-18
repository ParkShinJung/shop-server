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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String companyPassword;

    @Column
    private String companyName;

    @Column
    private String ceoName;

    @Column
    private String contact;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String email;
}
