package com.github.ccmagic.generateinterface;

import com.github.ccmagic.Log;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

//生成接口的处理类 ，在此不考虑方法的参数及返回类型(为了演示简单)
@SupportedAnnotationTypes("com.github.ccmagic.generateinterface.GenerateInterface")
//@SupportedOptions({"name"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CreateInterfaceProcessor extends AbstractProcessor {
    /**
     * javax.annotation.processing.Filer，
     * 注解处理器可用此创建新文件（源文件、类文件、辅助资源文件）。
     * 由此方法创建的源文件和类文件将由管理它们的工具（javac）处理
     */
    private Filer filer;
    /**
     * javax.annotation.processing.Messager，
     * 注解处理器用此来报告错误消息、警告和其他通知的方式。
     * 可以为它的方法传递元素、注解、注解值，以提供消息的位置提示，不过，这类位置提示可能是不可用的，或者只是一个大概的提示。
     * 打印错误种类的日志将会产生一个错误。
     * <p>
     * 注意：打印消息可能会出现在System.out、System.out中，也可能不是。也可以选择在窗口中显示消息
     */
    private Messager messager;

    private int r = 0;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "process() is execute...");
        //获取所有编译类元素,并打印，测试用
        Set<? extends Element> elements = roundEnv.getRootElements();
        Log.i("输入的所有类有：");
        for (Element element : elements) {
            Log.i(">>> " + element.getSimpleName());
        }

        //获取使用了注解GenerateInterface的类元素
        Set<? extends Element> genElements = roundEnv.getElementsAnnotatedWith(GenerateInterface.class);
        for (Element element : genElements) {
            Log.i(">>> " + element.getSimpleName());
            GenerateInterface generateInterface = element.getAnnotation(GenerateInterface.class);
            String className = element.getSimpleName() + generateInterface.suffix();
            StringBuilder classString = new StringBuilder("package com.github.ccmagic.generateinterface;\n"
                    + "public interface " + className + "{\n");
            //获取所有的方法元素
            List<? extends Element> genElementAll = element.getEnclosedElements();
            Log.i(">>> 类" + element.getSimpleName() + "封装元素（仅对修饰符有public的生成元素接口方法）:");

            for (Element element1 : genElementAll) {
                Log.i(">>> >>> " + element1.getSimpleName() + " 修饰符： " + element1.getModifiers());
                if (!element1.getSimpleName().toString().equals("<init>")
                        && element1.asType() instanceof ExecutableType
                        && isPublic(element1)) {
                    Log.i(">>> >>> >>> " + element1.getSimpleName());
                    classString.append("  void ").append(element1.getSimpleName()).append("();\n");
                }
            }

            classString.append("}");
            try {
                JavaFileObject javaFileObject = filer.createSourceFile("com.github.ccmagic.generateinterface." + className, element);
                Writer writer = javaFileObject.openWriter();
                writer.flush();
                String createSourceFile = classString.toString();
                Log.i("\ncreateSourceFile : \n" + createSourceFile);
                writer.append(createSourceFile);
                writer.flush();
                writer.close();
            } catch (IOException exception) {
                Log.e(exception);
            }
        }

        Log.i("注解处理器第" + (r++) + "次循环处理结束...\n");
        return false;
    }

    public boolean isPublic(Element element) {
        Set<Modifier> modifiers = element.getModifiers();
        for (Modifier modifier : modifiers) {
            if (modifier.equals(Modifier.PUBLIC)) {
                return true;
            }
        }
        return false;
    }
}
