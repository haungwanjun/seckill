package com.mobilephone.seckill.access;

import com.mobilephone.seckill.domain.SeckillUser;

public class UserContext {
    //ThreadLocal 跟线程绑定的信息。线程安全
    private static ThreadLocal<SeckillUser> userHolder = new ThreadLocal<>();

    public static SeckillUser getUser() {
        return userHolder.get();
    }

    public static void setUser(SeckillUser user) {
        userHolder.set(user);
    }

}
