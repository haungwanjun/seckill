package com.mobilephone.seckill.domain;

import lombok.Data;

import java.util.Date;


@Data
public class PayInfo {
    private Long id;

    private Long userId;

    private Long orderId;

    private String payPlatform;

    private Integer platformNumber;

    private String platformStatus;

    private Date createTime;

    private Date updateTime;

}
