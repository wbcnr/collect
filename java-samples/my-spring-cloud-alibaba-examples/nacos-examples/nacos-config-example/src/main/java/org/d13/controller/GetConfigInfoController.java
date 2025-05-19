package org.d13.controller;

import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 测试调用NacosFactory获取配置
 */
@RequestMapping("/config")
@RestController
public class GetConfigInfoController {

    // Nacos 地址
    private String serverAddr = "127.0.0.1:8848";
    // Data ID
    private String dataId = "nacos-config-example.properties";
    // Group
    private String group = "DEFAULT_GROUP";
    //config
    private Properties config = new Properties();

    /**
     * 测试注册监听
     * @return
     */
    @RequestMapping(value = "/test/addlistener", method = RequestMethod.GET)
    public String  testAddListener(){
        final String[] retStr = {""};
        config.put("serverAddr", serverAddr);
        try {
            ConfigService configService = NacosFactory.createConfigService(config);
            // 添加监听器【关键代码】
            configService.addListener(dataId, group, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    // 接收配置变更通知
                    System.out.println(configInfo);
                    retStr[0] = retStr[0] + configInfo;
                }
            });

            // String getConfig(String dataId, String group, long timeoutMs) throws NacosException;
            // timeoutMs 为获取配置超时时间，单位毫秒
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
            // 注意：这里写一个简单循环，是为了避免程序执行完后立即结束
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
        } catch (NacosException e) {
            System.out.println("发生异常:");
            e.printStackTrace();
            retStr[0] = retStr[0] + ":发生异常";
        }


        return retStr[0];
    }

    /**
     * 测试命名空间的使用
     * @return
     */
    @RequestMapping(value = "/test/namespace", method = RequestMethod.GET)
    public String testNameSpace(){
        String retStr = "";
        // Nacos 地址
        String serverAddr = "127.0.0.1:8848";
        // Data ID
        String dataId = "my_config";
        // Group
        String group = "DEFAULT_GROUP";

        Properties config = new Properties();
        config.put("serverAddr", serverAddr);
        // 如果 Nacos 开启了登录权限，则指定用户名和密码
        // 如果没有开启登录权限，则注释掉下面两行代码
//        config.put("username", "nacos");
//        config.put("password", "nacos");
        //设置命名空间
        //尝试设置命名空间的名称test
        config.setProperty("namespace", "test");

        try {
            ConfigService configService = NacosFactory.createConfigService(config);
            // String getConfig(String dataId, String group, long timeoutMs) throws NacosException;
            // timeoutMs 为获取配置超时时间，单位毫秒
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
            //如果namespace = test 没有返回，尝试给namespace赋值namespaceId
            if (content == null || content.equals("")){
                System.out.println("尝试传入namespaceId，来获取配置内容：");
                config.setProperty("namespace", "3f016b56-1746-4922-a8eb-c9c231ef56f0");
                configService = NacosFactory.createConfigService(config);
                content = configService.getConfig(dataId, group, 5000);
                System.out.println(content);
            }
            retStr = content;
        } catch (NacosException e) {
            System.out.println("发生异常:");
            e.printStackTrace();
        } finally {
        }
        return retStr;
    }
    /**
     * 使用 NacosFactory的方法获取配置信息
     * @return
     */
    @RequestMapping(value = "/get/nacos-factory", method = RequestMethod.GET)
    public String getConfig(){
        // Nacos 地址
        String serverAddr = "127.0.0.1:8848";
        // Data ID
        String dataId = "nacos-config-example.properties";
        // Group
        String group = "DEFAULT_GROUP";

        Properties config = new Properties();
        config.put("serverAddr", serverAddr);
        // 如果 Nacos 开启了登录权限，则指定用户名和密码
        // 如果没有开启登录权限，则注释掉下面两行代码
//        config.put("username", "nacos");
//        config.put("password", "nacos");
        String retStr = "";

        try {
            ConfigService configService = NacosFactory.createConfigService(config);
            // String getConfig(String dataId, String group, long timeoutMs) throws NacosException;
            // timeoutMs 为获取配置超时时间，单位毫秒
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
            retStr = content;
        } catch (NacosException e) {
            System.out.println("发生异常:");
            e.printStackTrace();
        } finally {
        }

        return retStr;
    }
}
