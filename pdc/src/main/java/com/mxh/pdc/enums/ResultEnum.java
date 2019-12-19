package com.mxh.pdc.enums;

/**
 * 统一返回结果枚举
 * @Author: maxiaohong
 * @Date: 2019/12/5
 * @Description:
 */
public enum ResultEnum {
    SUCCESS(200,"成功"),
    FAIL(400,"失败"),
    DATA_NO(100,"未查询到数据"),
    DATA_SELECTING(101,"正在查询中"),
    DATA_SUCCESS(102,"查询成功"),
    DATA_FAIL(103,"与服务器连接异常，请重试"),
    USERID_IS_NULL(104,"用ID为空"),
    STOP_FAIL(105,"停止失败"),
    STOP_SUCCESS(106,"停止成功");



    ;

    //编码
    private Integer code;
    //信息
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
