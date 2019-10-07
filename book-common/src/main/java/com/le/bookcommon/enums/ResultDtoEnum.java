package com.le.bookcommon.enums;

public enum ResultDtoEnum {

    SUCCESS(0,"操作成功"),
    ERROR(1,"系统错误"),
    ;

    private Integer code;

    private String msg;

    ResultDtoEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
