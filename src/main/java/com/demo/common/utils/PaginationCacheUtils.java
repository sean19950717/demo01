package com.demo.common.utils;


import org.springframework.cache.Cache;

/**
 * 分页缓存工具类
 * @author Tommy Huang
 * @email tommy.huang@demo.com
 * @date 2017/11/9 15:01
 */
public class PaginationCacheUtils {
    private static Cache cache=null;
    /**
     * 存储的key value
     * @param key
     * @param value
     */
    public static void savePaginationCache(String key,String value){
        if (cache==null) {
            cache=SpringContextUtils.getBean("ehcacheUtils",EhcacheUtils.class).getpageResultCountCache();
        }
        cache.put(key,value);
    }

    /**
     * 获取key value
     * @param key
     * @return
     */
    public static  String getPaginationCache(String key){
        if (cache==null) {
            cache=SpringContextUtils.getBean("ehcacheUtils",EhcacheUtils.class).getpageResultCountCache();
        }
        return cache.get(key,String.class);
    }


}
