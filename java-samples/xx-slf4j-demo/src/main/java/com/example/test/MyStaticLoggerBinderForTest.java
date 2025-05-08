package com.example.test;


import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.apache.logging.slf4j.Log4jMarkerFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.impl.StaticMarkerBinder;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * 只为测试 在用 类加载器 加载这个类到内存中后 的 初始化顺序。
 * 网上说的是： 静态（代码块、变量是），实例（实例变量、实例代码块），构造方法。
 * 加载代码放在：MyTest类中。
 */
public class MyStaticLoggerBinderForTest implements LoggerFactoryBinder {

    /**
     * Declare the version of the SLF4J API this implementation is compiled
     * against. The value of this field is usually modified with each release.
     */
    // to avoid constant folding by the compiler, this field must *not* be final
    public static String REQUESTED_API_VERSION = "1.6"; // !final

    private static final String LOGGER_FACTORY_CLASS_STR = Log4jLoggerFactory.class.getName();

    /**
     * The unique instance of this class.
     */
    private static final MyStaticLoggerBinderForTest SINGLETON = new MyStaticLoggerBinderForTest();

    /**
     * The ILoggerFactory instance returned by the {@link #getLoggerFactory}
     * method should always be the same object
     */
    private final ILoggerFactory loggerFactory;

    /**
     * Private constructor to prevent instantiation
     */
    private MyStaticLoggerBinderForTest() {
        System.out.println("MyStaticLoggerBinderForTest.MyStaticLoggerBinderForTest() running");
        loggerFactory = new Log4jLoggerFactory(
                (Log4jMarkerFactory) StaticMarkerBinder.getSingleton().getMarkerFactory());
    }

//    public MyStaticLoggerBinderForTest(String args){
//        System.err.println("public MyStaticLoggerBinderForTest() running.");
//    }

    /**
     * Returns the singleton of this class.
     *
     * @return the StaticLoggerBinder singleton
     */
    public static MyStaticLoggerBinderForTest getSingleton() {
        System.out.println("MyStaticLoggerBinderForTest.getSingleton() running.");
        return SINGLETON;
    }

    /**
     * Returns the factory.
     * @return the factor.
     */
    @Override
    public ILoggerFactory getLoggerFactory() {
        System.out.println("MyStaticLoggerBinderForTest.getLoggerFactory() running.");
        return loggerFactory;
    }

    /**
     * Returns the class name.
     * @return the class name;
     */
    @Override
    public String getLoggerFactoryClassStr() {
        System.out.println("MyStaticLoggerBinderForTest.getLoggerFactoryClassStr() running.");
        return LOGGER_FACTORY_CLASS_STR;
    }
    /**
     * 为了测试添加的静态代码段
     */
    static {
        System.err.println("为了测试添加的静态代码段。");
    }

}
