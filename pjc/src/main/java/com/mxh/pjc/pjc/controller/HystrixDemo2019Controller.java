package com.mxh.pjc.pjc.controller;


import com.mxh.pjc.pjc.annotation.Limited;
import com.mxh.pjc.pjc.annotation.Timeout2019;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/hystrixa")
public class HystrixDemo2019Controller {

    private Random random = new Random();
    @Value("${server.port}")
    private String port;


    //方法一：HystrixCommand基本使用
    @HystrixCommand(fallbackMethod ="failContent",
    commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100")
    })
    @RequestMapping("/echo")
    public String echo(@RequestParam(value="message") String message){
        await();
        return "ECHO:"+port;
    }


    //方法二：自定义注解+springmvc的HandlerInceptor实现
    @Timeout2019(value =50L,fallback = "failContent")
    @RequestMapping("/echo1")
    public String echo1()
    {
        await();
        return  "hello";
    }


    //方法三：Samephore限流实现
    @Limited(10)
    @GetMapping("/world")
    public String world() {
        await();
        return "world";
    }



    void await(){
        long value = random.nextInt(100);
        System.out.println("helloworld() costs"+value+"ms.");
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String failContent(String message){
        return "Fault";
    }

    public String failContent(){
        return "Fault No Params";
    }


//    /**
//     * 当{@link #sayhello1}方法调用超时或失败时
//     * @return
//     */
//    @HystrixCommand(fallbackMethod = "errorContent",
//            commandProperties = {
//                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100")
//            })
//    @GetMapping("/sayhello1")
//    public String  sayhello1(@RequestParam(value="message") String message) throws Exception{
//        int value = random.nextInt(200);
//
//        System.out.println("helloworld() costs"+value+"ms."+"message:"+message);
//        Thread.sleep(value);
//        return "hello,world";
//    }
//
//    public String errorContent(){
//        return "error";
//    }
}
