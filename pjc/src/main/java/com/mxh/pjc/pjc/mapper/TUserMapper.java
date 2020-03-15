package com.mxh.pjc.pjc.mapper;

import com.mxh.pjc.pjc.domain.TUser;
import com.mxh.pjc.pjc.domain.User;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKeyWithBLOBs(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUser> selectAllUsers();
}