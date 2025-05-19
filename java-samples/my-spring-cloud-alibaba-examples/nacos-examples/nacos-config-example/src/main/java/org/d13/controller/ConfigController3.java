package org.d13.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试：多环境配置共享
 * 公共环境：    demo.title =
 * 生产环境：    demo.multiEnValue =
 * 测试环境：    demo.multiEnValue =
 */
@RestController
@RefreshScope
public class ConfigController3 {
    //必须得有默认值，否则会报错。
    @Value("${demo.title:demo-title}")
    private String title;
    @Value("${demo.multiEnValue:多环境值}")
    private String multiEnValue;
    @GetMapping("/demo3")
    public Map<String,Object> demo3() {
        return new HashMap<String,Object>(){
            {
                put("title", title);
                put("multiEnValue", multiEnValue);
            }
        };
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
