package com.mxh.springbootstart.demo;


import com.mxh.starter.autoconfiguration.HelloProperties;
import com.mxh.starter.format.FormatProcessor;
import com.mxh.starter.format.HelloFormatTemplate;
import com.mxh.starter.format.StringFormatProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloFormatTemplate helloFormatTemplate;


    @RequestMapping("/sayhello")
    public String sayHello(@RequestParam(value = "name")String name){
        System.out.println(name);
        return  "hello:"+name;
    }

    @RequestMapping("/doformat")
    public String doFormat(){
        User user = new User();
        user.setId("1001");
        user.setName("马小红");
        return  helloFormatTemplate.doFormat(user);
    }

    @RequestMapping("/format")
    public String format(){
        User user = new User();
        user.setId("aaa");
        user.setName("maciaohong");
        HelloProperties helloProperties = new HelloProperties();
        FormatProcessor formatProcessor = new StringFormatProcessor();
        HelloFormatTemplate helloFormatTemplate = new HelloFormatTemplate(formatProcessor,helloProperties);
        return  helloFormatTemplate.doFormat(user);
    }


}
