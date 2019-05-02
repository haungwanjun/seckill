package com.mobilephone.seckill.dao;


import com.mobilephone.seckill.domain.OrderPay;
import com.mobilephone.seckill.domain.PayInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderPayDao {
    @Insert("insert into order_pay (user_id,goods_id,order_id,seller_id,app_id,total_amount,trade_status,subject,body,out_trade_no,gmt_create," +
            "gmt_payment,gmt_refund,gmt_close,passback_params)values(#{userId}, #{goodsId}, #{orderId},#{sellerId}, #{appId},#{totalAmount}, #{tradeStatus}, " +
            "#{subject},#{body}, #{outTradeNo}, #{gmtCreate},#{gmtPayment}, #{gmtRefund}, #{gmtClose}, #{passbackParams})")
    void insertOrderPay(OrderPay orderPay);


    @Select("select * from order_pay where out_trade_no= #{outTradeNo}")
    public OrderPay getOrderByOutTradeNo(String outTradeNo);

    @Update("update order_pay set trade_status = #{tradeStatus}, gmt_payment= #{gmtPayment} where out_trade_no = #{outTradeNo}")
    void setOrderByOutTradeNo(OrderPay orderPay);


    @Insert("insert into pay_info (user_id,order_id,pay_platform,platform_number,platform_status,create_time,update_time)values" +
            "(#{userId}, #{orderId},#{payPlatform}, #{platformNumber},#{platformStatus}, #{createTime},#{updateTime})")
    void insertPayInfo(PayInfo payInfo);
}
