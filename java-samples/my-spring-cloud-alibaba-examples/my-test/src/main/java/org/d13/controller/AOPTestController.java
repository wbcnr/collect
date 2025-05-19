package org.d13.controller;

import lombok.extern.slf4j.Slf4j;
import org.d13.annotation.LogExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名：AOPTestController
 * 作用：测试AOP注解
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/Test/AOP/")
public class AOPTestController {

    @LogExecutionTime
    @GetMapping(path = "/LogExecutionTime")
    public void TestAOPAnnotation(){
        System.out.println ("TestAOPAnnotation : 测是AOP注解。");
        log.info ( "TestAOPAnnotation : 测是AOP注解。" );
    }
    @GetMapping(path = "/Annotation")
    public void TestAOPAnnotation2(){
        log.info("正在执行方法: public class AOPTestController#TestAOPAnnotation2().");
        log.info("在这个方法执行的前后，会执行AOP方式实现的日志。");
    }
}
