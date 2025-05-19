package org.d13.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 测试调用服务提供者
 */
@Slf4j
@RequestMapping("/nacos/consummer")
@RestController
public class ControllerForTest {


    private final String SERVICE_NACOS_PROVIDER_URL;

    public ControllerForTest(@Value("${service.nacos.provider.url}") String service_nacos_provider_url) {
        SERVICE_NACOS_PROVIDER_URL = service_nacos_provider_url;
    }

    @RequestMapping(value = "/get/provider/demo1", method = RequestMethod.GET)
    public String callNacosProvider(){
        String retstr = "";
        log.info("/nacos/consummer/get/provider/demo1");
        log.info("Nacos-Consummer.ControllerForTest.callNacosProvider");
        try {
            RestTemplate restTemplate = new RestTemplate();
            retstr = restTemplate.getForObject(new URI(SERVICE_NACOS_PROVIDER_URL), String.class);
            log.info(retstr);
        } catch (URISyntaxException e) {
            log.error("发生异常：");
            e.printStackTrace();
        }

        return retstr;
    }

}
