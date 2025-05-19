package org.d13.controller;

import org.d13.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

/**
 * 自动刷新，使用：ConfigurationProperties
 */
@RequestMapping("/config")
@RestController
public class ConfigController2 {

    @Autowired
    private MyConfig myConfig;

//    public ConfigController2(MyConfig myConfig) {
//        this.myConfig = myConfig;
//    }

    @GetMapping("/refresh/configurationproperties")
    public String demo() {
        Map<String, String> envMap = System.getenv();
        Properties sysProperties = System.getProperties();
        System.out.println("envMap : " + envMap);
        System.out.println("sysProperties" + sysProperties);
        String msg = String.format("user.name:%s, user.age:%d, user.addr:%s",
                myConfig.getName(),myConfig.getAge(), myConfig.getAddr());
        System.out.println(msg);
        return msg;
    }

}
