package com.example.shop.dto.product;

import java.time.LocalDateTime;

public interface OrdersItem {

    String getOrdId();
    String getAddress1();
    String getAddress2();
    String getContact();
    String getName();
    LocalDateTime getOrdDate();
    String getPayment();
    String getStatus();
    String getZipcode();
    String getMemberId();
    Long getId();
    Long getAmount();
    String getProductId();
}
