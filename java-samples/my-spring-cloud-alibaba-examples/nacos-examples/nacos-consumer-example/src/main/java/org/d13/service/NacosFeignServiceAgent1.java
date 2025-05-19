package org.d13.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用Figen的服务的注册
 * 使用方法：
 *  声明一个接口，这个接口代表要调用的服务；
 *  注解@FeignClient("nacos-provider") 的参数是服务提供者在nacos中的服务名称；
 *  定义服务提供方的方法对应的抽象方法；
 *      抽象方法get() 是服务提供者的 路径名称还是方法名称？
 *
 *  1. @FeignClient(value = "nacos-provider", url = "/test/") 中的url如果不填写启动应用会报异常。
 *  这是一个什么道理？
 *  url 是 想要调用的服务的客户端的服务名称，在我的测试demo中就是nacos-provider
 *
 *  Feign的本质是一个http客户端，所以注解@FeignClient注解需要知道被调用服务的URL（即：ip:port/url），
 *  从这个理解，那@FeignClient的value（或者name）参数填写的就是被注解接口的取的一个名字而已。因为有url在，
 *  也不会从服务的注册中心获取服务名称，所以这个应该是这个接口类的名称。但是为了便于理解，value/name还是应该写成
 *  服务提供方的application.name，便于构架。
 *
 *  测试方案一：@FeignClient(value = "随便取一个名称，代表接口的唯一的名称", url = "被调用方的完整的url")，
 *      @FeignClient(value = "NacosFeignServiceAgent1",
 *          url = "http://127.0.0.1:8848/Nacos/Services/Provider/")
 *  测试结果：
 *  方案二：url设置为注解ip和端口，具体方法上加url
 *  方案三：url设置为“”， 具体方法上加ip:port/url
 *
 * 这个版本是不从 nacos 获取服务配置信息的
 *
 * @author Administrator
 */
// 这种方式可以调用到服务提供者，但是应该是没有经过服务注册中心查询服务，因为url是写死的
//@FeignClient(value = "NacosFeignServiceAgent1", url = "http://127.0.0.1:8083/Nacos/Services/Provider/")
@FeignClient(value = "nacos-provider")
public interface NacosFeignServiceAgent1 {
    //如何定义这个接口路径？ 在Controller中也定义了uri，是不是重复了？
    // 表示接口地址
    @GetMapping("/Nacos/Services/Provider/get")
    //这个抽象方法根据什么定义？是被调用服务的方法名称吗？
    public String get();
    @GetMapping("/Nacos/Services/Provider/get/{arg}")
    public String getWithParam(@PathVariable("arg") String arg);
}