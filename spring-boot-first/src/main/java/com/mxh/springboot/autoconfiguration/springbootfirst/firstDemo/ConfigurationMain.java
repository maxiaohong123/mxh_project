package com.mxh.springboot.autoconfiguration.springbootfirst.firstDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * demo1:通过"AnnotationConfigApplicationContext" 将DemoClass托管到spring中，而不是通过new拿到的。
 */
@ComponentScan(basePackages = "com.mxh.springboot.autoconfiguration.springbootfirst")
//@ComponentScan
public class ConfigurationMain {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationDemo.class);
//        DemoClass demoClass = applicationContext.getBean(DemoClass.class);
//        demoClass.say();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationMain.class);
         String[] defNames = applicationContext.getBeanDefinitionNames();
         for(String name:defNames){
             System.out.println(name);
         }
    }
}
