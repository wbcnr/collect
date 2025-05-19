package org.d13.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos Feign 测试，这是服务提供者
 */
@Slf4j
@RequestMapping("/Nacos/Services/Provider/")
@RestController
public class NacosFeignController {
    // 提供一个简单接口
    @GetMapping("/get")
    public String get() {
        log.info("这是Nacos Feign的服务提供者，NacosFeignController#get()");
        return "service1.BootstrapApp";
    }
    @GetMapping("get/{arg}")
    public String getWithParam(@PathVariable("arg") String arg){
        log.info("这是Nacos Feign的服务提供者，NacosFeignController#getWithParam(), arg = " + arg);
        return "service1.BootstrapApp with param: " + arg;
    }
}
