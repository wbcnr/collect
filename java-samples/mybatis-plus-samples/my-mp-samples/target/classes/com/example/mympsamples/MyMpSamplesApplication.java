package com.example.mympsamples;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.creator.druid.DruidConfig;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.example.mympsamples.controller.UserListController;
import com.example.mympsamples.entity.UserList;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.example.mympsamples.mapper")
@Slf4j
public class MyMpSamplesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext =
                SpringApplication.run(MyMpSamplesApplication.class, args);
        //给测试方法传递上下文参数
        log.info("===================================");
        //getAllBeansTest(appContext);
        //getBeansWithTypeDataSource(appContext);
        MyMpSamplesApplication bean =
                appContext.getBean(MyMpSamplesApplication.class);
//        DynamicDataSourceAutoConfiguration bean =
//                appContext.getBean(DynamicDataSourceAutoConfiguration.class);
        log.info("===================================");
    }

    /**
     *  获取所有DataSource类型的bean
     */
    public static void getBeansWithTypeDataSource(ConfigurableApplicationContext context){
        Map<String, DataSource> maps = context.getBeansOfType(DataSource.class);
        for (Map.Entry<String, DataSource> entry : maps.entrySet()){
            log.info("DataSource Beans : {}", entry);
        }
        //DynamicDataSourceProperties
        Map<String, DynamicDataSourceProperties> maps1 = context.getBeansOfType(DynamicDataSourceProperties.class);
        for (Map.Entry<String, DynamicDataSourceProperties> entry : maps1.entrySet()){
            log.info("DynamicDataSourceProperties Beans : {}", entry);
        }
        log.info("---");
    }

    /**
     * 获取所有的已经加载的bean，然后过滤出所有与druid有关的bean
     * @param context
     */
    public static void getAllBeansTest(ConfigurableApplicationContext context){
        Map<String, Object> maps =
                context.getBeansOfType(Object.class);
        Map<String, Object> mapsOfDruid = new HashMap<>();
        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            String beanName = entry.getKey();
            if (beanName.contains("druid")){
                mapsOfDruid.put(entry.getKey(), entry.getValue());
            }
            Object beanClass = entry.getValue();
            //log.info("beanname:{},beanclass:{}", beanName, beanClass.toString());
            // 可以在这里调用UserService的方法，例如
        }
        //输出过滤后的map
        for (Map.Entry<String, Object> entry : mapsOfDruid.entrySet()){
            log.info("bean_name:{}, bean_class:{}", entry.getKey(), entry.getValue());
        }
        log.info("===================================");
    }

}
