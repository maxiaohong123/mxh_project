package com.mxh.pjc.pjc.controller;

import com.mxh.pjc.pjc.domain.TUser;
import com.mxh.pjc.pjc.domain.User;
import com.mxh.pjc.pjc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Value("${server.port}")
    int port;

    @Autowired
    private UserService userService;



    @RequestMapping("hello")
    public String hello(@RequestParam(value = "name")String name){
        System.out.println("控制台日志："+name);
        logger.info("logger日志：{}",name);
        return "welcome,hello:"+name+";port:"+port;
    }

    @RequestMapping("findAllUsers")
    public List<TUser> findAllUsers(){
        List<TUser> userList = userService.findallDB();
        System.out.println("查询人员列表："+userList);
        return userList;
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
