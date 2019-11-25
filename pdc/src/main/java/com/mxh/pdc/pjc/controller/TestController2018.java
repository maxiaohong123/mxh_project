package com.mxh.pdc.pjc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 2018年ribbon负载均衡原理(手写代码实现负载均衡原理)
 * 2018 ribbon 上  1.07分钟了。
 * @param
 * @return
 */
@RestController
public class TestController2018 {

    @Autowired
    private RestTemplate restTemplate;







    @RequestMapping("/invoke1/say1")
    public String invokeSay(@RequestParam String name){
        //1、获取服务器列表
        //2、轮询列表
        //3、选择其中一台服务器
        //4、RestTemplate发送请求到服务器
        //5、输出响应

        String targetUrl = "http://10.1.56.175:8111/user/hello?name="+name;
        return restTemplate.getForObject(targetUrl,String.class);

    }
}
