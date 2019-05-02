package com.mobilephone.seckill.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderPay {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
    private String sellerId;
    private String appId;
    private BigDecimal totalAmount;
    private String tradeStatus;
    private String subject;
    private String body;
    private String outTradeNo;
    private Date gmtCreate;
    private Date gmtPayment;
    private Date gmtRefund;
    private Date gmtClose;
    private String passbackParams;
}
