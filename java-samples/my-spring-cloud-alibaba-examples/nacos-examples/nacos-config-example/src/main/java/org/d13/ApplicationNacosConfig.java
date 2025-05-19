package org.d13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class ApplicationNacosConfig {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(ApplicationNacosConfig.class, args);

        while(true) {
            String title = applicationContext.getEnvironment().getProperty("demo.title");
            String multiEnValue = applicationContext.getEnvironment().getProperty("demo.multiEnValue");
            System.err.println("demo.multiEnValue = "+ multiEnValue);
            System.err.println("demo.title = "+ title);
            TimeUnit.SECONDS.sleep(1);
        }
    }



}
