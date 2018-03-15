package com.deppon.esb.management.web.deploy.struts.result.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 定义日期格式的Annotation
 * @author HuangHua
 * @Date 2012-4-12
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
	String formate() default "yyyy-MM-dd HH:mm:ss";
}