package com.demo.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tommy Huang
 * @email tommy.huang@demo.com
 * @date 2017/11/6 14:00
 */
@Component
@Scope("singleton")
public class RetryLimitUtils {

    @Autowired
    private  EhcacheUtils ehcacheUtils;
    @Value(value = "${Login.retryLimitCounts}")
    private Integer retryCounts;
    private static Cache retryLimitCache=null;

    /**
     * 账号是否被锁定,
     * @param userName 用户名
     * @return  true
     */
    public boolean checkLockStatus(String userName){

        if(retryLimitCache==null){
            retryLimitCache=ehcacheUtils.getRetryLimitCache();
        }
        AtomicInteger atomicInteger=retryLimitCache.get(CommonUtils.geneRateRetryLimitCacheKey(userName),AtomicInteger.class);
        if(atomicInteger==null || atomicInteger.intValue()<retryCounts){
            return false;
        }
        return  true;
    }

    /**
     * 增加重试次数
     * @param userName
     * @return
     */
    public int addLimitCount(String userName){
       if(retryLimitCache==null){
           retryLimitCache=ehcacheUtils.getRetryLimitCache();
       }
        AtomicInteger atomicInteger=retryLimitCache.get(CommonUtils.geneRateRetryLimitCacheKey(userName),AtomicInteger.class);
        if (atomicInteger==null){
            atomicInteger=new AtomicInteger(0);
        }
        atomicInteger.incrementAndGet();
        retryLimitCache.put(CommonUtils.geneRateRetryLimitCacheKey(userName),atomicInteger);
        return atomicInteger.intValue();
    }

}
