package com.mxh.pjc.pjc.web.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig2018  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CircuitBreakerHandlerInterceptor2018());
//        registry.addInterceptor(new CircuitBreakerHandlerInterceptor2019());
    }
}
