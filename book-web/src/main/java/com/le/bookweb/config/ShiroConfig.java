package com.le.bookweb.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;


@Configuration
@Order
public class ShiroConfig {

    @Autowired
    private RedisConfig redisConfig;


//    @Autowired
//    private SecurityManager  securityManager;

    @Bean
    public Realm userRealm(){
        UserRealm realm = new UserRealm();
        return realm;
    }

    @Bean
    public SessionsSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        SecurityUtils.setSecurityManager(securityManager);
//        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }



    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/user/notLogin");
        shiroFilter.setSuccessUrl("/user/current");
        shiroFilter.setUnauthorizedUrl("/unauthorized");
        Map<String, String> filterChainDefinitionMap = new HashMap<>(3);
        filterChainDefinitionMap.put("/**", DefaultFilter.authc.name());
        filterChainDefinitionMap.put("/user/login", DefaultFilter.anon.name());
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

//    public RedisManager redisManager(){
//        RedisManager redisManager = new RedisManager();
//        redisManager.setPort(redisConfig.getPort());
//        redisManager.setHost(redisConfig.getHost());
//        return redisManager;
//    }

//    @Bean
//    public CacheManager cacheManager(){
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        return redisCacheManager;
//    }


//    @Bean
//    public SessionDAO sessionDAO(){
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//
//        return redisSessionDAO;
//    }


    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setGlobalSessionTimeout(1800000L);
//        sessionManager.setCacheManager(cacheManager());
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }






}
