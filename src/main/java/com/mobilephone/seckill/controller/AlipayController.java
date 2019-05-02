package com.mobilephone.seckill.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mobilephone.seckill.access.AccessLimit;
import com.mobilephone.seckill.alipay.AlipayConfig;
import com.mobilephone.seckill.alipay.AlipayUtils;
import com.mobilephone.seckill.converter.StringToDateConverter;
import com.mobilephone.seckill.domain.*;
import com.mobilephone.seckill.enums.Const;
import com.mobilephone.seckill.service.AlipayService;
import com.mobilephone.seckill.service.OrderService;
import com.mobilephone.seckill.service.SeckillUserService;
import com.mobilephone.seckill.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@Controller
@RequestMapping("/alipay")
@Slf4j
public class AlipayController extends AlipayConfigController {


    @Autowired
    OrderService orderService;
    StringToDateConverter stringToDateConverter;
    @Autowired
    AliPayConfigBean aliPayConfigBean;
    @Autowired
    SeckillUserService seckillUserService;

    @Autowired
    AlipayService alipayService;
    @Override
    public AlipayConfig getAliPayConfig() {
        return AlipayConfig.New().setConfigProperties(aliPayConfigBean).build();
    }

    @RequestMapping(value = "/pcpay", method = RequestMethod.GET)
    @ResponseBody
    @AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
    public void startPay(HttpServletResponse response, @RequestParam("orderId") Long orderId) {
       // Long orderId = Long.valueOf("13");
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = orderService.getOrderById(orderId);
        }
        //create pay order and insert into DB

        try {
            //商户订单号
            String outTradeNo = StringUtils.getOutTradeNo();
            //订单金额
            String totalAmount = orderInfo.getGoodsPrice().toString();

            log.info("pcPay outTradeNo--->" + outTradeNo);
            //返回地址
            String returnUrl = aliPayConfigBean.getDomain() + "/alipay/return_url";
            //异步通知地址
            String notifyUrl = aliPayConfigBean.getDomain() + "/alipay/notify_url";

            String subject = orderInfo.getGoodsName();
            String body = orderInfo.getDeliveryAddress();
            AlipayTradePagePayModel model = new AlipayTradePagePayModel();
            model.setOutTradeNo(outTradeNo);
            model.setProductCode("FAST_INSTANT_TRADE_PAY");
            model.setTotalAmount(totalAmount);
            model.setSubject(subject);
            model.setBody(body);
            model.setPassbackParams("passback_params");
            OrderPay orderPay = alipayService.createPayOrder(orderInfo, model,aliPayConfigBean);

            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setBizModel(model);
            request.setNotifyUrl(notifyUrl);
            request.setReturnUrl(returnUrl);
            AlipayUtils.tradePayPage(response, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/return_url")
    @ResponseBody
    public ModelAndView return_url(HttpServletRequest request,Map<String, Object> map) {
        Map<String, String> params = AlipayUtils.toMap(request);
        OrderPay orderPay = alipayService.getOrderByOutTradeNo(params.get("out_trade_no"));
        SeckillUser user = seckillUserService.getById(orderPay.getUserId());
        //记录交易的时间，这个字符串是固定的
        orderPay.setTradeStatus(Const.AlipayCallback.TRADE_STATUS_TRADE_SUCCESS);
        TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(tz);
        orderPay.setGmtPayment(new Date());
        alipayService.setOrderByOutTradeNo(orderPay);
        OrderInfo orderInfo = orderService.getOrderById(orderPay.getOrderId());
        orderInfo.setStatus(1);
        orderInfo.setPayDate(orderPay.getGmtPayment());
        orderService.updateOrderInfoById(orderInfo);
        PayInfo payInfo = new PayInfo();
        payInfo.setCreateTime(orderPay.getGmtCreate());
        payInfo.setOrderId(orderPay.getOrderId());
        payInfo.setUserId(orderPay.getUserId());
        payInfo.setPayPlatform(Const.PayPlatformEnum.ALIPAY.getValue());
        payInfo.setPlatformNumber(Const.PayPlatformEnum.ALIPAY.getCode());
        payInfo.setPlatformStatus(Const.PaymentTypeEnum.ONLINE_PAY.getValue());
        payInfo.setUpdateTime(orderPay.getGmtPayment());
        alipayService.insertPayInfo(payInfo);
        map.put("payInfo",payInfo);
        map.put("orderPay",orderPay);
        map.put("user",user);
        return new ModelAndView("pay/orderpay_detail", map);
    }

//    @RequestMapping(value = "/return_url")
//    public String return_url(HttpServletRequest request) {
//        Map<String, String> params = AlipayUtils.toMap(request);
//        OrderPay orderPay = alipayService.getOrderByOutTradeNo(params.get("out_trade_no"));
//        OrderPayVo orderPayVo = new OrderPayVo();
//        orderPayVo.setOrderPay(orderPay);
//        SeckillUser seckillUser =seckillUserService.getById(orderPay.getUserId());
//        //return Result.success(orderPayVo);
//        return "redirect:/order/detail?orderId="+orderPay.getOrderId();
//    }

    @RequestMapping(value = "/notify_url")
    @ResponseBody
    public String notify_url(HttpServletRequest request) {
        Map<String, String> params = AlipayUtils.toMap(request);
        params.remove("sign_type");
        try {
            // 获取支付宝GET过来反馈信息
            boolean verify_result = AlipaySignature.rsaCheckV2(params,aliPayConfigBean.getPublicKey(),aliPayConfigBean.getCHARSET(),
                    aliPayConfigBean.getSIGNTYPE());

            if (verify_result) {// 验证成功
                // TODO 请在这里加上商户的业务逻辑程序代码 异步通知可能出现订单重复通知 需要做去重处理
                System.out.println("notify_url 验证成功succcess");
                return "success";
            } else {
                System.out.println("notify_url 验证失败");
                // TODO
                return "failure";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "failure";
        }
    }


}
