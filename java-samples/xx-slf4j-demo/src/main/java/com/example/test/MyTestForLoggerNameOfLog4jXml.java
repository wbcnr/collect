package com.example.test;

import com.example.MyLog4j22SLF4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTestForLoggerNameOfLog4jXml {
    private static final Logger log = LoggerFactory.getLogger(MyLog4j22SLF4j.class);
    public void test(){
        log.info("改成./logs");
        log.info("this is test");
        log.error("error 4");
        log.warn("warn");
    }

    public static void main(String[] args) {
        MyTestForLoggerNameOfLog4jXml o = new MyTestForLoggerNameOfLog4jXml();
        o.log.info("this is log4j-slf4j-impl,log4j 2 and slf4j 2");
        o.test();
    }
}
