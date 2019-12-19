package com.mxh.pdc.service.impl;

import com.mxh.pdc.domain.User;
import com.mxh.pdc.service.UserFeignService;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Maxiaohong on 2019-12-19.
 */
@Component
public class UserFeignServiceImpl implements UserFeignService {
    @Override
    public User save(String name, String addr) {
        User user = new User();
        user.setUserName("error");
        return user;
    }

    @Override
    public String hello(String name) {
        return "hello error";
    }

    @Override
    public Collection findall() {
        System.out.println("findall调用失败");
        return null;
    }

    @Override
    public String say(String name) {
        System.out.println("say error");
        return "我是托底数据";
    }
}
