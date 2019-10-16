package com.mxh.springboot.autoconfiguration.springbootfirst.firstDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ConfigurationDemo {

    @Bean
    @Scope
    public DemoClass demoClass(){
        return  new DemoClass();
    }


}
