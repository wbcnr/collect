package org.d13.controller;

import lombok.extern.slf4j.Slf4j;
import org.d13.service.NacosFeignServiceAgent1;
import org.d13.service.NacosProviderServiceAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发现并 使用Feign 调用服务
 * @author Administrator
 */
@Slf4j
@RequestMapping("/nacos/consummer")
@RestController
public class NacosFeignController{
    /**
     * 不连接 nacos
     */
    @Autowired
    private  NacosFeignServiceAgent1 nacosFeignServiceAgent1;


    @GetMapping("/get")
    public String get() {
        log.info("service-consumer:NacosFeignController#get running.");
        // 通过代理类调用远程接口
        return "service2.invoke = " + nacosFeignServiceAgent1.get();
    }
    @GetMapping("/get/{arg}")
    public String get2(@PathVariable("arg") String arg) {
        log.info("service-consumer:NacosFeignController#get2 running.");
        // 通过代理类调用远程接口
        return "service2.invoke = " + nacosFeignServiceAgent1.getWithParam(arg);
    }

}
