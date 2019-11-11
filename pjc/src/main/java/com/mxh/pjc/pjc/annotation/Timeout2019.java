package com.mxh.pjc.pjc.annotation;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)  //标注在方法上
@Retention(RetentionPolicy.RUNTIME) //运行时保存注解信息
@Documented //表示可以形成文档
public @interface Timeout2019 {
    /**
     * 超时时间
     * @return
     */
    long value();
    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 补偿方法，默认可以为空
     * @return
     */
    String fallback() default "";

}
