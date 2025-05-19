package org.d13.config;

import org.d13.service.NacosFeignServiceAgent1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置类
 */
@Configuration
public class ConsumerConfig {
    //实例化 RestTemplate 实例
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplateWithLoadBalanced(){
//        return new RestTemplate();
//    }
//    @Bean
//    public NacosFeignServiceAgent1 nacosFeignServiceAgent1(){
//        return new NacosFeignServiceAgent1() {
//            @Override
//            public String get() {
//                return null;
//            }
//
//            @Override
//            public String getWithParam(String arg) {
//                return null;
//            }
//        };
//    }
}
