package com.mxh.pdc.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Maxiaohong on 2019-11-10.
 */
@Configuration
public class FeignCustomerConfig2  {
    @Bean
    public RequestInterceptor getRequestInterceptor(){
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = sra.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if(headerNames!=null){
                    while (headerNames.hasMoreElements()){
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        requestTemplate.header(name,values);
                    }
                }

            }
        };
    }

}
