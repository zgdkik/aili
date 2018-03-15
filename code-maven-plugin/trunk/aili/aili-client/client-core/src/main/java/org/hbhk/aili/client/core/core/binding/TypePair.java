package org.hbhk.aili.client.core.core.binding;

import java.awt.Component;

/**
 *	包装类型转换器中需要用到的两个相互转换的类型。分别存储他们的Class信息。
 */
public class TypePair {
	private Class<? extends Component> sourceType;
	private Class<?> targetType;
	/**
	 * 
	  * 创建一个新的实例 TypePair.
	  * @param sourceType
	  * 需要转换的类型，比如从String转换到int，那么String类型的参数就是sourceType，
	  * 而targetType则是int类型的Class对象
	  * @param targetType
	  * @since
	 */
	public TypePair(Class<? extends Component> sourceType, Class<?> targetType) {
		this.sourceType = sourceType;
		this.targetType = targetType;
	}
	/**
	 * 
	 * @see java.lang.Object#hashCode()
	 * hashCode
	 * @return
	 * @since:
	 */
	@Override
	public int hashCode() {
		return sourceType.hashCode() + targetType.hashCode();
	}
	/**
	 * 判断两个TypePair对象是否相等就是需要判断sourceType及targetType这两个类类型是否相等即可
	 * @see java.lang.Object#equals(java.lang.Object)
	 * equals
	 * @param obj
	 * @return
	 * @since:
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TypePair) {
			TypePair other = (TypePair)obj;
			
			return other.sourceType == sourceType &&
				other.targetType == targetType;
		}
		
		return false;
	}
}
