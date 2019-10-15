package com.mxh.springboot.autoconfiguration.springbootfirst.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("sayHello")
    public  String sayHello(@RequestParam(value = "name")String name){
        return "hello:"+name;
    }



}
