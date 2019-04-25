package com.mobilephone.seckill.service;


import com.mobilephone.seckill.dao.OrderDao;
import com.mobilephone.seckill.domain.OrderInfo;
import com.mobilephone.seckill.domain.SeckillOrder;
import com.mobilephone.seckill.domain.SeckillUser;
import com.mobilephone.seckill.redis.OrderKey;
import com.mobilephone.seckill.redis.RedisService;
import com.mobilephone.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    RedisService redisService;

    public List<OrderInfo> listAllOrder() {
        return orderDao.getAllOrder();
    }

    public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
        //return orderDao.getseckillOrderByUserIdGoodsId(userId, goodsId);
        return redisService.get(OrderKey.getSeckillOrderByUidGid, "" + userId + "_" + goodsId, SeckillOrder.class);
    }

    public OrderInfo getOrderById(long orderId) {
        return orderDao.getOrderById(orderId);
    }


    @Transactional
    public OrderInfo createOrder(SeckillUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddress(user.getDeliveryAddress());
        orderInfo.setUserName(user.getNickname());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderDao.insert(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(user.getId());
        orderDao.insertSeckillOrder(seckillOrder);
        redisService.set(OrderKey.getSeckillOrderByUidGid, "" + user.getId() + "_" + goods.getId(), seckillOrder);
        return orderInfo;
    }

    public void deleteOrders() {
        orderDao.deleteOrders();
        orderDao.deleteSeckillOrders();
    }

    public void deleteOrderById(Long orderId) {
        orderDao.deleteOrderInfoById(orderId);
        orderDao.deleteSeckillOrderById(orderId);
    }
}
