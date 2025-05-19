package org.d13.test;

import org.d13.annotation.EnableMyCache;
import org.d13.entity.Student;
import org.d13.entity.Student2;
import org.d13.entity.UserConfig;
import org.d13.service.CacheService;
import org.d13.support.CacheType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.sql.SQLSyntaxErrorException;

//根据不同的注解参数，注入不同的缓存
//@EnableMyCache(type = CacheType.LOCAL)
@EnableMyCache(type = CacheType.REDIS)
@SpringBootTest
@RunWith(SpringRunner.class)
public class AnnotationImportTest {
    @Autowired
    private Student student;
//    @Autowired
//    private Student2 student2;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserConfig userConfig;

    @Test
    public void testUserConfig() {
        String username = userConfig.getUsername();
        System.out.println("username = " + username);
        Field[] fields = userConfig.getClass().getDeclaredFields();
        for (Field field : fields){
            System.out.println(field.getName());
        }
    }


    @Test
    public void test() {
        cacheService.setData("key");
    }

//    @Test
//    public void getStudent2() {
//        String name = student2.getClass().getName();
//        System.out.println("name2 = " + name);
//    }

    @Test
    public void getStudent() {
        String name = student.getClass().getName();
        System.out.println("name = " + name);
    }

    public void ttt(){
        ConfigurationClassPostProcessor c = null;

    }

}
