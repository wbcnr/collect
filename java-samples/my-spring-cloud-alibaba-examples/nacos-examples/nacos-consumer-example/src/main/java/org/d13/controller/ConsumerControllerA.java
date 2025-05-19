package org.d13.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 这是Nacos上的例子
 */
@RestController
public class ConsumerControllerA {

    private final RestTemplate restTemplateWithLoadBalanced;

    /**
     * 如何设置强制绑定restTemplateWithLoadBalanced返回的RestTemplate
     * @param restTemplateWithLoadBalanced
     */
    public ConsumerControllerA(RestTemplate restTemplateWithLoadBalanced) {
        this.restTemplateWithLoadBalanced = restTemplateWithLoadBalanced;
    }

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplateWithLoadBalanced.getForObject("http://service-provider/echo/" + str, String.class);
    }

}
