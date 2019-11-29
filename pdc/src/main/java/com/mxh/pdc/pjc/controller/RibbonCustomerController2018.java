package com.mxh.pdc.pjc.controller;

import com.mxh.pdc.pjc.annotation.CustomizedLoadBalanced2018;
import com.mxh.pdc.pjc.loadblance.LoadBalancedRequestInterceptor2018;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description :2018年 下  ribbon负载均衡原理(RestTemplate+自定义实现)  45：46秒
 * @param
 * @return
 */
@RestController
public class RibbonCustomerController2018 {
    @Autowired // 依赖注入自定义 RestTemplate Bean
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced // 依赖注入 Ribbon RestTemplate Bean (如果有自定义RestTemplate时，此处需要加@LoadBalanced注解)
    private RestTemplate lbRestTemplate;


    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${pjc.name}")
    private String serviceName;

    // 自定义RestTemplate
    @RequestMapping("/invoke/{serviceName}/say")//pdc-->pjc
    public String invokeSay(@PathVariable String serviceName,@RequestParam String name){
        return restTemplate.getForObject(serviceName+"/user/say?name="+name,String.class);

    }

    //ribbon RestTemplate
    @RequestMapping("/lb/{serviceName}/say")//pdc-->pjc
    public String lbInvokeSay(@PathVariable String serviceName,@RequestParam String name){
        return lbRestTemplate.getForObject("http://"+serviceName+"/user/say?name="+name,String.class);

    }

    //--------以下是手写实现负载均衡的第四版-start---------
    //自定义拦截器需注入
//    @Bean
//    public ClientHttpRequestInterceptor interceptor() {
//        return new LoadBalancedRequestInterceptor2018();
//    }
//
//    // @Bean+RestTemplate+自定义拦截器(自定义负载均衡实现)
//    // 自定义RestTemplate
//    @Bean
//    @Autowired
//    @CustomizedLoadBalanced2018  //此处如果采用@Qualifire注解，会影响到Ribbon RestTemplate，因为@Qualifire具有派生性，我们自已定义一个注解就ok了。
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
//
//    @Bean
//    @Autowired
//    public Object customizer(@CustomizedLoadBalanced2018 Collection<RestTemplate> restTemplates,
//                             ClientHttpRequestInterceptor interceptor) {
//        restTemplates.forEach(r -> {
//            r.setInterceptors(Arrays.asList(interceptor));
//        });
//        return new Object();
//    }
    //--------以下是手写实现负载均衡的第四版-end---------



    //--------以下是手写实现负载均衡的第三版-start---------
    // 该版本采用自定义拦截器实现；核心拦截器ClientHttpRequestInterceptor ，在请求前进行拦截，然后处理业务(获取请求路径、获取所有服务列表、根据负载均衡算法选择其中一台、建立连接、返回结果)
//    @RequestMapping("/invoke/{serviceName}/say")//pdc-->pjc
//    public String invokeSay(@PathVariable String serviceName,@RequestParam String name){
//        return restTemplate.getForObject(serviceName+"/user/say?name="+name,String.class);
//
//    }
//
//    //自定义拦截器需注入
//    @Bean
//    public ClientHttpRequestInterceptor interceptor() {
//        return new LoadBalancedRequestInterceptor2018();
//    }
//
//    @Bean+RestTemplate+自定义拦截器(服务列表更新、自定义负载均衡实现)
//    @Bean
//    public RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor){  //通过构造函数依赖注入
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Arrays.asList(interceptor));
//        return restTemplate;
//    }
//--------以下是手写实现负载均衡的第三版-end---------



    //--------以下是手写实现负载均衡的第二版-start-------
