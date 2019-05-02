package com.mobilephone.seckill.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/pay/alipay.properties")
@ConfigurationProperties(prefix = "alipay")
@Data
public class AliPayConfigBean {
    // 商户appid
    private String appId;
    // 收款支付宝账号Id
    private String sellerId;
    // 私钥 pkcs8格式的
    private String privateKey;
    // 支付宝公钥
    private String publicKey;
    //网站域名
    private String domain;
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notify_url = "http://47.105.160.177:8080/";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，
    // 必须外网可以正常访问 商户可以自定义同步跳转地址
    private String return_url = "http://47.105.160.177:8080/";
    // 请求网关地址
    private String serverUrl = "https://openapi.pay.com/gateway.do";
    // 日志记录目录
    private String log_path = "/log";
    // RSA2
    private String SIGNTYPE = "RSA2";
    // 编码
    private String CHARSET = "UTF-8";
    // 返回格式
    private String FORMAT = "json";

    @Override
    public String toString() {
        return "AliPayConfig [appId=" + appId + ", sellerId=" + sellerId +", privateKey=" + privateKey + ", publicKey=" + publicKey + ", serverUrl="
                + serverUrl + ", domain=" + domain + ", log_path=" + log_path+", SIGNTYPE=" + SIGNTYPE+", CHARSET=" + CHARSET+
                ", FORMAT=" + FORMAT+", notify_url=" + notify_url+", return_url=" + return_url+"]";
    }
}
