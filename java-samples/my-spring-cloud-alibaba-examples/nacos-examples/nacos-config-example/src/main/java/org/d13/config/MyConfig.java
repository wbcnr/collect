package org.d13.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 使用注解：@ConfigurationProperties 和@Refresh完整Nacos配置中心的Bean的自动刷新
 * @author Administrator
 */
@ConfigurationProperties(prefix = "app.user")  // 绑定前缀为user.
@Configuration
public class MyConfig {
    private int age;
    /**
     * user.name无法获取更新配置，可能与系统默认的user.name冲突了。
     */
    private String name;
    private String addr;


    public String getAddr() {
        return addr;
    }


    public void setAddr(String addr) {
        this.addr = addr;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
