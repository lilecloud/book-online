package com.le.bookweb.config;

import com.le.bookcommon.GlobalStatic;
import com.le.bookcommon.dto.ResultDto;
import com.le.bookweb.util.ShiroUtils;
import org.apache.shiro.session.Session;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthorFilter implements Filter {


    private Set<String> excludeUrlSet = new HashSet<>(GlobalStatic.FOUR);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludeUrl = this.getPropertyFromInitParams(filterConfig,"excludeUrl","aaa");
        String [] excludeUrls =  excludeUrl.split(",");
        excludeUrlSet.addAll(Arrays.asList(excludeUrls));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;




        String path = request.getServletPath();
        if(excludeUrlSet.contains(path)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{

            Session session = ShiroUtils.getSession();
            String username = (String) session.getAttribute(GlobalStatic.SESSION_USERNAME);
            if(!StringUtils.isEmpty(username)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                response.setStatus(401);
                response.setHeader("Content-Type","application/json;charset=UTF-8");
                PrintWriter writer = servletResponse.getWriter();
                writer.write(ResultDto.NOT_LOGIN.toString());
                writer.flush();
                writer.close();
                response.flushBuffer();
            }

        }

    }



    private final String getPropertyFromInitParams(FilterConfig filterConfig, String propertyName, String defaultValue) {
        String value = filterConfig.getInitParameter(propertyName);
        if (!StringUtils.isEmpty(value)) {
            return value;
        }
        return defaultValue;
    }

}
