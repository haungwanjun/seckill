package com.mobilephone.seckill.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mobilephone.seckill.util.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderInfo {
    private Long id;
    private Long userId;
    private String userName;
    private Long goodsId;
    private String deliveryAddress;
    private String goodsName;
    private Integer goodsCount;
    private BigDecimal goodsPrice;
    private Integer orderChannel;
    private Integer status;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createDate;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date payDate;
}
