package com.le.bookcommon.dto;

import com.le.bookcommon.enums.ResultDtoEnum;

import java.io.Serializable;

public class ResultDto<T> implements Serializable {


   public static final ResultDto SUCCESS = new ResultDto(0,"操作成功");


   public static final ResultDto  ERROR = new ResultDto(1,"系统错误");
   public static final ResultDto   NO_PRIVILEGE = new ResultDto(2,"没有权限");


   public static final ResultDto   AUTH_ERROR= new ResultDto(3,"鉴权失败");
   public static final ResultDto    NO_USER=new ResultDto(4,"这个用户不存在");
    public static final ResultDto   INCORRECT_PASSWORD = new ResultDto(5,"密码错误");
    public static final ResultDto  NOT_LOGIN = new ResultDto(6,"请登录");




    private Integer code;

   private String msg;

   private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultDto(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public ResultDto(ResultDtoEnum resultDtoEnum){
        this.code = resultDtoEnum.getCode();
        this.msg = resultDtoEnum.getMsg();
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
