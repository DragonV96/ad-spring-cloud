package com.glw.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : glw
 * @date : 2020/3/1
 * @time : 13:13
 * @Description : 忽略拦截注解
 */
@Target({ElementType.TYPE, ElementType.METHOD}) // 可用于类/接口/枚举 和 方法上
@Retention(RetentionPolicy.RUNTIME)             // 保留在运行时
public @interface IgnoreResponseAdvice {
}
