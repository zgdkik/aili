package org.hbhk.aili.client.core.commons.validators;

import org.hbhk.aili.client.core.commons.validation.IValidator;
/**
 * <b style="font-family:微软雅黑"><small>Description:数据接收验证器</small></b>   </br>
 */
public abstract class ValueAcceptedValidator implements IValueAcceptor, IValidator {
	private Object value;
	
	public ValueAcceptedValidator() {
	}
	
	public ValueAcceptedValidator(Object value) {
		this.value = value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
	
	
}
