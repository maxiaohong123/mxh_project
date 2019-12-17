说明：
1、这是xiaomage的课堂手写实现“spring-cloud-openfeign”的核心源码，帮助需要的同学了解openfeign的实现原理。
2、关键类：
   @EnableRestClients  //enable模块驱动
   @RestClient  //在接口上标注的注解
   RestClientsRegistar.java  //enable模块驱动时的注入类
   RMMInvocationHandler.java //要将接口生成动态代理时的InvocationHandler类。
3、小结openfeign实现原理
   1) 开启模块驱动 @EnableFeignClient
   2) 通过模块驱动发现标注(或过滤)@FeignClient的接口
   3) 将接口生成代理类，并且注入spring容器中。
      代理类的invoke方法中执行以下逻辑：
      3.1) 获取接口上的服务名称
      3.2) 获取接口方法上的@RequestMapping(或@GetMapping或@PostMapping)的路径
      3.3) 根据4、5组装url
      3.4) 利用ribbon的负载均衡机制选择其中的一台服务，发起调用。
   以上步骤完成启动时的初始化工作。
   7) 在**Controller中注入标注@FeignClient的接口。
   8) 在方法中使用以上注入的接口进行远程调用。(实际是采用初始化时代理对象的invoke方法发起调用)。

4、存在问题：
  1)  在自定义的RestClientsRegistar类中进行注入接口生成的代理类时，没有返回真正的代理类，而是返回null，导致项目启动老是报UserRestService没有注入。
