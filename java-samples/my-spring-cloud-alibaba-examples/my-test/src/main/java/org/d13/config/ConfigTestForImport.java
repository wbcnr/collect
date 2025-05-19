package org.d13.config;

import org.d13.entity.Person;
import org.d13.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 用于测试注解：@Import
 * 这是注解@Import的第一种用法，将类导入，存入spring容器。
 */
@Configuration
@Import({Student.class})
public class ConfigTestForImport {
//    @Bean
//    public Person person01() {
//        Person person = Person.builder().id(1L).name("shepherd").age(25).build();
//        return person;
//
//    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ConfigTestForImport.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        // 遍历Spring容器中的beanName
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
