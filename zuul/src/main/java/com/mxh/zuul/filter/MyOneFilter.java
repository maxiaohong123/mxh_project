package com.mxh.zuul.filter;

import com.mxh.zuul.domain.Result;
import com.mxh.zuul.util.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Maxiaohong on 2019-12-23.
 */

@Component
public class MyOneFilter  extends ZuulFilter{

    @Value("${noAuth.uri}")
    private String noAuth;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("shouldFilter method...");
//        共享RequestContext，上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String type = request.getMethod().toUpperCase();

        String urlHead = request.getRequestURI().split("/")[2];
        boolean result = !noAuth.equals(urlHead) && !"logout".equals(urlHead);
        //需要权限校验URL
        return result;

//        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("MyOneFilter method...");
        //共享RequestContext，上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //发送日志
//        String userId = JwtUtil.getUserId(request);
//        String cookieId = JwtUtil.getUserId2(request);
//        logger.info("登陆人id：{}",userId);
//        if (StringUtil.isEmpty(userId) && StringUtil.isEmpty(cookieId)){
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(ExceptionEnum.NOT_LOGIN.getCode());
//            Result result=new Result();
//            result.setCode(ExceptionEnum.NOT_LOGIN.getCode());
//            result.setMsg(ExceptionEnum.NOT_LOGIN.getMsg());
//            ctx.setResponseBody(JsonUtils.toJSONString(result));
//            ctx.getResponse().setContentType("application/json;;charset=UTF-8");
//        }
        //----------------test----------------------

        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(300);
        Result result=new Result();
        result.setCode(300);
        result.setMsg("未登录");
        ctx.setResponseBody(JsonUtils.toJSONString(result));
        ctx.getResponse().setContentType("application/json;charset=UTF-8");




        return null;


    }
}
