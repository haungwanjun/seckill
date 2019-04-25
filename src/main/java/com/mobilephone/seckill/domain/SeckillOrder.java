package com.mobilephone.seckill.domain;

import lombok.Data;

import java.util.List;

@Data
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;

}
