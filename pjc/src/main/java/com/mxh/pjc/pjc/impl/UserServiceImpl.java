package com.mxh.pjc.pjc.impl;

import com.mxh.pjc.pjc.domain.User;
import com.mxh.pjc.pjc.resporistry.UserResporistry;
import com.mxh.pjc.pjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserResporistry resporistry;
    @Override
    public boolean save(User user) {
       return resporistry.save(user);
    }

    @Override
    public Collection findall() {
        System.out.println("服务提供方查询成功");
        return resporistry.findAll();
    }
}
