package com.mxh.pdc.pjc.impl;

import com.mxh.pdc.pjc.domain.User;
import com.mxh.pdc.pjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PROVIDER_SERVER_URL_PREFIX = "http://pjcmxh-local";
    @Override
    public boolean save(User user) {
        String name = user.getUserName();
        String addr = user.getAddr();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("name",name);
        paramMap.put("addr",addr);
        User returnValue =
                restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/save?name={name}&addr={addr}",User.class,paramMap);
        return returnValue != null;
    }

    @Override
    public Collection findall() {
        System.out.println("服务调用方查询成功");
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/findall", Collection.class);
    }
}
