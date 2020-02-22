package com.le.bookweb.controller;

import com.le.bookcommon.GlobalStatic;
import com.le.bookcommon.dto.ResultDto;
import com.le.bookcommon.enums.ResultDtoEnum;
import com.le.bookdao.entity.User;
import com.le.bookservice.user.IUserService;
import com.le.bookweb.config.RedisConfig;
import com.le.bookweb.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@RequestMapping("/user")
@Controller
public class UserController {


    @Autowired
    private IUserService userService;


    @Autowired
    private RedisConfig redisConfig;


    @RequestMapping("/login")
    public Object login(@Valid User user){

        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(token);
        Session session = ShiroUtils.getSession();
        session.setAttribute(GlobalStatic.SESSION_USERNAME,user.getUsername());
        return "forward:/user/current";
    }

    @RequestMapping("/notLogin")
    @ResponseBody
    public Object notLogin(){
        return ResultDto.NOT_LOGIN;
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public Object userLogin(){
        return ResultDto.NO_PRIVILEGE;
    }

//    @RequestMapping("/success")
//    public Object success(){
//        Session session = ShiroUtils.getSession();
//        return ResultDto.SUCCESS;
//    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public Object unauthorized(){
        return ResultDtoEnum.unauthorized;
    }


    @RequestMapping("/current")
    @ResponseBody
    public Object getCurrent(HttpServletRequest request){
        Session session = ShiroUtils.getSession();
        String username = (String) session.getAttribute(GlobalStatic.SESSION_USERNAME);
        User user = userService.selectByUsername(username);
        if(Objects.isNull(user)){
            return ResultDto.NOT_LOGIN;
        }
        user.setPassword(null);
        return new ResultDto<User>(ResultDtoEnum.SUCCESS,user);
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public Object getUser(@PathVariable("id") Long id){
        Subject subject = ShiroUtils.getSubject();
        subject.isAuthenticated();
        User user = userService.selectById(id);
        if(Objects.nonNull(user)){
            user.setPassword(null);
        }
        return user;
    }


    @RequestMapping("/logout")
    @ResponseBody
    public Object logOut(String username){
        Subject subject = ShiroUtils.getSubject();
        subject.logout();
        return ResultDto.SUCCESS;
    }
}
