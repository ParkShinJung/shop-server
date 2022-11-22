package com.example.shop.domain.product;

import com.example.shop.common.util.StringPrefixedSequenceIdGenerator;
import com.example.shop.domain.info.Notice;
import com.example.shop.domain.info.Qna;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
    @GenericGenerator(
            name = "product_id",
            strategy = "com.example.shop.common.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PD"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
            })
    @Column(length = 8)
    private String productId;


    @Column
    private String title;

    @Column
    private String subtitle;

    @Column
    private Long price;     //정가

    @Column
    private Long discountPrice;     //할인적용된가격

    @Column
    private Long stock;

    @Column
    private Long count;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDateTime;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;

    @Column
    private Integer weight;

    @Column
    private String mainImg;

    @Column
    private String subImg;

/*    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private List<Notice> notices;*/

    @Column
    private Long discountRate;
}
