package com.mxh.springboot.autoconfiguration.springbootfirst.secondDemo.current;


import com.mxh.springboot.autoconfiguration.springbootfirst.secondDemo.other.OtherConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(OtherConfig.class)
public class SpringConfig {

    @Bean
    public DefaultBean defaultBean(){
        return  new DefaultBean();
    }
}
