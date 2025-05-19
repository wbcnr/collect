package org.d13.annotation;


import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * 标签：《自定义注解处理器2025-4-14 10:46:36》
 *
 */
@SupportedAnnotationTypes("org.d13.annotation.Loggable") // 指定处理的注解
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class LoggableProcessorByProcessor extends AbstractProcessor {
    /**
     * 初始化操作，通过参数ProcessingEnvironment可以获取一系列有用的工具
     * @param processingEnv
     */
    @Override
    public synchronized void init( ProcessingEnvironment processingEnv ) {
        super.init ( processingEnv );
    }

    /**
     *  注解处理器的核心方法，处理具体的注解。主要功能基本可以理解为两个:
     *     1、获取同一个类中的所有指定注解修饰的Element；
     *          a、set参数，存放的是支持的注解类型，一般无用。
     *          b、RoundEnvironment参数，可以通过遍历获取代码中所有通过指定注解修饰的Element对象。
     *              通过Element对象可以获取字段名称，字段类型以及注解元素的值。
     *     2、创建Java文件；
     *          将同一个类中通过指定注解修饰的所有Element在同一个Java文件中实现初始化，这样做的目的是让在最终依赖注入时便于操作。
     *
     * TODO：
     *  （1） 如何使用这个注解处理器？
     *  （2） 这个注解处理器在编译的时候增强类，也就是根据注解生成代码到.class文件中？
     *
     * @param annotations
     * @param roundEnv
     * @return true : annotations 已经被处理完毕，下一个处理器不必再处理；
     *         false ： 下一个处理器继续处理相关注解；
     */
    @Override
    public boolean process( Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //原文链接：https://blog.csdn.net/qq_16268979/article/details/128228442
        if(!roundEnv.processingOver()){
            for(Element element : roundEnv.getElementsAnnotatedWith(Loggable.class)){
                String name = element.getSimpleName().toString();
                System.out.println ("Found @Loggable on method: " + name);
                processingEnv.getMessager().printMessage( Diagnostic.Kind.NOTE, "element name: " + name);
            }
        }
        return false;
    }
}