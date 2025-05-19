package org.d13.annotation;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname DemoAspect
 * @description:
 *  这是一个日志注解，以spring的AOP的方式实现的。
 *  会在指定的类的指定方法的执行的前、后、异常、返回等等输入指定的日志。
 *  甚至不需要声明一个注解。
 *  实现方式：
 *  1.pom.xml中引入spring的aop：
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-aop</artifactId>
 *         </dependency>
 *  2. 在pom.xml引入日志相关依赖，以logback为例：
 *          <dependency>
 *             <groupId>org.slf4j</groupId>
 *             <artifactId>slf4j-api</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>ch.qos.logback</groupId>
 *             <artifactId>logback-classic</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>ch.qos.logback</groupId>
 *             <artifactId>logback-core</artifactId>
 *         </dependency>
 *  3.添加此类代码。
 *
 * @author: d
 * @createTime: 2025/05/17 9:19
 * @Version: 1.0
 */
@Aspect
@Component
public class DemoAspect {

    private static final Logger LOG = LoggerFactory.getLogger(DemoAspect.class);
    //指定类的指定方法作为切面。
    @Pointcut("execution(public * org.d13.controller.AOPTestController.TestAOPAnnotation2*(..))")
    public void addAdvice(){}

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        HttpServletRequest requests = null;
        String reqStr = "[null]";
        if (args.length >= 1) {
            requests = (HttpServletRequest) args[0];
            reqStr = requests.getRequestURL().toString();
        }
        LOG.info("before start ...");
        LOG.info("============打印日志开始============");
        LOG.info("URL: " + reqStr);
        LOG.info("============打印日志结束============");
        LOG.info("before end ...");
    }
}
