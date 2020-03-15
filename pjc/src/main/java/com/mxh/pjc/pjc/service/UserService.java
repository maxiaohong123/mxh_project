package com.mxh.pjc.pjc.service;

import com.mxh.pjc.pjc.domain.TUser;
import com.mxh.pjc.pjc.domain.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    boolean save(User user);

    Collection findall();

    List<TUser> findallDB();
}
