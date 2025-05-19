package org.d13.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 *  作为服务提供者，供服务消费者调用
 */
@Slf4j
@RequestMapping("/Nacos/Services/Provider/")
@RestController
public class NacosProviderController {


    private final String serverPort;

    private final String applicationName;

    public NacosProviderController(@Value("${server.port:8083}") final String serverPort,
                                   @Value("${spring.application.name:nacos-provider}") final String applicationName) {
        this.serverPort = serverPort;
        this.applicationName = applicationName;
    }

    /**
     * 没有参数的服务提供者
     * @return
     */
    @GetMapping("/demo1")
    public String demo1(){
        log.info("demo1:" + applicationName + ":" + serverPort);
        return "nacos.services.provider.demo1";
    }
    @RequestMapping(value = "/demo2/{arg}", method = RequestMethod.GET )
    public String demo2(@PathVariable("arg") String arg){
        log.info("demo2/" + arg + "->" + applicationName + ":" + serverPort);
        return "nacos.services.provider.demo1" + arg;
    }
}
