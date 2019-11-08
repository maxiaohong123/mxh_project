package com.mxh.pjc.pjc.controller;


import com.mxh.pjc.pjc.annotation.CircuitBreaker2018;
import com.mxh.pjc.pjc.annotation.SamporeCircuitBreaker2018;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.*;
//看到2018 熔断 上  16：06秒了


@RestController
@RequestMapping("/hystrix")
public class HystrixDemo2018Controller {


    private  final Random random = new Random();

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 当{@link #say2}低级版本：无容错实现，出错直接向浏览器返回异常
     * @return
     */
    @GetMapping("/say2")
    public String  say2(@RequestParam String message) throws Exception{

       Future<String> future =  executorService.submit(()->{
           return doSay2(message);
       });
       //表示100毫秒超时
      String returnValue =  future.get(100,TimeUnit.MILLISECONDS);
       return  returnValue;
    }

    /**
     * 当{@link #say3}低级版本：带容错实现，出错直接向浏览器返回异常{@link #errorContent}
     * @return
     */
    @GetMapping("/say3")
    public String  say3(@RequestParam String message) throws Exception{

        Future<String> future =  executorService.submit(()->{
            return doSay2(message);
        });
        //表示100毫秒超时
        String returnValue = null;
        try{
            returnValue  =  future.get(100,TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException e){
            return   errorContent();
        }

        return  returnValue;
    }

    /**
     * 当{@link #say4}中级版本：分为HandlerInteceptor和RestControllerAdvice 两种实现，但是这种还是有一定的侵入性
     * @return
     */
    @GetMapping("/say4")
    public String  say4(@RequestParam String message) throws Exception{
        Future<String> future =  executorService.submit(()->{
            return doSay2(message);
        });
        String returnValue  =  future.get(100,TimeUnit.MILLISECONDS);
        return  returnValue;
    }


    /**
     * 当{@link #say3}高级版本：采用aspect+无注解实现
     * @return
     */
    @GetMapping("/say5")
    public String  say5(@RequestParam String message) throws Exception{
        return  doSay2(message);
    }

    /**
     * 当{@link #say6}高级版本：采用aspect+有注解实现
     * @return
     */
    @GetMapping("/say6")
    @CircuitBreaker2018(timeout = 100)
    public String  say6(@RequestParam String message) throws Exception{
        return  doSay2(message);
    }


    /**
     * 当{@link #say7}高级版本：采用aspect+Sampore实现
     * @return
     */
    @GetMapping("/say7")
    @SamporeCircuitBreaker2018(5)
    public String  say7(@RequestParam String message) throws Exception{
        return  doSay2(message);
    }




    private String doSay2(String message) throws InterruptedException {

        int value = random.nextInt(200);
        System.out.println("helloworld() costs"+value+"ms.");
        Thread.sleep(value);
        System.out.println("HystrixDemo2018Controller接收到消息："+message);
        String returnValue = "Hello:"+message;

        return returnValue;
    }





    //熔断后的处理方法
    public String errorContent(){
        return "error! see you latter1";
    }
}
