package com.mobilephone.seckill.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.mobilephone.seckill.domain.AliPayConfigBean;
import com.mobilephone.seckill.util.StringUtils;

import java.io.Serializable;

public class AlipayConfig implements Serializable {
    private static final long serialVersionUID = 6608790305598458742L;
    
    private String privateKey;
    private String alipayPublicKey;
    private String appId;
    private String serviceUrl;
    private String charset;
    private String signType;
    private String format;
    private AlipayClient alipayClient;

    private AlipayConfig() {
    }

    public static AlipayConfig New() {
        return new AlipayConfig();
    }

    public AlipayConfig build(){
        this.alipayClient = new DefaultAlipayClient(getServiceUrl(), getAppId(), getPrivateKey(), getFormat(), getCharset(), getAlipayPublicKey(),getSignType());
        return this;
    }

    public String getPrivateKey() {
        if (StringUtils.isBlank(privateKey))
            throw new IllegalStateException("privateKey 未被赋值");
        return privateKey;
    }
    public AlipayConfig setPrivateKey(String privateKey) {
        if (StringUtils.isBlank(privateKey))
            throw new IllegalArgumentException("privateKey 值不能为 null");
        this.privateKey = privateKey;
        return this;
    }

    public String getAlipayPublicKey() {
        if (StringUtils.isBlank(alipayPublicKey))
            throw new IllegalStateException("alipayPublicKey 未被赋值");
        return alipayPublicKey;
    }
    public AlipayConfig setAlipayPublicKey(String alipayPublicKey) {
        if (StringUtils.isBlank(alipayPublicKey))
            throw new IllegalArgumentException("alipayPublicKey 值不能为 null");
        this.alipayPublicKey = alipayPublicKey;
        return this;
    }

    public String getAppId() {
        if (StringUtils.isBlank(appId))
            throw new IllegalStateException("appId 未被赋值");
        return appId;
    }
    public AlipayConfig setAppId(String appId) {
        if (StringUtils.isBlank(appId))
            throw new IllegalArgumentException("appId 值不能为 null");
        this.appId = appId;
        return this;
    }

    public String getServiceUrl() {
        if (StringUtils.isBlank(serviceUrl))
            throw new IllegalStateException("serviceUrl 未被赋值");
        return serviceUrl;
    }


    public AlipayConfig setServiceUrl(String serviceUrl) {
        if (StringUtils.isBlank(serviceUrl))
            serviceUrl = "https://openapi.alipay.com/gateway.do";
        this.serviceUrl = serviceUrl;
        return this;
    }

    public String getCharset() {
        if (StringUtils.isBlank(charset))
            charset = "UTF-8";
        return charset;
    }

    public AlipayConfig setCharset(String charset) {
        if (StringUtils.isBlank(charset))
            charset = "UTF-8";
        this.charset = charset;
        return this;
    }

    public String getSignType() {
        if (StringUtils.isBlank(signType))
            signType = "RSA2";
        return signType;
    }

    public AlipayConfig setSignType(String signType) {
        if (StringUtils.isBlank(signType))
            signType = "RSA2";
        this.signType = signType;
        return this;
    }

    public String getFormat() {
        if (StringUtils.isBlank(format))
            format = "json";
        return format;
    }

    public AlipayClient getAlipayClient() {
        if (alipayClient == null)
            throw new IllegalStateException("alipayClient 未被初始化");
        return alipayClient;
    }

    public AlipayConfig setConfigProperties(AliPayConfigBean aliPayConfigBean) {
        this.setAppId(aliPayConfigBean.getAppId())
                .setAlipayPublicKey(aliPayConfigBean.getPublicKey())
                .setCharset("UTF-8")
                .setPrivateKey(aliPayConfigBean.getPrivateKey())
                .setServiceUrl(aliPayConfigBean.getServerUrl())
                .setSignType("RSA2");
        return this;
    }
}
