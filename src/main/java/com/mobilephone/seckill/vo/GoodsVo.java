package com.mobilephone.seckill.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mobilephone.seckill.domain.Goods;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
