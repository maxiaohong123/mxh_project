package com.mxh.pdc.mapper;

import com.mxh.pdc.domain.Priority;

import java.util.List;

public interface PriorityMapper {
    int deleteByPrimaryKey(Integer priorityId);

    int insert(Priority record);

    int insertSelective(Priority record);

    Priority selectByPrimaryKey(Integer priorityId);

    int updateByPrimaryKeySelective(Priority record);

    int updateByPrimaryKey(Priority record);

    List<Priority> selectAll();
}