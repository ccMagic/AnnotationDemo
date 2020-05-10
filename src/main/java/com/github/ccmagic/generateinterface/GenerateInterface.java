package com.github.ccmagic.generateinterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)//GenerateInterface
@Retention(RetentionPolicy.SOURCE)//注解保留范围为源代码
public @interface GenerateInterface {
    String suffix() default "Interface";//生成对应接口的后缀名
}
