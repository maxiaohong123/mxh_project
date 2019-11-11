package com.mxh.pjc.pjc.controller;


import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
//看到2017 熔断 下  36：06秒了


@RestController
@RequestMapping("/hystrix")
public class HystrixDemo2017Controller {

    private  final Random random = new Random();

    /**
     * 当{@link #sayhello1}方法调用超时或失败时
     * @return
     */
    @HystrixCommand(fallbackMethod = "errorContent",
                    commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="100")
    })
    @GetMapping("/sayhello1")
    public String  sayhello1() throws Exception{
        int value = random.nextInt(200);
        System.out.println("helloworld() costs"+value+"ms.");
        Thread.sleep(value);
        return "hello,world";
    }

    @GetMapping("/sayhello2")
    public String  sayhello2() throws Exception{

        return new HelloWorldCommand().execute();
    }

    //HystrixCommand编程方式
    private   class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String>{

        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),
                    100);
        }

        @Override
        protected String run() throws Exception {
            return "hello,world";
        }
        protected  String getFallback(){
            return  HystrixDemo2017Controller.this.errorContent();
        }
    }


    public String errorContent(){
        return "error";
    }
}
