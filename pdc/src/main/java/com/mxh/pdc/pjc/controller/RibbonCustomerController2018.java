package com.mxh.pdc.pjc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description :2018年ribbon负载均衡原理(RestTemplate+自定义实现)
 * @param
 * @return
 */
@RestController
public class RibbonCustomerController2018 {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${pjc.name}")
    private String serviceName;
    private volatile Set<String> targetUrls = new HashSet<>();
    @Scheduled(fixedRate = 10*1000) //10秒更新一次缓存
    public  void updateTargetUrls(){
        //获取当前服务的所有实例表
        //  http://ip:port
        Set<String> oldTargetUrls = this.targetUrls;
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        Set<String> newTargetUrls =  serviceInstances
                .stream()
                .map(s->
                        s.isSecure()?
                        "https://"+s.getHost()+":"+s.getPort():
                        "http://"+s.getHost()+":"+s.getPort()
                ).collect(Collectors.toSet());
        //swap
         this.targetUrls = newTargetUrls;
        oldTargetUrls.clear();
    }

    @RequestMapping("/invoke/say")
    public String invokeSay(@RequestParam String name){
        //1、获取服务器列表
        //2、轮询列表
        //3、选择其中一台服务器
        //4、RestTemplate发送请求到服务器
        //5、输出响应

        List<String> targetUrls = new ArrayList<>(this.targetUrls);  //copyonwrite、保证线程安全
        int size = targetUrls.size();
        int index = new Random().nextInt(size);
        String targetUrl = targetUrls.get(index);
        return restTemplate.getForObject(targetUrl+"/user/hello?name="+name,String.class);

    }

    @RequestMapping("/say")
    public String say(@RequestParam String name){
        System.out.println(name);
        return "hello:"+name;
    }

    //注意：此处的RestTemplate仅仅是做调用，并没有负载均衡的作用。因为是自己实现负载均衡，所以没有加@LoadBlance；
    // @Bean+RestTemplate+自定义负载均衡实现  表示自己实现负载均衡
    // @Bean+@LoadBlance+RestTemplate+   表示使用ribbon的负载均衡
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }



}
