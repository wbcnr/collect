package org.d13.annotation;


import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 标签：《自定义注解处理器2025-4-14 10:46:36》
 * 自定义注解
 */
@SupportedAnnotationTypes("org.d13.annotation.BuildProperty") // Only process the annotation with the type of BuildProperty;
public class SzzBuilderProcessor extends AbstractProcessor {
    @Override
    public boolean process( Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("SzzBuildProcessor.process ;");

        for (TypeElement annotation : annotations) {
            //输出annotation的全量限定名
            System.out.println ("SzzBuilderProcessor.process-> annotation : " + annotation.getSimpleName ());
            // Obtain all instances annotated by the annotation.
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            // Check whether the annotation starts with set and only one argument is used as required.
            Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream().collect(
                    Collectors.partitioningBy( element ->
                            ((ExecutableType) element.asType()).getParameterTypes().size() == 1
                                    && element.getSimpleName().toString().startsWith("set")));

            List<Element> setters = annotatedMethods.get(true);
            List<Element> otherMethods = annotatedMethods.get(false);

            // Print the case where the annotation was used incorrectly.
            otherMethods.forEach(element ->
                    processingEnv.getMessager().printMessage( Diagnostic.Kind.ERROR,
                            "@BuilderProperty must be applied to a setXxx method with a single argument", element));

            if (setters.isEmpty()) {
                continue;
            }

            Map<String ,List<Element>> groupMap = new HashMap ();

            // Group by fully-qualified class name. A builder is created for each class.
            setters.forEach(setter ->{
                // Fully-qualified class name
                String className = ((TypeElement) setter
                        .getEnclosingElement()).getQualifiedName().toString();
                List<Element> elements = groupMap.get(className);
                if(elements != null){
                    //这个操作好像没啥用，本来elements就是内部的临时变量。
                    elements.add(setter);
                }else {
                    //真正起作用的在这里：将符合条件的元素放入groupMap。
                    List<Element> newElements = new ArrayList<> ();
                    newElements.add(setter);
                    groupMap.put(className,newElements);
                }
            });


            groupMap.forEach((groupSetterKey,groupSettervalue)->{
                // Obtain the class name SimpleName and the input parameters of the set() method.
                Map<String, String> setterMap = groupSettervalue.stream().collect(Collectors.toMap(
                        setter -> setter.getSimpleName().toString(),
                        setter -> ((ExecutableType) setter.asType())
                                .getParameterTypes().get(0).toString()
                ));
                try {
                    // Assemble the XXXBuild class and create the corresponding class file.
                    writeBuilderFile(groupSetterKey,setterMap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        // Returning false indicates that other processors can continue to process the annotation after the
        // current processor has processed it, and returning true indicates that other processors will no
        // longer process the annotation after the current processor has processed it.
        return true;
    }

    /**
     * 生成xxxBuilder类。
     * TODO：
     *      （1） 源码类的内容，完全可以声明成一个由类名，参数名，方法名拼接成的字符串，然后循环创建，这样方便理解，
     *          因为这个源码文件中的内容的结构是固定的，只是类名、方法名、参数类型等需要根据实际的替换；
     * @param className
     * @param setterMap
     * @throws IOException
     */
    private void writeBuilderFile(
            String className, Map<String, String> setterMap)
            throws IOException {
        //获取包名
        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }
        //获取类名
        String simpleClassName = className.substring(lastDot + 1);
        //新生成的类名，xxxBuilder
        String builderClassName = className + "Builder";
        String builderSimpleClassName = builderClassName
                .substring(lastDot + 1);
        //java 源码文件或者类文件
        JavaFileObject builderFile = processingEnv.getFiler()
                .createSourceFile(builderClassName);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }

            out.print("public class ");
            out.print(builderSimpleClassName);
            out.println(" {");
            out.println();

            out.print("    private ");
            out.print(simpleClassName);
            out.print(" object = new ");
            out.print(simpleClassName);
            out.println("();");
            out.println();

            out.print("    public ");
            out.print(simpleClassName);
            out.println(" build() {");
            out.println("        return object;");
            out.println("    }");
            out.println();

            setterMap.entrySet().forEach(setter -> {
                String methodName = setter.getKey();
                String argumentType = setter.getValue();

                out.print("    public ");
                out.print(builderSimpleClassName);
                out.print(" ");
                out.print(methodName);

                out.print("(");

                out.print(argumentType);
                out.println(" value) {");
                out.print("        object.");
                out.print(methodName);
                out.println("(value);");
                out.println("        return this;");
                out.println("    }");
                out.println();
            });

            out.println("}");
        }
    }

    @Override
    public synchronized void init( ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("----------");

        System.out.println(processingEnv.getOptions());

    }
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


}
