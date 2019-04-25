package com.mobilephone.seckill.controller;


import com.mobilephone.seckill.converter.StringToDateConverter;
import com.mobilephone.seckill.domain.Goods;
import com.mobilephone.seckill.domain.SeckillGoods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {

    @GetMapping("/login")
    public String login() {
            return "/adminuser/login";
    }

    @RequestMapping(value = "/do_login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.equals("admin") && password.equals("admin")){
            return "redirect:/admin/order/list";
        }
        return "redirect:/admin/login";
    }
}
