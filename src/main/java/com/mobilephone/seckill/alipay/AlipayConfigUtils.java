package com.mobilephone.seckill.alipay;

import com.mobilephone.seckill.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AlipayConfigUtils {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>();

    private static final Map<String, AlipayConfig> CONFIG_MAP = new ConcurrentHashMap<String, AlipayConfig>();
    private static final String DEFAULT_CONFIG_KEY = "_default_pay_key_";

    /**
     * 添加支付宝支付配置，每个appId只需添加一次，相同appId将被覆盖
     * @param alipayConfig 支付宝支付配置
     * @return {AlipayConfig} 支付宝支付配置
     */
    public static AlipayConfig setAlipayConfig(AlipayConfig alipayConfig) {
        if (CONFIG_MAP.size() == 0) {
            CONFIG_MAP.put(DEFAULT_CONFIG_KEY, alipayConfig);
        }
        return CONFIG_MAP.put(alipayConfig.getAppId(), alipayConfig);
    }

    public static AlipayConfig setThreadLocalAlipayConfig(AlipayConfig alipayConfig) {
        return setAlipayConfig(alipayConfig);
    }

    public static AlipayConfig removeAlipayConfig(AlipayConfig alipayConfig) {
        return removeAlipayConfig(alipayConfig.getAppId());
    }

    public static AlipayConfig removeAlipayConfig(String appId) {
        return CONFIG_MAP.remove(appId);
    }

    public static void setThreadLocalAppId(String appId) {
        if (StringUtils.isBlank(appId)) {
            appId = CONFIG_MAP.get(DEFAULT_CONFIG_KEY).getAppId();
        }
        THREAD_LOCAL.set(appId);
    }

    public static void removeThreadLocalAppId() {
        THREAD_LOCAL.remove();
    }

    public static String getAppId() {
        String appId = THREAD_LOCAL.get();
        if (StringUtils.isBlank(appId)) {
            appId = CONFIG_MAP.get(DEFAULT_CONFIG_KEY).getAppId();
        }
        return appId;
    }



    public static AlipayConfig getAlipayConfig() {
        String appId = getAppId();
        return getApiConfig(appId);
    }

    public static AlipayConfig getApiConfig(String appId) {
        AlipayConfig cfg = CONFIG_MAP.get(appId);
        if (cfg == null)
            throw new IllegalStateException("需事先调用 AlipayConfigKit.putApiConfig(AlipayConfig) 将 appId对应的 AlipayConfig 对象存入，" +
                    "才可以使用 AlipayConfigKit.getAlipayConfig() 的系列方法");
        return cfg;
    }
}
