package org.d13.annotation;

import org.d13.service.impl.CustomAnnotationServiceImpl;

import java.lang.reflect.Method;

/**
 * 标签：《自定义注解处理器2025-4-14 10:46:36》
 *
 * 注解处理器：通过发射的方式处理注解@Loggable；
 *
 * 正常情况下，这个类中的main方法，应该换成java或者第三方框架调用的方法，或者这个类换成被调用的类。
 * 还需要注意这个类的方法的调用时机，需要在调用被@Loggable注解的方法的时候，才被调用。
 * @author d
 * @date 2025-4-14
 */
public class LoggableProcessorByReflection {
    //处理注解
    public void parseMethod(final Class<?> clazz) throws  Exception{

        //实例化这个类clazz
        final Object obj = clazz.getConstructor ( new Class[]{} ).newInstance ( new Object []{} );
        //获取所有实例对象的所有方法
        final Method[] methods = clazz.getDeclaredMethods ();
        for (final Method method : methods){
            //获取方法上的注解
            final Loggable loggable = method.getAnnotation (Loggable.class);
            //如果注解不为空，说明这个是被Loggable注解的方法
            if (loggable != null){
                String message = loggable.message();
                boolean logTime = loggable.logTime();
                System.out.println("Log: " + message);
                if (logTime) {
                    System.out.println("Execution time: " + System.currentTimeMillis());
                }
                //调用被注解的方法
                method.invoke ( obj, loggable.getClass().getSimpleName () );
            }
        }
    }
    /**
     * 测试方法
     * @param args
     */
    public static void main( String[] args ) throws  Exception{
        LoggableProcessorByReflection loggableProcessorByReflection = new LoggableProcessorByReflection ();
        loggableProcessorByReflection.parseMethod( CustomAnnotationServiceImpl.class );
    }
}
