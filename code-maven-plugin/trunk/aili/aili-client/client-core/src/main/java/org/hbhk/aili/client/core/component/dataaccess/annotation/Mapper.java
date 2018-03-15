package org.hbhk.aili.client.core.component.dataaccess.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 *	映射注解，用在Dao类里面用来获取Mapper对象，可以通过面向对象的方式来操作数据增删改查。
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapper {

}
