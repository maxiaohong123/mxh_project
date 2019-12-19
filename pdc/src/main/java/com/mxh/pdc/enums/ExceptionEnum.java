package com.mxh.pdc.enums;

/**
 * @Author: maxiaohong
 * @Date: 2019/12/5
 * @Description:  异常枚举
 */
public enum ExceptionEnum {
    PARAMS_EXCEPTION(-3,"参数异常"),
    MY_EXCEPTION(-2,"自定义异常"),
    UNKNOW_EXCEPTION(-1,"未知异常"),
    ERROR_EXCEL(102,"上传文件格式不正确"),
    ERROR_DEPARTMENT(103,"保存部门失败"),
    FAIL_IMPORT_EXCEL(104,"导入excel文件失败"),
    LOGIN_FAIL(105,"登录失败"),
    LOGOUT_FAIL(106,"登出失败"),
    EMPTY_EXPORT_EXCEL(107,"导出excel文件数据为空"),
    FAIL_IMPORT_TYPE_EXCEL(-600,"只能选择xls或xlsx类型的文件"),
    //pmc专用
    LOGINOUT_EXCEPTION(-9,"退出异常"),
    NOT_LOGIN(401,"未登录"),
    //pjc专用
    PROJECT_IS_EXIST(-111,"该项数据存在关联项目，请先解除关联项目")
    ;
    //异常码
    private Integer code;
    //异常信息
    private String msg;

    ExceptionEnum(Integer code, String msg) {
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
