package com.mxh.pdc.pjc.service;

import com.mxh.pdc.pjc.config.FeignCustomerConfig2;
import com.mxh.pdc.pjc.config.FeignCustomerConfigIncepter;
import com.mxh.pdc.pjc.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(value = "${pjc.name}")
public interface UserFeignService {

    @RequestMapping("/user/save")
    User save(@RequestParam(value = "name")String name,
                 @RequestParam(value = "addr")String addr);

    @RequestMapping("/user/hello")
     String hello(@RequestParam(value = "name")String name);

    @RequestMapping("/user/findall")
    Collection findall();

    @RequestMapping("/user/say")//pdc-->pjc
    public String say(@RequestParam String name);
}
