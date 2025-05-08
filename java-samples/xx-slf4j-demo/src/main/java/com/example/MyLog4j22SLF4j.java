package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog4j22SLF4j {
    final Logger log = LoggerFactory.getLogger(MyLog4j22SLF4j.class);
    public void test(){
        log.info("ver:  ==> 让控制台只输出info及以上级别的日志信息！");
        log.info("ver: 2024-1-4 10:32:04 ==> 让控制台只输出info及以上级别的日志信息！");
        log.info("configuration root logger appenders 4者之间的level关系\n" +
                " configuration > root > logger > appenders. 因为数据流向是这样的。");
        log.info("ver:2024-1-4 10:11:46 （File.append=true）  ==> 解决日志总是覆盖的问题。让日志接着输出！");
        log.info("in test ver: 2024-1-4 10:04:42");
        log.info("设置 logger.level = all");
        log.info("this is slf4j + log4j2.x info.");
        log.error("this is slf4j + log4j2.x error.");
        log.warn("this is slf4j + log4j2.x warn.");
        log.trace("this is slf4j + log4j2.x trace.");
        log.info("this is slf4j + log4j2.x debug.");
        String s = log.toString();
        log.info("this is slf4j + log4j " + s);
    }

    public static void main(String[] args) {
        MyLog4j22SLF4j o = new MyLog4j22SLF4j();
        o.log.info("this is log4j-slf4j-impl,log4j 2 and slf4j 2");
        o.test();
    }
}
