package com.mobilephone.seckill.controller;

import com.mobilephone.seckill.domain.SeckillUser;
import com.mobilephone.seckill.redis.RedisService;
import com.mobilephone.seckill.result.Result;
import com.mobilephone.seckill.service.SeckillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<SeckillUser> info(Model model, SeckillUser user) {
        return Result.success(user);
    }

}
