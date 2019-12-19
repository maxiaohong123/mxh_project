package com.mxh.pdc.exception;


import com.mxh.pdc.enums.ExceptionEnum;
import com.mxh.pdc.enums.ResultEnum;

/**
 * @Author: maxiaohong
 * @Date: 2019/12/5 
 * @Description:
 */
public class MyException extends RuntimeException{
    //错误码
    private Integer code;

    /**
    * @Description:    默认异常处理
    * @Author:         maxiaohong
    * @Date:     2019/12/5
    */
    public MyException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
    /**
     * @Author maxiaohong
     * @Description //根据结果枚举的构造方法
     * @Date  2019/12/5
     * @param resultEnum
     * @return
     **/
    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    /**
     * @Description:    自定义异常处理
     * @Author:         maxiaohong
     * @Date:    2019/12/5
     */
    public MyException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }




    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
