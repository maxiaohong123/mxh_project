package com.mxh.springboot.autoconfiguration.springbootfirst.thirdDemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDefinitionService
public class ThirdMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ThirdMain.class,args);
        System.out.println(context.getBean(CacheService.class));
        System.out.println(context.getBean(LoggerService.class));
    }
}
