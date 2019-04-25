package com.mobilephone.seckill.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private BigDecimal goodsPrice;
    private Integer goodsStock;
    private Integer isSeckill;//是否设为秒杀
}
