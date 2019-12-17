package com.mxh.pdc.service;

import com.mxh.pdc.domain.User;

import java.util.Collection;

public interface UserService {

    boolean save(User user);

    Collection findall();
}
