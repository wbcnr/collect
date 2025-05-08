package com.example.test;

import org.apache.logging.log4j.spi.AbstractLoggerAdapter;
import org.slf4j.helpers.Util;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

public class MyTest {

    static final String JAVA_VENDOR_PROPERTY = "java.vendor.url";
    private static String MY_STATIC_LOGGER_BINDER_PATH =
            "com/example/test/MyStaticLoggerBinderForTest.class";
    //测试是否是安卓系统
    public static String safeGetSystemProperty(String key) {
        if (key == null)
            throw new IllegalArgumentException("null input");

        String result = null;
        try {
            result = System.getProperty(key);
        } catch (java.lang.SecurityException sm) {
            ; // ignore
        }
        return result;
    }

    public static boolean isAndroid(){
        String vendor = MyTest.safeGetSystemProperty(MyTest.JAVA_VENDOR_PROPERTY);

        boolean isAndroid = vendor.toLowerCase().contains("android");
        //输出的是：http://java.oracle.com/
        System.out.println(vendor);
        System.out.println(isAndroid);
        return isAndroid;
    }
    //
    public static void testStaticLoggerBinderPathSet(){
        Set<URL> staticLoggerBinderPathSet =
                MyLoggerFactoryOfSlf4j.findPossibleStaticLoggerBinderPathSet();

        //通过迭代器循环遍历
        Iterator<URL> iterators = staticLoggerBinderPathSet.iterator();
        System.out.println("\r\n" + "-----利用for循环-----" + "\r\n");
        for (URL url : staticLoggerBinderPathSet){
            System.out.println(url);
        }
        System.out.println("\r\n" + "-----利用Lambda表达式的foreach-----" + "\r\n");
        staticLoggerBinderPathSet.forEach(obj -> System.out.println(obj.toString()));
        System.out.println("\r\n" + "-----利用while循环输出-----" + "\r\n");
        while(iterators.hasNext()){
            System.out.println(iterators.next());
        }
    }

    public void test(){
        Iterable iterable = null;

    }

    /**
     * 测试获用类加载器加载MyStaticLoggerBinderForTest.class
     * @return
     */
    static Set<URL> findPossibleStaticLoggerBinderPathSet() {
        // use Set instead of list in order to deal with bug #138
        // LinkedHashSet appropriate here because it preserves insertion order
        // during iteration
        Set<URL> staticLoggerBinderPathSet = new LinkedHashSet<URL>();
        try {
            ClassLoader myTestClassLoader = MyTest.class.getClassLoader();
            Enumeration<URL> paths;
            if (myTestClassLoader == null) {
                paths = ClassLoader.getSystemResources(MY_STATIC_LOGGER_BINDER_PATH);
            } else {
                paths = myTestClassLoader.getResources(MY_STATIC_LOGGER_BINDER_PATH);
            }
            while (paths.hasMoreElements()) {
                URL path = paths.nextElement();
                staticLoggerBinderPathSet.add(path);
            }
        } catch (IOException ioe) {
            //Util.report("Error getting resources from path", ioe);
            System.err.println("Error getting resources from path" + ioe);
        }
        return staticLoggerBinderPathSet;
    }

    public void test0 (){
        //这段代码是什么意思？
        //private static final Predicate<Class<?>> CALLER_PREDICATE = clazz ->
        //            !AbstractLoggerAdapter.class.equals(clazz) && !clazz.getName().startsWith(SLF4J_PACKAGE);

        Predicate<Class<?>> CALLER_PREDICATE = clazz -> !AbstractLoggerAdapter.class.equals(clazz) && !clazz.getName().startsWith("org.slf4j");





    }

    public static void main(String[] args) {
        //MyStaticLoggerBinderForTest.getSingleton();
        Iterable iterable = null;

        try {
            Class<?> clazz = Class.forName("com.example.test.MyStaticLoggerBinderForTest");
            // 打印加载的类信息
            System.out.println("Loaded class: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

