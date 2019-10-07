package com.le.bookweb.config;

import com.le.bookcommon.GlobalStatic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    @Bean("corsFilter")
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }


    @Bean("authorFilter")
    public FilterRegistrationBean authorFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean(new AuthorFilter());
        registration.addUrlPatterns("/*");
        registration.setName("authorFilter");
        Map<String,String> initPara = new HashMap<>(GlobalStatic.THREE);
        StringBuilder sb = new StringBuilder();
        sb.append("/user/login");
        initPara.put("excludeUrl",sb.toString());
        registration.setInitParameters(initPara);
        registration.setOrder(2);
        return registration;

    }








//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthorityInterceptor());
//    }
}


