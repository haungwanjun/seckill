package com.mobilephone.seckill.redis;

public class SeckillKey extends BasePrefix {

    public static SeckillKey isGoodsOver = new SeckillKey(0, "go");
    public static SeckillKey getSeckillPath = new SeckillKey(60, "mp");
    public static SeckillKey getSeckillVerifyCode = new SeckillKey(300, "vc");
    private SeckillKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
