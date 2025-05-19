package org.d13.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标签：《自定义注解处理器2025-4-14 10:46:36》
 *
 * 用来完成自定义java注解，spring注解。
 * java注解处理器：编译时处理，运行时处理
 * spring注解处理器：AOP、
 *
 * 注解功能：记录日志
 * 注解元素： message ： 日志信息，
 *          logTime ： 是否记录日志
 * @author d
 * @date 2025-4-14
 */
@Retention(RetentionPolicy.RUNTIME) // 运行时可用
@Target(ElementType.METHOD)        // 只能标记方法
public @interface Loggable {

    String message() default "Method executed";
    boolean logTime() default true;
}
