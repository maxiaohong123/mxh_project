package com.mxh.pjc.pjc.service;

import com.mxh.pjc.pjc.domain.User;

import java.util.Collection;

public interface UserService {

    boolean save(User user);

    Collection findall();
}
