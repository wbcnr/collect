package org.d13.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 这是来自ai的demo代码
 * import org.springframework.http.*;
 * import org.springframework.web.client.RestTemplate;
 * import java.util.Base64;
 * import java.util.Collections;
 *
 * public class NacosServiceDiscovery {
 *
 *     // Nacos Server 地址（需替换实际地址）
 *     private static final String NACOS_SERVER = "http://localhost:8848";
 *     // 服务名称（需替换目标服务名）
 *     private static final String SERVICE_NAME = "your-service-name";
 *     // 命名空间ID（默认为public，按需修改）
 *     private static final String NAMESPACE_ID = "public";
 *     // 认证信息（如果Nacos开启认证）
 *     private static final String USERNAME = "nacos";
 *     private static final String PASSWORD = "nacos";
 *
 *     public static void main(String[] args) {
 *         RestTemplate restTemplate = new RestTemplate();
 *
 *         // 1. 构造请求URL
 *         String url = String.format("%s/nacos/v1/ns/instance/list?serviceName=%s&namespaceId=%s",
 *                 NACOS_SERVER, SERVICE_NAME, NAMESPACE_ID);
 *
 *         // 2. 添加认证请求头（如果启用认证）
 *         HttpHeaders headers = new HttpHeaders();
 *         if (USERNAME != null && PASSWORD != null) {
 *             String auth = USERNAME + ":" + PASSWORD;
 *             String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
 *             headers.set("Authorization", "Basic " + encodedAuth);
 *         }
 *         headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
 *
 *         // 3. 发送HTTP请求
 *         ResponseEntity<String> response = restTemplate.exchange(
 *                 url,
 *                 HttpMethod.GET,
 *                 new HttpEntity<>(headers),
 *                 String.class
 *         );
 *
 *         // 4. 处理响应
 *         if (response.getStatusCode() == HttpStatus.OK) {
 *             System.out.println("服务实例列表: " + response.getBody());
 *         } else {
 *             System.out.println("请求失败: " + response.getStatusCode());
 *         }
 *     }
 * }
 */
@RequestMapping("/NacosConsumer/")
@RestController
public class ConsumerControllerFromAI {

    private final RestTemplate restTemplate;
//    服务名

    private final String serviceName;

    private final String nacosServer;
    private String namespaceid;

    public ConsumerControllerFromAI(RestTemplate restTemplate,
                                    @Value("${spring.application.name}") String serviceName,
                                    @Value("${spring.cloud.nacos.discovery.server-addr}") String nacosServer,
                                    @Value("${spring.cloud.nacos.discovery.namespace-id:public}") String namespaceid) {
        this.restTemplate = restTemplate;
        this.serviceName = serviceName;
        this.nacosServer = nacosServer;
        if(namespaceid == null || namespaceid.equals("")) {
            namespaceid = "public";
        }
        this.namespaceid = namespaceid;
    }

    @GetMapping("get/serviceprovider/test")
    public String test(){
        System.out.println("ConsumerControllerFromAI.test() running.");
        String serviceNameProvider = "nacos-provider";
        String nacosServer = "http://127.0.0.1:8848";
        String url = String.format("%s/nacos/v1/ns/instance/list?serviceName=%s&namespaceId=%s",
                  nacosServer, serviceNameProvider, namespaceid);
        System.out.println("url:" + url);
        HttpHeaders headers = new HttpHeaders();

        String retStr = "";

        // 3. 发送HTTP请求
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        // 4. 处理响应
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("服务实例列表: " + response.getBody());
        } else {
            System.out.println("请求失败: " + response.getStatusCode());
        }
        System.out.println("ConsumerControllerFromAI.test() will stop.");
        retStr = response.toString();
        return retStr;
    }

}
