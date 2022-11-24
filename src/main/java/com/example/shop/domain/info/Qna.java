package com.example.shop.domain.info;

import com.example.shop.common.type.QnaStatus;
import com.example.shop.common.type.YesNo;
import com.example.shop.common.util.StringPrefixedSequenceIdGenerator;
import com.example.shop.domain.account.Member;
import com.example.shop.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qna_id")
    @GenericGenerator(
            name = "qna_id",
            strategy = "com.example.shop.common.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "QNA"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(length = 8)
    private String qnaId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String password;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String file;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDateTime;

    @Column(columnDefinition = "ENUM('Y', 'N') DEFAULT 'Y'")
    @Enumerated(EnumType.STRING)
    private YesNo secret;

    @Column(columnDefinition = "ENUM('BEFORE', 'AFTER') DEFAULT 'BEFORE'")
    @Enumerated(EnumType.STRING)
    private QnaStatus qnaStatus;
}
