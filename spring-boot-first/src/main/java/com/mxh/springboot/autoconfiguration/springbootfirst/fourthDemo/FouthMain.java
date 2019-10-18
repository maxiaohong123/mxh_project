package com.mxh.springboot.autoconfiguration.springbootfirst.fourthDemo;

import com.mxh.core.MxhCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * demo4:本例是测试springboot的 EnableAutoConfiguration 、ConditionalOnClass...(条件注解)。
 *      结合另外一个小demo :mxh-gupaocore
 */
@SpringBootApplication
public class FouthMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FouthMain.class,args);
        System.out.println(context.getBean(MxhCore.class));
    }

}
