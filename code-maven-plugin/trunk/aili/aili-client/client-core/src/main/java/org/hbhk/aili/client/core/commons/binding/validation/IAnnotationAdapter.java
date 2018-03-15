package org.hbhk.aili.client.core.commons.binding.validation;

import java.lang.annotation.Annotation;

import org.hbhk.aili.client.core.commons.validation.IValidator;

/**
 * 
 *	绑定适配器，一般情况下需要需要通过注解来校验某个字段的时候就需要实现此接口。
 */
public interface IAnnotationAdapter<T extends Annotation> {
	/**
	 * 此适配器主要是为了返回一个校验器。
	 * adapt
	 * @param annotation
	 * 自定义的校验注解
	 * @param errorKey
	 * 他是BindingAssociation对象，此对象描述了绑定关系
	 * @return IValidator
	 * @since:0.6
	 */
	IValidator adapt(T annotation, Object errorKey);
}
