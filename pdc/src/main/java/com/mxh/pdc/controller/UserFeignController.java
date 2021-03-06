package com.mxh.pdc.controller;

import com.mxh.pdc.domain.Priority;
import com.mxh.pdc.domain.Result;
import com.mxh.pdc.domain.User;
import com.mxh.pdc.mapper.PriorityMapper;
import com.mxh.pdc.service.UserFeignService;
import com.mxh.pdc.service.rest.clients.UserRestService;
import com.mxh.pdc.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private UserRestService userRestService;

    @Autowired
    private PriorityMapper priorityMapper;





//    @PostConstruct chenting test use
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


    @RequestMapping("/feign/hello")
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


    /**
     * @description:使用openfeign方式调用pjc服务
     * @param name
     * @return
     */
    @RequestMapping("/feign/say")//pdc-->pjc
    public String feignSay(@RequestParam("name") String name){
        try{
            return  userFeignService.say(name);
        }catch (Exception e){
            e.printStackTrace();
            return "调用异常";
        }

    }

    /**
     * @description:使用自定义feign方式调用pjc服务,通过自定义feign方式，可以了解spring-cloud-openfeign的实现原理
     * @param name
     * @return
     */
    @RequestMapping("/rest/say")//pdc-->pjc
    public String restSay(@RequestParam("name") String name){
        return  userRestService.say(name);
    }


    @RequestMapping("selectAllPriority")
    public Result selectAllPriority(){
        List<Priority> list = priorityMapper.selectAll();
        return ResultUtil.success(list);
    }



}
