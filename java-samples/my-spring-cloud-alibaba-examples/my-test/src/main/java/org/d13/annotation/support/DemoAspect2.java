package org.d13.annotation.support;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.d13.annotation.DemoAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname DemoAspect2
 * @description:
 *  作用：在注解：LogExecutionTime 标注的方法作为切面，执行相关的日志操作。是以AOP的方式实现的。
 *  实现方式：   参考类DemoAspect中的说明
 *
 * @author: d
 * @createTime: 2025/05/17 21:28
 * @Version: 1.0
 */
@Aspect
@Component
public class DemoAspect2 {
    private static final Logger LOG = LoggerFactory.getLogger(DemoAspect.class);
    //声明切面
    // 注意：注解的类用全量名，为了避免不必要的错误。
    //第一种方式：指定类方法 + 指定的注解
    //@Pointcut("execution(public * org.d13.controller.AOPTestController.TestAOPAnnotation*(..)) && @annotation(org.d13.annotation.LogExecutionTime)")
    //第二种方式：指定的注解
    @Pointcut("@annotation(org.d13.annotation.LogExecutionTime)")
    public void addAdvice(){}

    @Before("addAdvice()")
    public void before(JoinPoint joinPoint){
        //String msg = "execution(public * org.d13.controller.AOPTestController.TestAOPAnnotation*(..)) && " +
        //        "@annotation(org.d13.annotation.LogExecutionTime)";
        String msg1 = "@Pointcut(\"@annotation(org.d13.annotation.LogExecutionTime)\")";

        Object[] args = joinPoint.getArgs();
        HttpServletRequest requests = null;
        String reqStr = "[null]";
        if (args.length >= 1) {
            requests = (HttpServletRequest) args[0];
            reqStr = requests.getRequestURL().toString();
        }
        LOG.info("以 " + msg1 + " 的方式实现AOP注解。");
        LOG.info("before start ...");
        LOG.info("============打印日志开始============");
        LOG.info("URL: " + reqStr);
        LOG.info("============打印日志结束============");
        LOG.info("before end ...");
    }
}
