package com.mxh.pdc.pjc.controller;

import com.mxh.pdc.pjc.domain.User;
import com.mxh.pdc.pjc.service.UserFeignService;
import com.mxh.pdc.pjc.service.rest.clients.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private UserRestService userRestService;





//    @PostConstruct
//    public void init(){
////        String name = "a1";
////        String addr = "a2";
////
////        User user = userFeignService.save(name,addr);
////        if(null!=user){
////            System.out.println("success");
////        }else{
////            System.out.println("fail");
////        }
//
////        User user =  saveUser(name,addr);
////        if(null!=user){
////            System.out.println("success");
////        }else{
////            System.out.println("fail");
////        }
//    }


    @RequestMapping("hello")
    public String hello(@RequestParam(value = "name")String name){
        System.out.println("feign:"+name);
       return  userFeignService.hello(name);
    }



    @RequestMapping("save")
    public User saveUser(@RequestParam(value = "name")String name,
                         @RequestParam(value = "addr")String addr){

        User user = userFeignService.save(name,addr);
        return user;
    }

    @RequestMapping("findall")
    public Collection findall(){
        return userFeignService.findall();
    }


    @RequestMapping("/feign/say")//pdc-->pjc
    public String feignSay(@RequestParam String message){
        return  userFeignService.say(message);
    }

    @RequestMapping("/rest/say")//pdc-->pjc
    public String restSay(@RequestParam String message){
        return  userRestService.say(message);
    }



}
