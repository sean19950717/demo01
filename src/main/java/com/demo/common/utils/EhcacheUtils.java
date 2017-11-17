package com.demo.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Tommy Huang
 * @email tommy.huang@demo.com
 * @date 2017/11/6 13:25
 */
@Component
@Scope("singleton")
public class EhcacheUtils {
    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;

    /**
     * 根据cache名称获得cache
     * @param cacheName cache名称
     * @return
     */
    public Cache getCacheByName(String cacheName){
        return  ehCacheCacheManager.getCache(cacheName);
    }

    /**
     * 获取登录限制缓存
     * @return
     */
    public Cache getRetryLimitCache(){
        return  ehCacheCacheManager.getCache("retryLimitCache");
    }

    /**
     * 获取分页查询语句数量缓存
     * @return
     */
    public Cache getpageResultCountCache(){
        return  ehCacheCacheManager.getCache("pageResultCountCache");
    }
}
