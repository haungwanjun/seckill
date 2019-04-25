package com.mobilephone.seckill.dao;

import com.mobilephone.seckill.domain.OrderInfo;
import com.mobilephone.seckill.domain.SeckillOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface OrderDao {

    @Select("select * from seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
    public SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, user_name, delivery_address, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId},#{userName}, #{deliveryAddress},  #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    public long insert(OrderInfo orderInfo);

    @Insert("insert into seckill_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    public int insertSeckillOrder(SeckillOrder seckillOrder);

    @Select("select * from order_info where id = #{orderId}")
    public OrderInfo getOrderById(@Param("orderId") long orderId);

    @Delete("delete from order_info")
    public void deleteOrders();

    @Delete("delete from seckill_order")
    public void deleteSeckillOrders();
    @Select("select * from order_info")
    public List<OrderInfo> getAllOrder();

    @Delete("delete from order_info where id= #{orderId}")
    void deleteSeckillOrderById(long orderId);
    @Delete("delete from seckill_order where order_id= #{orderId}")
    void deleteOrderInfoById(long orderId);
}
