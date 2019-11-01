package com.mxh.pjc.pjc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient  //注意：一定要用EnableDiscoveryClient,它即支持eureka，也支持zookeeper
@EnableHystrix
public class PjcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PjcApplication.class, args);
    }

}
