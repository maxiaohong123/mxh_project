package com.mxh.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Maxiaohong on 2019-12-11.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("sayHello")
    public String sayHello(@RequestParam("name")String name){
        return  "hello:"+name;
    }

}
