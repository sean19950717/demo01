package com.demo.common.constant;

/**
 * @author Tommy Huang
 * @email tommy.huang@demo.com
 * @date 2017/11/6 22:35
 */
public class JwtConstant {
    /**
     * jwt 加密key
     */
    public static final String SECRETKEY="sdsfgfrgeregfreg";
    /**
     * jwt web过期时间
     */
    public static final long JWT_WEB_EXPIRETIME=30*60*1000;
    /**
     * jwt restful接口过期时间
     */
    public static final long JWT_RESTFUL_EXPIRETIME=3600*1000*24*15;
}
