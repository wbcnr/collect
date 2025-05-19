package org.d13.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/config")
@RestController
@RefreshScope
public class ConfigController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @Value("${user.name:xxx}")
    private String username;

    @Value("${user.age:0}")
    private int age;


    @RequestMapping("/get")
    public String get() {
        System.out.println("useLocalCache = " + useLocalCache +
                ",user.name = " + username +
                ",user.age = " + age);

        return "useLocalCache = " + useLocalCache +
                ",user.name = " + username +
                ",user.age = " + age;
    }

    /**
     * 测试方法

     curl -d 'dataId=nacos-config-example.properties' \
     -d 'group=DEFAULT_GROUP' \
     -d 'content=user.age=97\n' \
     -d 'content=user.name=lilei\n' \
     -d 'content=useLocalCache=true' \
     -X POST 'http://127.0.0.1:8848/nacos/v1/cs/configs'

     curl http://localhost:8081/config/get

     curl -d 'dataId=nacos-config-example.properties' \
     -d 'group=DEFAULT_GROUP' \
     -d $'content=app.user.age=68 \napp.user.name=TOM \nuseLocalCache=true \napp.user.addr=diqiu' \
     -X POST 'http://127.0.0.1:8848/nacos/v1/cs/configs'

     *
     */
}
