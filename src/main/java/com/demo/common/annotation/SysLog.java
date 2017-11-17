package com.demo.common.annotation;


import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Centling Techonlogies
 * @email xxx@demo.com
 * @url www.demo.com
 * @date 2017年8月14日 下午8:00:45
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String module() default "";

	String value() default "";
}
