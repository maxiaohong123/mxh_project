package com.mxh.springboot.autoconfiguration.springbootfirst.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HelloAspect {
    private final static Logger logger = LoggerFactory.getLogger(HelloAspect.class);
    @Pointcut("execution( * com.mxh.springboot.autoconfiguration.springbootfirst.controller.HelloController.sayHello(..))")
    public  void sayHello(){}


    @Before("sayHello()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("执行方法前");
        ServletRequestAttributes sra =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.info("完整的URL:"+request.getRequestURL());

        String methodName = joinPoint.getSignature().getName();
        logger.info("请求的方法名称："+methodName);
    }

}
