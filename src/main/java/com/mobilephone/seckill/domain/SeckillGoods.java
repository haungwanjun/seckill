package com.mobilephone.seckill.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SeckillGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
