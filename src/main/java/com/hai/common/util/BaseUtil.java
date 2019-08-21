package com.hai.common.util;


import com.hai.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @Author: admin
 * @Description:基础工具类
 * @Version: 1.0.0
 * @Date; 2017-12-20 10:53
 */
public class BaseUtil {
    private static final Logger logger = LoggerFactory.getLogger(BaseUtil.class);
    // 验证码字符集
    private static final char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * @param size 随机数长度
     * @Author: admin
     * @Description: 获取随机数
     * @Version: 1.0.0
     * @Date; 2018/4/25 16:12
     * @return: java.lang.String
     */
    public static String getRandomNumber(int size) {
        StringBuffer sb = new StringBuffer();
        Random ran = new Random();
        for (int i = 0; i < size; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 记录字符
            sb.append(chars[n]);
        }
        return sb.toString();
    }


    /**
     * @param request
     * @Author: admin
     * @Description: nginx代理后获取ip方法
     * @Version: 1.0.0
     * @Date; 2018/5/17 9:31
     * @return: java.lang.String
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        if (ip.contains(",")) {
            // 多个路由时，取第一个非unknown的ip
            final String[] arr = ip.split(",");
            for (final String str : arr) {
                if (!"unknown".equalsIgnoreCase(str)) {
                    ip = str;
                    break;
                }
            }
        }
        logIp(ip, request);
        return ip;
    }

    private static void logIp(String ip, HttpServletRequest request) {
        StringBuilder ipLog = new StringBuilder();
        ipLog.append("X-forwarded-for:").append(request.getHeader("x-forwarded-for")).append("; ");
        ipLog.append("Proxy-Client-IP:").append(request.getHeader("Proxy-Client-IP")).append("; ");
        ipLog.append("WL-Proxy-Client-IP:").append(request.getHeader("WL-Proxy-Client-IP")).append("; ");
        ipLog.append("HTTP_CLIENT_IP:").append(request.getHeader("HTTP_CLIENT_IP")).append("; ");
        ipLog.append("HTTP_X_FORWARDED_FOR:").append(request.getHeader("HTTP_X_FORWARDED_FOR")).append("; ");
        ipLog.append("X-Real-IP:").append(request.getHeader("X-Real-IP")).append("; ");
        ipLog.append("Request.getRemoteAddr:").append(request.getRemoteAddr()).append("; ");
        ipLog.append("Ip:").append(ip).append("; ");
        logger.info(ipLog.toString());
    }

    /**
     * @param data 待加密数据
     * @Author: admin
     * @Description: AES 加密
     * @Version: 1.0.0
     * @Date; 2018/4/23 17:19
     * @return: java.lang.String
     */
    public static String AesEncrypt(String data) {
        return com.caipiao.core.library.security.AESUtil.encrypt(data, Constants.AES_KEY);
    }

    /**
     * @param data 待解密数据
     * @Author: admin
     * @Description: AES 解密
     * @Version: 1.0.0
     * @Date; 2018/4/23 17:19
     * @return: java.lang.String
     */
    public static String AesDecrypt(String data) {
        return com.caipiao.core.library.security.AESUtil.decrypt(data, Constants.AES_KEY);
    }

}
