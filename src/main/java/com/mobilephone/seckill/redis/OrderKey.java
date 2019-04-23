package com.mobilephone.seckill.redis;

public class OrderKey extends BasePrefix {

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("oug");

    public OrderKey(String prefix) {
        super(prefix);
    }
}
