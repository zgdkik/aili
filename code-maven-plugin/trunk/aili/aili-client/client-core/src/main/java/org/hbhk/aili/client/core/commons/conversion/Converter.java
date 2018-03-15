package org.hbhk.aili.client.core.commons.conversion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * 转换注解，当某个空间与bean类的某个字段绑定的时候，
 * 但是他们的属性值不能关联，比如string到某个java对象，
 * 此时是不能直接赋值的，需要通过自己实现的转换器来进行转换。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Converter {
	/**
	 * 需要传入转换的类的类型
	 * 需要实现IConverter接口
	 * type
	 * @return
	 * @return Class<? extends IConverter>
	 * @since:0.6
	 */
	Class<? extends IConverter> type();
	
	/**
	 * 如果转换失败的现实的错误信息
	 * errorMsg
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String errorMsg();
}
