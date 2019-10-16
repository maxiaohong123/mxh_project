package com.mxh.springboot.autoconfiguration.springbootfirst.fourthDemo;

import com.mxh.core.MxhCore;
import com.mxh.springboot.autoconfiguration.springbootfirst.thirdDemo.CacheService;
import com.mxh.springboot.autoconfiguration.springbootfirst.thirdDemo.LoggerService;
import com.mxh.springboot.autoconfiguration.springbootfirst.thirdDemo.ThirdMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FouthMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FouthMain.class,args);
        System.out.println(context.getBean(MxhCore.class));
    }

}
