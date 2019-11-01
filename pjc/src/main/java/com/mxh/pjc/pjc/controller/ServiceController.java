package com.mxh.pjc.pjc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getAllServices()
    {
        return  discoveryClient.getServices();
    }


    @GetMapping("/services/instances/{serviceName}")
    public  List<String> getAllServiceInstances(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName)
                .stream()
                .map(s->{
            return s.getServiceId()+"-"+s.getHost()+":"+s.getPort();
        }).collect(Collectors.toList());
    }

    /**
     * eureka的api编程
     * @param serviceName
     * @return
     */
    @GetMapping("/services/instances1/{serviceName}")
    public  List<ServiceInstance> getAllServiceInstances1(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName)
                ;
    }
}
