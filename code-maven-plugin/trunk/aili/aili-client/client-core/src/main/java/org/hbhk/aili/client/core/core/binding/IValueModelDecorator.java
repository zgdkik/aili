package org.hbhk.aili.client.core.core.binding;

import com.jgoodies.binding.value.ValueModel;

/**
 * 
 *	用来修饰ValueModel，作为结果，创建一个次序相同的DecoratedValueModel链。
 */
public interface IValueModelDecorator {
	/**
	 * 用来修饰ValueModel
	 * decorate
	 * @param valueModel
	 * @return ValueModel
	 * @since:0.6
	 */
	ValueModel decorate(ValueModel valueModel);
}