package com.mobilephone.seckill.vo;


import com.mobilephone.seckill.domain.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;

}
