package com.example;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class slf4japplication {
    public static final Logger LOGGER= LoggerFactory.getLogger(slf4japplication.class);
    @Test
    public void testLog(){
        //日志输出
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        //使用占位符输出日志信息
        String name="xiaoxu";
        Integer age=34;
        //slf4j的占位符，不用在{}中输入0、1、2等等
        LOGGER.info("用户：{}，年龄：{}",name,age);

        //将系统的异常信息输出
        try {
            int i=1/0;
        } catch (Exception e) {
//            e.printStackTrace();
//          LOGGER中输出，可以逗号隔开（类似python的print），但是System.out.println只能+拼接
            LOGGER.error("出现异常：",e);
        }
    }
}
