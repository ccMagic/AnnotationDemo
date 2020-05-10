# AnnotationDemo
> Java注解处理学习项目


Java5中提供了apt工具来进行编译期的注解处理。apt是命令行工具，与之配套的是一套描述“程序在编译时刻的静态结构”的API：Mirror API（com.sun.mirror.*）。通过Mirror API可以获取到被注解的Java类型元素的信息，从而提供自定义的处理逻辑。具体的处理工具交给apt来处理。编写注解处理器的核心是两个类：注解处理器（com.sun.mirror.apt.AnnotationProcessor）、注解处理器工厂（com.sun.mirror.apt.AnnotationProcessorFactory）。apt工具在完成注解处理后，会自动调用javac来编译处理完成后的源代码。然而，apt工具是oracle提供的私有实现（在JDK开发包的类库中是不存在的）。在JDK6中，将注解处理器这一功能进行了规范化，形成了java.annotation.processing的API包，Mirror API则进行封装，形成javax.lang.model包。注解处理器的开发进行了简化，不再单独使用apt工具，而将此功能集成到了javac命令中。（当前开发使用的JDK版本一般都在6以上，故对apt工具不做研究）。