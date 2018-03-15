package org.hbhk.aili.client.core.component.dataaccess.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *	标示事务的注解，此注解用在DAO类中，并且只能用在方法上。
 *	如果方法上打了次注解，就表示这是一个事务方法。
 *包含此注解的类需要通过DefaultTransactionFactory.getProxy(Class cls)来获取代理类才能起到事务的作用
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaction {

}
