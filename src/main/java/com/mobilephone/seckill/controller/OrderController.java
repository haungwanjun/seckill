package com.mobilephone.seckill.controller;

import com.mobilephone.seckill.domain.OrderInfo;
import com.mobilephone.seckill.domain.SeckillUser;
import com.mobilephone.seckill.redis.RedisService;
import com.mobilephone.seckill.result.CodeMsg;
import com.mobilephone.seckill.result.Result;
import com.mobilephone.seckill.service.GoodsService;
import com.mobilephone.seckill.service.OrderService;
import com.mobilephone.seckill.service.SeckillUserService;
import com.mobilephone.seckill.vo.GoodsVo;
import com.mobilephone.seckill.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    SeckillUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, SeckillUser user,
                                      @RequestParam("orderId") long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }

}
