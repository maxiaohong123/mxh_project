package com.mxh.pjc.pjc.web.mvc;

import com.mxh.pjc.pjc.annotation.Timeout2019;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class CircuitBreakerHandlerInterceptor2019 implements HandlerInterceptor {

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    //注意：此处2018年采用afterCompletion实现
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.拦截处理方法(SpringMVC 内存HandlerInceptor)
        //2.得到被拦截的方法
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            //2.1 通过handlerMethod获取Method对象。
            Method method = handlerMethod.getMethod();
            //3.通过Method获取Timeout2019注解
            Timeout2019 timeout2019 = method.getAnnotation(Timeout2019.class);
            if(timeout2019!=null){
                //4.获取Timeout2019中的属性
                Object bean = handlerMethod.getBean();
                Long value = timeout2019.value();
                TimeUnit timeUnit = timeout2019.timeUnit();
                String fallback = timeout2019.fallback();
                //5.根据以上数据构造超时间
                Future<Object> future =  executorService.submit(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return  method.invoke(bean);
                    }
                });

                Object returnValue = null;
                try{
                    //6.执行被拦截的方法
                    returnValue = future.get(value,timeUnit);
                }
                catch (TimeoutException e){
                    //7.如果失败，调用fallback方法
                    returnValue =invokeFallbackMethod(handlerMethod,bean,fallback);
                }

                // 8. 返回执行结果（当前实现是存在缺陷的，大家可以尝试通过 HandlerMethodReturnValueHandler 实现）
                response.getWriter().write(String.valueOf(returnValue));
                return false;
            }


        }
        return true;
    }

    private Object invokeFallbackMethod(HandlerMethod handlerMethod, Object bean, String fallback) throws Exception {
        // 7.1 查找 fallback 方法
        Method method = findFallbackMethod(handlerMethod, bean, fallback);
        return method.invoke(bean);
    }

    private Method findFallbackMethod(HandlerMethod handlerMethod, Object bean, String fallbackMethodName) throws NoSuchMethodException {
        // 通过被拦截方法的参数类型列表结合方法名，从同一类中找到 fallback 方法
        Class beanClass = bean.getClass();
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        Class[] parameterTypes = Stream.of(methodParameters)
                .map(MethodParameter::getParameterType) // Class
                .toArray(Class[]::new);                 // Stream<Class> -> Class[]
        Method fallbackMethod = beanClass.getMethod(fallbackMethodName, parameterTypes);
        return fallbackMethod;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
