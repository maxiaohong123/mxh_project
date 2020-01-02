package com.mxh.gateway;

import com.mxh.gateway.loadbalancer.ZookeeperLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.mxh.gateway.servlet")
@EnableScheduling
public class ServletGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletGatewayApplication.class, args);
	}

	@Bean
	public ZookeeperLoadBalancer zookeeperLoadBalancer(DiscoveryClient discoveryClient) {
		return new ZookeeperLoadBalancer(discoveryClient);
	}

}
