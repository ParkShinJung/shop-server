package com.example.shop.domain.product;

import com.example.shop.common.type.OrderStatus;
import com.example.shop.common.util.StringPrefixedSequenceIdGenerator;
import com.example.shop.domain.account.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_id")
    @GenericGenerator(
            name = "ord_id",
            strategy = "com.example.shop.common.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ORD"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(length = 8)
    private String ordId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    @Builder.Default
    @Column(columnDefinition = "ENUM('order_confirmation', 'in_delivery', 'order_complete', 'order_cancel') DEFAULT 'order_confirmation'")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.order_confirmation;

    @Column
    private String name;

    @Column
    private String contact;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String zipcode;

//    @Column
//    private String amount;

    @Column
    private Long totalPayment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_id", referencedColumnName = "ordId")
    @ToString.Exclude
    private List<OrdersProduct> ordersProducts;

}
