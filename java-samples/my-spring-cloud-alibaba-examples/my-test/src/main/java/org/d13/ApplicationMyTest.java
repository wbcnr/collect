package org.d13;

import org.d13.entity.Company2Builder;
import org.d13.entity.CompanyBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 这是一个用来做临时测试的模块
 */
@SpringBootApplication
public class ApplicationMyTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMyTest.class, args);
        test();
    }

    /**
     * 测试方法
     */
    public static void test(){
        System.out.println("this is test()");
        CompanyBuilder cb = new CompanyBuilder();
        cb.setName("cb").setEmail("cb@qq.com").build();
        System.out.println("cb.toString : " + cb.toString());

        Company2Builder cb2 = new Company2Builder();
        cb2.setName("cb2").setEmail("cb2@qq.com").build();
        System.out.println("cb2.toString : " + cb2.toString());
    }
}
