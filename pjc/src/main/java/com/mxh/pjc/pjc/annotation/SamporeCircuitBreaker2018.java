package com.mxh.pjc.pjc.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)  //标注在方法上
@Retention(RetentionPolicy.RUNTIME) //运行时保存注解信息
@Documented  //表示可以形成文档
public @interface SamporeCircuitBreaker2018 {
    /*
      并发量
     */
    int value();
}
