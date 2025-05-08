package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySLF4JWithLog4j1XApplication {
    final  Logger log = LoggerFactory.getLogger(MySLF4JWithLog4j1XApplication.class);
    public void testSLF4JWithLog4j(){
        log.info("this is slf4j + log4j info.");
        log.error("this is slf4j + log4j error.");
        log.warn("this is slf4j + log4j warn.");
        log.trace("this is slf4j + log4j trace.");
        log.debug("this is slf4j + log4j debug.");
        String s = log.toString();
        log.info("this is slf4j + log4j " + s);
    }

    public static void main(String[] args) {
        MySLF4JWithLog4j1XApplication o = new MySLF4JWithLog4j1XApplication();
        o.testSLF4JWithLog4j();
    }
}
