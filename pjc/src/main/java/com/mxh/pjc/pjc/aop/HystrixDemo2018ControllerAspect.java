package com.mxh.pjc.pjc.aop;


import com.mxh.pjc.pjc.annotation.CircuitBreaker2018;
import com.mxh.pjc.pjc.annotation.SamporeCircuitBreaker2018;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Aspect
@Component
public class HystrixDemo2018ControllerAspect {

    private ExecutorService executorService = Executors.newFixedThreadPool(20);
    private Semaphore semaphore = null;

    //高级版本：采用aspect+无注解实现
    @Around("execution(* com.mxh.pjc.pjc.controller." +
            "HystrixDemo2018Controller.say5(..))&&args(message)")
    public Object say5Timeout(ProceedingJoinPoint point,String message) throws Throwable{
        return  doInvoke(point,message,100);
    }

    //采用aspect+有注解实现
//    @Around("execution(* com.mxh.pjc.pjc.controller." +
//            "HystrixDemo2018Controller.say6(..))&&args(message)&&@annotation(circuitBreaker)")
//    public Object say6Timeout(ProceedingJoinPoint point, String message, CircuitBreaker2018 circuitBreaker) throws Throwable{
//        //1、获取@CircuitBreaker注解的超时时间。
//        long timeout = circuitBreaker.timeout();
//        return  doInvoke(point,message,timeout);
//    }

     //高级版本：采用aspect+反射实现
    @Around("execution(* com.mxh.pjc.pjc.controller." +
            "HystrixDemo2018Controller.say6(..))&&args(message)")
    public Object say6Timeout(ProceedingJoinPoint point, String message) throws Throwable{
        //1、获取@CircuitBreaker注解的超时时间。
        long timeout = -1;
        if (point instanceof MethodInvocationProceedingJoinPoint) {
            MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) point;
            MethodSignature signature = (MethodSignature) methodPoint.getSignature();
            Method method = signature.getMethod();
            CircuitBreaker2018 circuitBreaker = method.getAnnotation(CircuitBreaker2018.class);
            timeout = circuitBreaker.timeout();
        }
        return doInvoke(point, message, timeout);
    }


    //高级版本：采用aspect+Samphore
    @Around("execution(* com.mxh.pjc.pjc.controller." +
            "HystrixDemo2018Controller.say7(..))&&args(message)&&@annotation(circuitBreaker)")
    public Object say7Timeout(ProceedingJoinPoint point, String message, SamporeCircuitBreaker2018 circuitBreaker) throws Throwable {

        int value = circuitBreaker.value();
        if(semaphore==null){
            semaphore = new Semaphore(value);
        }
        Object returnValue = null;
        try{
            if(semaphore.tryAcquire()){
                returnValue = point.proceed(new Object[]{message});
                Thread.sleep(1000);
            }else{
                returnValue = errorContent7();
            }
        }
        finally {
           semaphore.release();
        }
       return  returnValue;
    }








    @PreDestroy
    public  void destory(){
        executorService.shutdown();
    }

    //熔断后的处理方法
    private String errorContent(){
        return "error! see you say5555";
    }

    private String errorContent6(){
        return "error! see you say666";
    }

    private String errorContent7(){
        return "error! see you say777";
    }

    private  Object doInvoke(ProceedingJoinPoint point,String message,long timeout)throws Throwable{
        //2、执行目标方法
        Future<Object> future =  executorService.submit(()->{
            Object returnValue = null;
            try{
                returnValue = point.proceed(new String[]{message});
            }
            catch (Throwable throwable){
                throwable.printStackTrace();
            }
            return returnValue;
        });

        //3、获取方法的返回值，利用trycatch实现熔断。
        Object returnValue = null;
        try{
            returnValue = future.get(timeout,TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException e){
            future.cancel(true);
            returnValue =   errorContent6();
        }
        return returnValue;
    }





}
