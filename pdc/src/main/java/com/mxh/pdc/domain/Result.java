package com.mxh.pdc.domain;

import java.io.Serializable;

/**
 * @Author: maxiaohong
 * @Date: 2019/12/06
 * @Description:
 */
public class Result<T> implements Serializable{
    //编码 200成功
    private  Integer code;
    //信息
    private String msg;
    //返回内容
    private T data;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
