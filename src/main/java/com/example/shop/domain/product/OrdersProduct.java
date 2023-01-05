package com.example.shop.domain.product;

import com.example.shop.common.type.YesNo;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ordersProduct")
public class OrdersProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @ToStringExclude
    private Product product;

    @Column
    private Long amount;

    @Column
    private Long price;

    @Column
    private Long totalPrice;

    @Builder.Default
    @Column(columnDefinition = "ENUM('Y', 'N') default 'Y'")
    @Enumerated(EnumType.STRING)
    private YesNo discountOver3 = YesNo.N;
}
