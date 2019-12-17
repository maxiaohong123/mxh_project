package com.mxh.pdc.controller;

import com.mxh.pdc.domain.User;
import com.mxh.pdc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/order")
public class UserController2017 {

    @Autowired
    private UserService userService;



    @RequestMapping("hello")
    public String hello(@RequestParam(value = "name")String name){
        return "hello:"+name;
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
