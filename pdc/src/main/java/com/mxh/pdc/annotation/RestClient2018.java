package com.mxh.pdc.annotation;

import java.lang.annotation.*;

/**
 * Created by Maxiaohong on 2019-12-01.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestClient2018 {
    //Rest服务应用名称
    String name();
}