//    private volatile Map<String,Set<String>> targetUrlsCache = new HashMap<>();
//    @Scheduled(fixedRate = 10*1000) //10秒更新一次缓存
//    public  void updateTargetUrlsCache(){
//        //获取当前服务的所有实例表
//        //  http://ip:port
//        Map<String,Set<String>> newTargetUrlsCache = new HashMap<>();
//
//        discoveryClient.getServices().forEach(serviceName->{
//           List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
//            Set<String> newTargetUrls =  serviceInstances
//                    .stream()
//                    .map(s->
//                            s.isSecure()?
//                                    "https://"+s.getHost()+":"+s.getPort():
//                                    "http://"+s.getHost()+":"+s.getPort()
//                    ).collect(Collectors.toSet());
//            newTargetUrlsCache.put(serviceName,newTargetUrls);
//        });
//        //swap
//         this.targetUrlsCache = newTargetUrlsCache;
//    }
//
//    @RequestMapping("/invoke/{serviceName}/say")//pdc-->pjc
//    public String invokeSay(@PathVariable String serviceName,@RequestParam String name){
//        //2、轮询列表
//        List<String> targetUrls = new LinkedList<>(targetUrlsCache.get(serviceName));  //copyonwrite、保证线程安全
//        int size = targetUrls.size();
//        int index = new Random().nextInt(size);
//        //3、选择其中一台服务器
//        String targetUrl = targetUrls.get(index);
//        //4、RestTemplate发送请求到服务器
//        //5、输出响应
//
//        return restTemplate.getForObject(targetUrl+"/user/say?name="+name,String.class);
//
//    }

    //注意：此处的RestTemplate仅仅是做调用，并没有负载均衡的作用。因为是自己实现负载均衡，所以没有加@LoadBalanced；
// @Bean+RestTemplate+自定义负载均衡实现  表示自己实现负载均衡
// @Bean+@LoadBalanced+RestTemplate  表示使用ribbon的负载均衡
//    @Bean
//    public RestTemplate restTemplate(){
//        return  new RestTemplate();
//    }
//--------以下是手写实现负载均衡的第二版-end-------


    //--------以下是手写实现负载均衡的第一版-start-------
//    private volatile Set<String> targetUrls = new HashSet<>();
//    @Scheduled(fixedRate = 10*1000) //10秒更新一次缓存
//    public  void updateTargetUrls(){
//        //获取当前服务的所有实例表
//        //  http://ip:port
//        Set<String> oldTargetUrls = this.targetUrls;
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
//        Set<String> newTargetUrls =  serviceInstances
//                .stream()
//                .map(s->
//                        s.isSecure()?
//                        "https://"+s.getHost()+":"+s.getPort():
//                        "http://"+s.getHost()+":"+s.getPort()
//                ).collect(Collectors.toSet());
//        //swap
//         this.targetUrls = newTargetUrls;
//         //help gc
//        oldTargetUrls.clear();
//    }

//    @RequestMapping("/invoke/say")//pdc-->pjc
//    public String invokeSay(@RequestParam String name){
//
//        //2、轮询列表
//        List<String> targetUrls = new ArrayList<>(this.targetUrls);  //copyonwrite、保证线程安全
//        int size = targetUrls.size();
//        int index = new Random().nextInt(size);
//        //3、选择其中一台服务器
//        String targetUrl = targetUrls.get(index);
//        //4、RestTemplate发送请求到服务器
//        //5、输出响应
//        return restTemplate.getForObject(targetUrl+"/user/hello?name="+name,String.class);
//
//    }

//注意：此处的RestTemplate仅仅是做调用，并没有负载均衡的作用。因为是自己实现负载均衡，所以没有加@LoadBalanced；
// @Bean+RestTemplate+自定义负载均衡实现  表示自己实现负载均衡
// @Bean+@LoadBalanced+RestTemplate  表示使用ribbon的负载均衡
//    @Bean
//    public RestTemplate restTemplate(){
//        return  new RestTemplate();
//    }
// -------------以下是手写实现负载均衡的第一版-end---------------


    @RequestMapping("/say")
    public String say(@RequestParam String name){
        System.out.println(name);
        return "hello:"+name;
    }


    //自定义拦截器需注入
    @Bean
    public ClientHttpRequestInterceptor interceptor() {
        return new LoadBalancedRequestInterceptor2018();
    }

    // @Bean+RestTemplate+自定义拦截器(自定义负载均衡实现)
    // 自定义RestTemplate
    @Bean
//    @Autowired
    @CustomizedLoadBalanced2018
    public RestTemplate restTemplate(){  //通过构造函数依赖注入
         return new RestTemplate();
    }

    @Bean
//    @Autowired
    public Object customizer(@CustomizedLoadBalanced2018 Collection<RestTemplate> restTemplates,
                             ClientHttpRequestInterceptor interceptor) {
        restTemplates.forEach(r -> {
            r.setInterceptors(Arrays.asList(interceptor));
        });
        return new Object();
    }

    //ribbon RestTemplate
    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){
        return  new RestTemplate();
    }

}
