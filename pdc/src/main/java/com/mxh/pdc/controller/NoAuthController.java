package com.mxh.pdc.controller;

import com.mxh.pdc.domain.Priority;
import com.mxh.pdc.domain.Result;
import com.mxh.pdc.mapper.PriorityMapper;
import com.mxh.pdc.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Maxiaohong on 2019-12-23.
 */


@RestController
@RequestMapping("/noAuth")
public class NoAuthController {

    @Autowired
    private PriorityMapper priorityMapper;


    @RequestMapping("selectAllPriority")
    public Result selectAllPriority(){
        List<Priority> list = priorityMapper.selectAll();
        return ResultUtil.success(list);
    }

    @RequestMapping("logout")
    public Result logout(){

        return ResultUtil.success("logout");
    }


}
