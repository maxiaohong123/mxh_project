package com.mxh.pdc;

import com.mxh.pdc.annotation.EnableRestClients2018;
import com.mxh.pdc.service.UserFeignService;
import com.mxh.pdc.service.rest.clients.UserRestService;
import com.mxh.pdc.xiaomage.annotation.EnableRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients(clients = UserFeignService.class)  //启动FeignClient
@EnableRestClients2018(clients = UserRestService.class) //启动自定义RestClient
//@EnableRestClient(clients = UserRestService.class) //启动自定义RestClient
@EnableScheduling
public class PdcApplication {
    public static void main(String[] args) {
        SpringApplication.run(PdcApplication.class, args);
    }
}
