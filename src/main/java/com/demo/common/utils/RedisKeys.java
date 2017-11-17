package com.demo.common.utils;

/**
 * Redis所有Keys
 *
 * @author Centling Technologies
 * @email xxx@demo.com
 * @date 2017-07-18 19:51
 */
public class RedisKeys {
    /**
     * 生成系统配置key
     * @param key
     * @return
     */
    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    /**
     * 生成shiro session key
     * @param key
     * @return
     */
    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }

    /**
     *  生成存放用户token的redis key
     * @param key
     * @return
     */
    public static String getUserWebTokenKey(String key){
        return "userwebtoken:"+key;
    }

}
