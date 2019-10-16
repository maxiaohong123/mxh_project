package com.mxh.springboot.autoconfiguration.springbootfirst.secondDemo.current;

import com.mxh.springboot.autoconfiguration.springbootfirst.firstDemo.ConfigurationMain;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SecondMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] defNames = applicationContext.getBeanDefinitionNames();
        for(String name:defNames){
            System.out.println(name);
        }
    }
}
