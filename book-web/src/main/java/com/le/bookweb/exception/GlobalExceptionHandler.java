package com.le.bookweb.exception;

import com.le.bookcommon.dto.ResultDto;
import com.le.bookcommon.enums.ResultDtoEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public Object authorizationException (Exception e){
        e.printStackTrace();
        return ResultDto.NO_PRIVILEGE;
    }

    @ExceptionHandler(AuthenticationException.class)
    public Object AuthenticationException(Exception e){
        e.printStackTrace();
        return ResultDto.AUTH_ERROR;
    }


    @ExceptionHandler(IncorrectCredentialsException.class)
    public Object incorrectCredentialsException(Exception e){
        e.printStackTrace();
        return ResultDto.INCORRECT_PASSWORD;
    }

    @ExceptionHandler(UnknownAccountException.class)
    public Object unknownAccountException(Exception e){
        UnknownAccountException ex = (UnknownAccountException) e;
        return ResultDto.NO_USER;
    }



    @ExceptionHandler(BindException.class)
    public Object bindException(Exception e){
        BindException bindException = (BindException) e;
        List<ObjectError> errors = bindException.getAllErrors();
        // 快速失败只需返回第一个错误即可
        return errors.get(0).getDefaultMessage();
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e){
        e.printStackTrace();
        return ResultDto.ERROR;
    }

}
