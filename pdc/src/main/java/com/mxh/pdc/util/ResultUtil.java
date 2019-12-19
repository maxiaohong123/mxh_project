package com.mxh.pdc.util;


import com.mxh.pdc.domain.Result;
import com.mxh.pdc.enums.ExceptionEnum;
import com.mxh.pdc.enums.ResultEnum;
import com.mxh.pdc.exception.MyException;

/**
 *
 * @Author: maxiaohong
 * @Date: 2019/12/5
 * @Description:
 */
public class ResultUtil {



    /**
    * @Description:    默认成功方法
    * @Author:         maxiaohong
    * @Date:     22019/12/5
    */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
    * @Description:    传空的成功方法
    * @Author:         maxiaohong
    * @Date:     2019/12/5
    */
    public static Result successForNull(){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }

    /**
    * @Description:    失败方法
    * @Author:         maxiaohong
    * @Date:     2019/12/5
    */
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
    * @Description:    针对枚举的失败方法
    * @Author:         maxiaohong
    * @Date:     2019/12/5
    */
    public static Result errorForEnum(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /**
     * @Author maxiaohong
     * @Description 保证错误方法名称一致性重写方法，上面得方法不指定有谁在用，所以没删除
     * @Date  2019/12/5
     * @param resultEnum 结果枚举
     * @return com.worktime.commons.domain.Result
     **/
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /**
    * @Description:    返回异常
    * @Author:         maxiaohong
    * @Date:     2019/12/5
    */
    public static Result error(ExceptionEnum exceptionEnum){
        Result result = new Result();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        return result;
    }

    /**
     * @Author maxiaohong
     * @Description //返回自定义异常
     * @Date  2019/12/5
     * @param me 自定义异常
     * @return com.worktime.commons.domain.Result
     **/
    public static Result error(MyException me){
        Result result = new Result();
        result.setCode(me.getCode());
        result.setMsg(me.getMessage());
        return result;
    }
}
