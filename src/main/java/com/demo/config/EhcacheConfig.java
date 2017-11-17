package com.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Ehcache配置类
 * @author Tommy Huang
 * @email tommy.huang@demo.com
 * @date 2017/11/6 10:57
 */
@Configuration
@EnableCaching
public class EhcacheConfig {
    Logger logger= LoggerFactory.getLogger(EhcacheConfig.class);
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean){
        return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
    }
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache.xml"));
        Resource resource=new ClassPathResource("ehcache.xml");
        logger.info( resource.toString());
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
}
