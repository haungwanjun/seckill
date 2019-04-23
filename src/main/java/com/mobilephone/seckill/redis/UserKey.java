package com.mobilephone.seckill.redis;

public class UserKey extends BasePrefix {

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
    private UserKey(String prefix) {
        super(prefix);
    }
}
