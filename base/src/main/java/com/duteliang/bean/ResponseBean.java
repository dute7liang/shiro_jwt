package com.duteliang.bean;

import org.springframework.http.HttpStatus;

public class ResponseBean {

    private boolean success;

    // http 状态码
    private int code = HttpStatus.OK.value();

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseBean(){}

    public ResponseBean(String msg){
        this.msg = msg;
    }

    public ResponseBean(Object data){
        this.data = data;
    }

    public ResponseBean(String msg,Object data){
        this.msg = msg;
        this.data = data;
        this.success = false;
    }

    public ResponseBean(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
