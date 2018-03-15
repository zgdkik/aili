package com.deppon.esb.management.web.deploy.struts.result.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description JSON格式的Annotation
 * @author HuangHua
 * @Date 2012-4-12
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JSON {
	
}