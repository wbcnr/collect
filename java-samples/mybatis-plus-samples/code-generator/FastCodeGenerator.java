package codegenerator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Collections;

/**
 * Mybatis-Plus Code Generator样例代码：
 * 功能：快速创建相关代码文件。
 *      这个程序是根据配置自动生成，也有在运行时根据手动输入内容生成代码的，注意区分。
 * 用法：
 *      pom：引入数据库连接驱动 、 模板
 * API网址：https://gitee.com/baomidou/generator
 *
 * 注意：
 *      （1）这个类不要放到工程包名下。
 *          例如：包名配置类似："org.example.mybatisplus.generator"，那这个类就不要放到org包下面，执行的时候可能会被删除或者
 *          放到test包下。
 *          所以，还是直接放到test包下比较好，或者在项目文件夹外面单独生成一个项目。
 *
 * 常见问题与解决办法：
 * （1）如果提示Kotlin版本不正确？
 *  A:IDEA 尝试Build-》Rebuild Project，然后重新执行main；
 *
 */
public class FastCodeGenerator {
    private final String url = "jdbc:mysql://localhost:3306/sd_mis?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true";
    private final String userName = "root";
    private final String passWord = "password";

    /**
     * 初始化类参数
     */
    public void init(){
        System.out.println("init");
    }
    /**
     * 快速生成代码
     */
    public void fastGenAuto(){
        System.out.println("自动代码生成:");
        //数据库配置信息
        FastAutoGenerator.create(url,userName,passWord)
                .globalConfig(builder -> builder
                        .author("d")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                )
                //创建包和相关文件
                .packageConfig(builder -> builder
                        .parent("org.example.mybatisplus.generator")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        //.xml("mapper.xml") ： 在mapper/xml文件夹下生成xml文件
                        .xml("mapper.xml")
                        .controller("controller")
                )
//                .strategyConfig(builder -> builder
//                        .entityBuilder()
//                        .enableLombok()
//                )
                .strategyConfig(builder -> {
                    //设置：处理指定的表 & 过滤指定的前缀
                    builder.addInclude("s_user_list")
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
     *
     *
     */
    public void fastGenInter(){
        System.out.println("交互式代码生成:");
        FastAutoGenerator.create(url, userName, passWord)
                // 全局配置
                .globalConfig((scanner, builder) -> builder
                            .author(scanner.apply("请输入作者名称？"))
                            .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                            //.outputDir(Paths.get(System.getProperty("user.dir")) + scanner.apply("请输入/src/main/java？"))
                )
                // 包配置  org.example.mybatisplus.generator
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名?")))
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
     * 测试路径
     * 返回值：项目的绝对路径。
     *  例如：这个项目名称是“my-mp-samples"那返回值就是:”绝对路径"/"my-mp-samples"
     */
    public void testUsrDir(){
        String pathOfUsrDir = System.getProperty("user.dir");
        System.out.println(pathOfUsrDir);
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
        FastCodeGenerator fastCodeGenerator = new FastCodeGenerator();
        fastCodeGenerator.init();
        fastCodeGenerator.fastGenInter();
    }
}
