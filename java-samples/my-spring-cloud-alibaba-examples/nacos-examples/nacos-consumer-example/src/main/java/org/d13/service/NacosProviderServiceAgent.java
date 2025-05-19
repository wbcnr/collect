package org.d13.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 这个版本是要从服务中心获取服务配置信息的
 */
@FeignClient(name = "nacos-provider111")
public interface NacosProviderServiceAgent {
    // 表示接口地址
    @GetMapping("/Nacos/Services/Provider/get")
    //这个抽象方法根据什么定义？是被调用服务的方法名称吗？
    public String get();
    @GetMapping("/Nacos/Services/Provider/get/{arg}")
    public String getWithParam(@PathVariable("arg") String arg);
}
