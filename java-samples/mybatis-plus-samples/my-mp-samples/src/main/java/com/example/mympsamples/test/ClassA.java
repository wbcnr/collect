package com.example.mympsamples.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试，构造器注入的循环依赖，是否会导致程序启动报错？
 */

@Component
public class ClassA {
//    @Autowired
//    ClassB b;
}
