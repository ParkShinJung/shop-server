package com.example.shop.domain.info;

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
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "qna"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column
    private String qnaId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;

    @Column
    private String qnaPw;

    @Column
    private String qnaTitle;

    @Column
    private String qnaContent;

    @Column
    private String qnaFile;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime qnaRegDate;

    @Column(columnDefinition = "ENUM('Y', 'N') DEFAULT 'Y'")
    @Enumerated(EnumType.STRING)
    private YesNo qnaSecret;
}
