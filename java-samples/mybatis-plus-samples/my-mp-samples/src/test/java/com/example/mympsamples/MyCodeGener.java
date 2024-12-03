package com.example.mympsamples;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

/**
 * 生成单独的一个表
 */
public class MyCodeGener {
    //数据库url
    private  String url = "jdbc:mysql://localhost:3306/nacos-config?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true";
    // 数据库用户名
    private  String userName = "root";
    // 数据库密码
    private  String passWord = "password";
    // 数据库驱动
    private   String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // @author 值
    private   String AUTHOR = "d";
    // 包的基础路径
    private   String BASE_PACKAGE_URL = "com.example.mympsamples";
    // xml文件路径
    private   String XML_PACKAGE_URL = "mapper.xml";
    //如果要将mapper的xml文件生成到resource文件夹下使用下面的配置：注意这个设置受到OUTPUT_DIR的影响
    //注意：这个配置“并不能”将xml放到java/resources/mapper/目录下；
    //private   String XML_PACKAGE_URL =Paths.get("..", "..", "..", "..") +  "/resources/mapper/";
    //输出根目录
    private  String OUTPUT_DIR = Paths.get(System.getProperty("user.dir")) + "/src/main/java";
    // 日期格式
    private  String DATEPARTTEN = "yyyy-MM-dd";

    //###下面的模板都没用到
    // xml 文件模板
    private   String XML_MAPPER_TEMPLATE_PATH = "generator1/templates/mapper.xml";
    // mapper 文件模板
    private   String MAPPER_TEMPLATE_PATH = "generator1/templates/mapper.java";
    // entity 文件模板
    private   String ENTITY_TEMPLATE_PATH = "generator1/templates/entity.java";
    // service 文件模板
    private   String SERVICE_TEMPLATE_PATH = "generator1/templates/service.java";
    // serviceImpl 文件模板
    private   String SERVICE_IMPL_TEMPLATE_PATH = "generator1/templates/serviceImpl.java";
    // controller 文件模板
    private   String CONTROLLER_TEMPLATE_PATH = "generator1/templates/controller.java";
    /**
     * 默认的参数初始化操作
     */
    public void init(){

    }

    /**
     * 快速生成代码
     */
    public void fastGenAuto(){
        System.out.println("自动代码生成:");
        //数据库配置信息
        FastAutoGenerator.create(url,userName,passWord)
                .globalConfig(builder -> builder
                        .author(AUTHOR)
                        .outputDir(OUTPUT_DIR)
                        .commentDate(DATEPARTTEN)
                )
                //创建包和相关文件
                .packageConfig(builder -> builder
                        .parent(BASE_PACKAGE_URL)
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        //.xml("mapper.xml") ： 在mapper/xml文件夹下生成xml文件
                        .xml(XML_PACKAGE_URL)
                        .controller("controller")
                )
                //所有的表都做代码生成
//                .strategyConfig(builder -> builder
//                        .entityBuilder()
//                        .enableLombok()
//                )
                .strategyConfig(builder -> {
                            //设置：处理指定的表 & 过滤指定的前缀
                            builder.addInclude("users")
                                    // 设置过滤表前缀
                                    .addTablePrefix("t_", "c_", "s_")
                                    .entityBuilder()
                                    .enableLombok();
                        }
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 交互式快速 代码生成
     *
     * 与自动式代码生成的不同是：相关参数需要根据提示手动收入，代码用“scanner.apply”获取，例如：
     * scanner.apply("请输入作者名称？")
     *
     * 注意：
     * （1）默认的文件创建目录是D:/；
     */
    public void fastGenInter(){
        System.out.println("交互式代码生成:");
        FastAutoGenerator.create(url, userName, passWord)
                // 全局配置
                .globalConfig(builder -> builder
                        .author(AUTHOR)
                        .outputDir(OUTPUT_DIR)
                        .commentDate(DATEPARTTEN)
                )
                // 包配置  org.example.mybatisplus.generator
                .packageConfig(builder -> builder
                        .parent(BASE_PACKAGE_URL)
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        //.xml("mapper.xml") ： 在mapper/xml文件夹下生成xml文件
                        .xml(XML_PACKAGE_URL)
                        .controller("controller")
                )
                // 策略配置 交互式
                .strategyConfig((scanner, builder) -> builder
                        .addInclude(scanner.apply("请输入表名:"))
                        .addTablePrefix(scanner.apply("请输入过滤的表名前缀:"))
                        .entityBuilder()
                        .enableLombok()
                )
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                   .templateEngine(new EnjoyTemplateEngine())
                 */
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();


    }

    /**
     * 指定测试代码
     * @param args
     *
     * org.example.mybatisplus.generator
     * s_user_list
     * s_
     */
    public static void main(String[] args) {
        System.out.println("MyCodeGener.main:");
        MyCodeGener myCodeGener = new MyCodeGener();
        myCodeGener.init();
        myCodeGener.fastGenInter();
    }

    /**
     * 测试路径
     * 返回值：项目的绝对路径。
     *  例如：这个项目名称是“my-mp-samples"那返回值就是:”绝对路径"/"my-mp-samples"
     */
    public void test(){
//        String pathOfUsrDir = System.getProperty("user.dir");
//        System.out.println(pathOfUsrDir);
        String p =Paths.get("..", "..", "..", "..") +  "/resources/mapper/";
        System.out.println(p);
    }
}
