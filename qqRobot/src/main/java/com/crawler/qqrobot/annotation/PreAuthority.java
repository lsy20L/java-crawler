package com.crawler.qqrobot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 指定注解作用范围
@Retention(RetentionPolicy.RUNTIME) // 指定注解生命周期
public @interface PreAuthority {

}
