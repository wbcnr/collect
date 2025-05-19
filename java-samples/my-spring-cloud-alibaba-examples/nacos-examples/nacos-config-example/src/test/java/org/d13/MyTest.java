package org.d13;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Nacos SDK 简单DEMO
 * @author hxstrive.com
 */
public class MyTest {

    public void test1(){
        ArrayList<String> list = (ArrayList<String>) Arrays.asList ( "a", "b", "c", "d" );
        list.stream()                // Head 节点（数据源）
                .filter(s -> s.length() > 3)  // 中间节点（记录过滤逻辑）
                .map(String::toUpperCase)     // 中间节点（记录映射逻辑）
                .collect( Collectors.toList()); // 终端节点（触发执行）
    }

    public void test(){
        // Nacos 地址
        String serverAddr = "127.0.0.1:8848";
        // Data ID
        String dataId = "nacos-config-example.properties";
        // Group
        String group = "DEFAULT_GROUP";

        Properties config = new Properties();
        config.put("serverAddr", serverAddr);
        // 关键代码，指定为 dev 命名空间
//        config.put("namespace", "8719efd1-94a6-49f7-9846-2debd66f6c0f");

        // 如果 Nacos 开启了登录权限，则指定用户名和密码
        // 如果没有开启登录权限，则注释掉下面两行代码
//        config.put("username", "nacos");
//        config.put("password", "nacos");
        NacosConfigProperties nacosConfigProperties = new NacosConfigProperties();
        nacosConfigProperties.setServerAddr(serverAddr);
        NacosConfigManager nacosConfigManager = new NacosConfigManager(nacosConfigProperties);
        ConfigService configService  = nacosConfigManager.getConfigService();
        NacosConfigService nacosConfigService = (NacosConfigService)configService;
    }

    public static void main(String[] args) throws NacosException {
        // Nacos 地址
        String serverAddr = "127.0.0.1:8848";
        // Data ID
        String dataId = "nacos-config-example.properties";
        // Group
        String group = "DEFAULT_GROUP";

        Properties config = new Properties();
        config.put("serverAddr", serverAddr);
        // 关键代码，指定为 dev 命名空间
//        config.put("namespace", "8719efd1-94a6-49f7-9846-2debd66f6c0f");

        // 如果 Nacos 开启了登录权限，则指定用户名和密码
        // 如果没有开启登录权限，则注释掉下面两行代码
//        config.put("username", "nacos");
//        config.put("password", "nacos");

        ConfigService configService = NacosFactory.createConfigService(config);
        // 添加监听器【关键代码】
        configService.addListener(dataId, group, new Listener() {
            public Executor getExecutor() {
                return null;
            }

            public void receiveConfigInfo(String configInfo) {
                System.out.println("回调触发：");
                // 接收配置变更通知
                System.out.println(configInfo);
            }
        });

        // String getConfig(String dataId, String group, long timeoutMs) throws NacosException;
        // timeoutMs 为获取配置超时时间，单位毫秒
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);

        // 注意：这里写一个简单循环，是为了避免程序执行完后立即结束
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}