package com.mxh.pdc.pjc;

import com.mxh.pdc.pjc.annotation.EnableRestClients2018;
import com.mxh.pdc.pjc.annotation.RestClient2018;
import com.mxh.pdc.pjc.service.rest.clients.UserRestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients  //启动FeignClient
@EnableRestClients2018(clients = RestClient2018.class) //启动自定义RestClient
@EnableScheduling
public class PdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdcApplication.class, args);


        //看到 2018期   feign  中  完了，但是自己手写的FeignClient实现原理还有问题，需继续研究
    }





}
