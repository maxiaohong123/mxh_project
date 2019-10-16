package com.mxh.springboot.autoconfiguration.springbootfirst.firstDemo;

import org.springframework.stereotype.Service;

/**
 * 在spring没有提供注解之前，使用xml配置方式，配置如下bean
 * <bean id="" class="com.mxh.springboot.autoconfiguration.springbootfirst.firstDemo.DemoClass"></>
 *
 */

public class DemoClass {

    public  void say(){
        System.out.println("Say:Hello Mic");
    }
}
