package com.example.mympsamples.test;


import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

public class TestConfig {
//    @Bean
//    public ClassA getClassA(){
//        return new ClassA();
//    }
//    @Bean
//    public ClassB getClassB(){
//        return new ClassB();
//    }

    public static void test(){
        AbstractAutowireCapableBeanFactory a = null;
        AutowiredAnnotationBeanPostProcessor b = null;
    }
}
