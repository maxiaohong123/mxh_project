package com.mxh.pdc.pjc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;


/**
 * 2018年ribbon负载均衡原理    2018 ribbon 上  1.07分钟了。
 * @param
 * @return
 */
@RestController
public class RibbonCustomerController2018 {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String serviceName;

    private volatile Set<String> targetUrls = new HashSet<>();

    @Scheduled(fixedRate = 10*1000) //10秒更新一次缓存
    public  void updateTargetUrls(){
        //获取当前服务的所有实例表
        //  http://ip:port
        Set<String> oldTargetUrls = this.targetUrls;
         List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
          serviceInstances
                .stream()
                .map(s->
                        s.isSecure()?
                        "https://"+s.getHost()+":"+s.getPort():
                        "http://"+s.getHost()+":"+s.getPort()
                ).collect(Collectors.toList());



         Set<String> newTargetUrls = new HashSet<>();
        newTargetUrls.removeAll(discoveryClient.getServices());
         this.targetUrls = newTargetUrls;
        oldTargetUrls.clear();
    }

    @RequestMapping("/invoke/say")
    public String invokeSay(){
        //1、获取服务器列表
        //2、轮询列表
        //3、选择其中一台服务器
        //4、RestTemplate发送请求到服务器
        //5、输出响应


    }
}
