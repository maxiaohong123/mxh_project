package com.mxh.pdc.pjc.service;

import com.mxh.pdc.pjc.domain.User;

import java.util.Collection;

public interface UserService {

    boolean save(User user);

    Collection findall();
}
