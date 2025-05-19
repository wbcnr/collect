package org.d13.entity;

import org.d13.annotation.BuildProperty2;

/**
 * 标签：《自定义注解处理器2025-4-14 10:46:36》
 * 这是一个实体类，用于测试自定义注解的，会引入custom-processor-with-processor的自定义注解:@BuildProperty
 * 这个是测试由@AutoService实现的自定以注解处理器的。
 */

public class Company2 {
    private String name;

    private String email ;

    @BuildProperty2
    public void setName( String name ) {
        this.name = name;
    }
    @BuildProperty2
    public void setEmail( String email ) {
        this.email = email;
    }
}