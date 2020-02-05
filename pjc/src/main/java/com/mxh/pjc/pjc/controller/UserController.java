package com.mxh.pjc.pjc.controller;

import com.mxh.pjc.pjc.domain.User;
import com.mxh.pjc.pjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    int port;

    @Autowired
    private UserService userService;

    @RequestMapping("hello")
    public String hello(@RequestParam(value = "name")String name){
        System.out.println(name);
        return "welcome,hello:"+name+";port:"+port;
    }

    @RequestMapping("say")
    public String say(@RequestParam(value = "name")String name){
        System.out.println(name);
        return "say :"+name;
    }




    @RequestMapping("save")
    public User saveUser(@RequestParam(value = "name")String name,
                         @RequestParam(value = "addr")String addr){
        User user = new User();
        user.setUserName(name);
        user.setAddr(addr);
        if(userService.save(user)){
            return  user;
        }else{
            return  null;
        }
    }

    @RequestMapping("findall")
    public Collection findall(){
        return userService.findall();
    }



}
