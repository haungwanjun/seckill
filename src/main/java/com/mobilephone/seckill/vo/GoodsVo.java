package com.mobilephone.seckill.vo;


import com.mobilephone.seckill.domain.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsVo extends Goods {
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
