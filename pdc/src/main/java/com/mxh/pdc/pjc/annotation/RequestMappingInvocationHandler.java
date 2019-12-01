package com.mxh.pdc.pjc.annotation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * Created by Maxiaohong on 2019-12-01.
 */
public class RequestMappingInvocationHandler  implements InvocationHandler {

    private final String serviceName;
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    private final BeanFactory beanFactory;
    public RequestMappingInvocationHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //过滤标注@RequestMapping方法
        RequestMapping requestMapping =  findAnnotation(method, RequestMapping.class);
        if(requestMapping!=null){
            //获取URI
            String[] uri = requestMapping.value();
            //得到完整的URL,即：   http://serviceName/uri
            StringBuilder urlBuilder = new StringBuilder("http://").append(serviceName).append("/").append(uri[0]);


            //获取方法参数数量
            int count = method.getParameterCount();
            //方法参数顺序
            String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
            //方法参数类型集合
            Class<?>[] paramTypes = method.getParameterTypes();
            //方法注解集合
            Annotation[] methodAnnotations =  method.getDeclaredAnnotations();
            StringBuilder queryStringBuilder = new StringBuilder();
            for(int i=0;i<count;i++){
                String paramName = paramNames[i];

                RequestParam requestParam = method.getAnnotation(RequestParam.class);
                if(requestParam!=null){
                    //组装uri
                    String requestParamName = StringUtils.hasText(requestParam.value())?requestParam.value():paramName;
                    Class<?> paramType = paramTypes[i];
                    String requestParamValue = String.class.equals(paramType)?(String)args[i]:String.valueOf(args[i]);

                    queryStringBuilder.append("&").append(requestParamName).append("=").append(requestParamValue);

                }
            }


            String queryString = queryStringBuilder.toString();
            if(StringUtils.hasText(queryString)){
                urlBuilder.append("?").append(queryString);
            }

            //  http://${serviceName}/${uri}/${queryString}

            String url = urlBuilder.toString();

            //获得RestTemplate

            RestTemplate restTemplate = beanFactory.getBean("lbRestTemplate", RestTemplate.class);
          return   restTemplate.getForObject(url,method.getReturnType());
        }
        return null;
    }
}
