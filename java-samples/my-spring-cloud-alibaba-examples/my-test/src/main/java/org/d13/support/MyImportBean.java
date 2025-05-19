package org.d13.support;

import org.d13.entity.UserConfig;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBean implements ImportBeanDefinitionRegistrar {
//    /**
//     * 据说两个方法只能实现一个，否则后面的会将前面的覆盖？
//     * @param importingClassMetadata
//     * @param registry
//     * @param importBeanNameGenerator
//     */
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
//        System.out.println("MyImportBean.registerBeanDefinitions(3 params) running");
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserConfig.class);
//        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
//        String beanName = importBeanNameGenerator.generateBeanName(beanDefinition, registry);
//        /**
//         * 所谓的不能两个方法都重写可能原因是在这里，一个是这里直接调用了
//         * registry.registerBeanDefinition(beanName, beanDefinition);
//         * 这里应该调用本类的 registerBeanDefinitions(importingClassMetadata, registry);
//         * 在这个方法里将bean注入到容器中。
//         */
//        //registry.registerBeanDefinition(beanName, beanDefinition);
//        registerBeanDefinitions(importingClassMetadata, registry);
//    }

    /**
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               注册类，其registerBeanDefinition()可以注册bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        //构建一个 BeanDefinition , Bean的类型为 UserConfig,这个Bean的属性username的值为后端元宇宙
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(UserConfig.class)
                .addPropertyValue("username", "后端元宇宙")
                .getBeanDefinition();
        //把 UserConfig 这个Bean的定义注册到容器中
        registry.registerBeanDefinition("userConfig", beanDefinition);
    }
}
