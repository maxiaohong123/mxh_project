package com.mxh.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Maxiaohong on 2019-12-20.
 */
@RestController
@RequestMapping("demo")
public class DemoController {


    @RequestMapping("say")
    public String say(@RequestParam("name")String name){
        System.out.println("say:"+name);
        return "say:"+name;
    }


}
