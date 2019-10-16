package com.mxh.core;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MxhConfig {

    @Bean
    public MxhCore mxhCore(){
       return new MxhCore();
    }
}
