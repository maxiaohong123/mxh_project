package com.mxh.pjc.pjc.web.mvc;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.concurrent.TimeoutException;

public class CircuitBreakerHandlerInterceptor2018 implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{

        if("/hystrix/say4".equals(request.getRequestURI())&& ex instanceof TimeoutException){
            Writer writer = response.getWriter();
            writer.write(errorContent());
        }
    }

    //熔断后的处理方法
    public String errorContent(){
        return "error! see you say4";
    }


}


