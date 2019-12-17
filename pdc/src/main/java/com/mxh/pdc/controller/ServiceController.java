package com.mxh.pdc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getAllServices()
    {
        return  discoveryClient.getServices();
    }


    /**
     * eureka的api编程
     * @param serviceName
     * @return
     */
    @GetMapping("/services/instances/{serviceName}")
    public  List<ServiceInstance> getAllServiceInstances(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName)
               ;
    }
}
