package com.mxh.pdc.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * Created by Maxiaohong on 2019-12-01.
 */
public class RestClientsRegistrar2018 implements ImportBeanDefinitionRegistrar,BeanFactoryAware,EnvironmentAware {

    private BeanFactory beanFactory;

    private Environment environment;
    /**
     *
     * @param metadata  元注解  ，表示标此注解的类
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
           ClassLoader classLoader = metadata.getClass().getClassLoader();
           Map<String,Object> attributes = metadata.getAnnotationAttributes(EnableRestClients2018.class.getName());
           //attributes--> key: clients  value :UserRestService
           Class<?>[] clientsClass = (Class<?>[]) attributes.get("clients");

           //接口类对象数组
            //筛选所有接口
           Stream.of(clientsClass)
                   .filter(Class::isInterface)  //仅选择接口
//                   .filter(interfaceClass ->{
//                       return findAnnotation(interfaceClass,RestClient2018.class)!=null;  //仅选择标注  @RestClient2018
//                   })
                   .filter(interfaceClass->findAnnotation(interfaceClass,RestClient2018.class)!=null)
                   .forEach(restClientClass->{
                       // 获取@RestClient2018元信息
                        RestClient2018 restClient2018 =  findAnnotation(restClientClass,RestClient2018.class);
                        //获取应用名称
//                        String serviceName = restClient2018.name(); //静态写法
                         String serviceName = environment.resolvePlaceholders(restClient2018.name());
                        //组装URL  RestTemplate :    serviceName/uri?param


                       //@RestClient 接口编程，生成接口代理 ，采用JDK动态代理
                         Object proxy =   Proxy.newProxyInstance(classLoader, new Class[]{restClientClass}, new RequestMappingInvocationHandler2018(serviceName,beanFactory));
//                        String beanName = "RestClient2018."+serviceName;
                        registerBeanByFactoryBean(serviceName,proxy,restClientClass,registry);
                   });



    }

    @Override
    public void setEnvironment(Environment environment) {
       this.environment = environment;
    }


    private static void registerBeanByFactoryBean(String serviceName,Object proxy,Class<?> restClientClass,BeanDefinitionRegistry registry){
        //将@RestClient 接口代理实现注册为Bean(@Autowired)
        String beanName = "RestClient2018."+serviceName;
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactoryBean.class);
        //以下参数构造器引用相当于
        //<bean id="xxx><construtor name="xxx"></bean>
        //增加第一个参数构造器引用：proxy
        beanDefinitionBuilder.addConstructorArgValue(proxy);
        //增加第二个构造器参数引用：restClientClass
        beanDefinitionBuilder.addConstructorArgValue(restClientClass);


        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        registry.registerBeanDefinition(beanName,beanDefinition);
    }

    private static class RestClientClassFactoryBean implements FactoryBean{

        private final Object proxy;
        private final Class<?> restClientClass;

        private RestClientClassFactoryBean(Object proxy, Class<?> restClientClass) {
            this.proxy = proxy;
            this.restClientClass = restClientClass;
        }

        @Nullable
        @Override
        public Object getObject() throws Exception {
            return proxy;   // 注意：这里一定要返回proxy,不能返回null,否则注入的对象为null,启动会报错。
        }

        @Nullable
        @Override
        public Class<?> getObjectType() {
            return restClientClass;
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
