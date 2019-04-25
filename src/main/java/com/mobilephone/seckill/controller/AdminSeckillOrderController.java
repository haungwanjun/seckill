package com.mobilephone.seckill.controller;

import com.mobilephone.seckill.domain.Goods;
import com.mobilephone.seckill.domain.OrderInfo;
import com.mobilephone.seckill.domain.SeckillOrder;
import com.mobilephone.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class AdminSeckillOrderController {
    @Autowired
    OrderService orderService;


    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<OrderInfo> orderInfoList = orderService.listAllOrder();
        map.put("orderInfoList", orderInfoList);
        return new ModelAndView("order/list", map);
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "orderId") Long orderId,
                               Map<String, Object> map) {
        if (orderId != null) {
           OrderInfo orderInfo =  orderService.getOrderById(orderId);
           map.put("orderInfo",orderInfo);
           return new ModelAndView("order/detail", map);
        }
        return new ModelAndView("common/error", map);
    }
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") Long orderId,
                               Map<String, Object> map) {
        if (orderId != null) {
            orderService.deleteOrderById(orderId);
        }
        map.put("url", "/admin/order/list");
        return new ModelAndView("common/success", map);
    }

}
