package com.mobilephone.seckill.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mobilephone.seckill.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillGoods {
    private Long id;
    private Long goodsId;
    private BigDecimal seckillPrice;
    private Integer stockCount;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date startDate;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endDate;
}
