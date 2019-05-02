package com.mobilephone.seckill.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.mobilephone.seckill.domain.AliPayConfigBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Michael
 * 2019-04-28
 */


public class AlipayUtils {



    /**
     * 电脑网站支付(PC支付)
     * @param response
     * @param request
     * @throws {AlipayApiException}
     * @throws IOException
     */
    public static void tradePayPage(HttpServletResponse response,AlipayTradePagePayRequest request) throws AlipayApiException, IOException{

        String form  = AlipayConfigUtils.getAlipayConfig().getAlipayClient().pageExecute(request).getBody();//调用SDK生成表单
        response.setContentType("text/html;charset=" + AlipayConfigUtils.getAlipayConfig().getCharset());
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }


    /**
     * 将异步通知的参数转化为Map
     * @param request
     * @return {Map<String, String>}
     */
    public static Map<String, String> toMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 检查支付宝反馈过来的信息
     * @param params
     * @param aliPayConfigBean
     * @return Boolean
     */
    public static Boolean checkBackInfo(Map<String, String> params, AliPayConfigBean aliPayConfigBean) throws AlipayApiException {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayConfigBean.getPublicKey(), "UTF-8",
                "RSA2");//调用SDK验证签名
        return verify_result;
    }


}
