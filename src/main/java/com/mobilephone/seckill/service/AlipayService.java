package com.mobilephone.seckill.service;

import com.alipay.api.domain.AlipayTradePagePayModel;
import com.mobilephone.seckill.converter.StringToDateConverter;
import com.mobilephone.seckill.dao.OrderPayDao;
import com.mobilephone.seckill.domain.AliPayConfigBean;
import com.mobilephone.seckill.domain.OrderInfo;
import com.mobilephone.seckill.domain.OrderPay;
import com.mobilephone.seckill.domain.PayInfo;
import com.mobilephone.seckill.enums.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@Service
public class AlipayService {


    @Autowired
    OrderPayDao orderPayDao;

    StringToDateConverter stringToDateConverter;

    @Transactional
    public OrderPay createPayOrder(OrderInfo orderInfo, AlipayTradePagePayModel model, AliPayConfigBean aliPayConfigBean) {
        OrderPay orderPay = new OrderPay();
        orderPay.setOrderId(orderInfo.getId());
        orderPay.setUserId(orderInfo.getUserId());
        orderPay.setOutTradeNo(model.getOutTradeNo());
        orderPay.setBody(model.getBody());
        orderPay.setGoodsId(orderInfo.getGoodsId());
        TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(tz);
        orderPay.setGmtCreate(new Date());
        orderPay.setPassbackParams(model.getPassbackParams());
        orderPay.setSubject(model.getSubject());
        orderPay.setTotalAmount(BigDecimal.valueOf(Double.valueOf(model.getTotalAmount())));
        orderPay.setTradeStatus(Const.AlipayCallback.TRADE_STATUS_WAIT_BUYER_PAY);
        orderPay.setSellerId(aliPayConfigBean.getSellerId());
        orderPay.setAppId(aliPayConfigBean.getAppId());
        orderPayDao.insertOrderPay(orderPay);
        return orderPay;
    }

    public OrderPay getOrderByOutTradeNo(String outTradeNo) {
       return  orderPayDao.getOrderByOutTradeNo(outTradeNo);
    }


    /**
     * 这个是回调地址之后更新一些订单状态的方法
     * @param params
     * @return {boolean}
     */
//    @Transactional
//    public boolean checkAgain(Map<String, String> params) {
//        //订单号
//        Long outTradeNo = Long.parseLong(params.get("out_trade_no"));
//        //支付宝的交易号
//        String tradeNo = params.get("trade_no");
//        //支付宝的交易状态
//        String tradeStatus = params.get("trade_status");
//        //买家支付宝Id
//        String sellerId = params.get("seller_id");
//        //AppId
//        String appId = params.get("app_id");
//        //总金额
//        BigDecimal totalAmount = BigDecimal.valueOf(Double.valueOf(params.get("total_amount")));
//        //查询订单是否存在
//        OrderPay orderPay = orderPayDao.getOrderByOutTradeNo(outTradeNo.toString());
////        if(orderPay == null || orderPay.getTotalAmount().compareTo(totalAmount) != 0 || !orderPay.getSellerId().equals(sellerId)
////        || !orderPay.getAppId().equals(appId) ||orderPay.getTradeStatus().equals(Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS)){
////            return false;
////        }
//        //这里调用的是支付宝官方的枚举
//        //“TRADE_STATUS_TRADE_SUCCESS”是交易完成的意思
//        if(Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS.equals(tradeStatus)){
//            //记录交易的时间，这个字符串是固定的
//            orderPay.setTradeStatus(Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS);
//            orderPay.setGmtPayment(stringToDateConverter.convert(params.get("gmt_payment")));
//            orderPayDao.setOrderByOutTradeNo(orderPay);
//            //把订单状态改成已付款
//        }
//        PayInfo payInfo = new PayInfo();
//        payInfo.setUserId(orderPay.getUserId());
//        payInfo.setOrderId(orderPay.getOrderId());
//        payInfo.setPayPlatform(Const.PayPlatformEnum.ALIPAY.getCode());
//        payInfo.setPlatformNumber(tradeNo);
//        payInfo.setPlatformStatus(tradeStatus);
//        payInfo.setCreateTime(orderPay.getGmtPayment());
//        orderPayDao.insertPayInfo(payInfo);
//        return true;
//    }

    public void setOrderByOutTradeNo(OrderPay orderPay) {
        orderPayDao.setOrderByOutTradeNo(orderPay);
    }

    public void insertPayInfo(PayInfo payInfo) {
        orderPayDao.insertPayInfo(payInfo);
    }
}
