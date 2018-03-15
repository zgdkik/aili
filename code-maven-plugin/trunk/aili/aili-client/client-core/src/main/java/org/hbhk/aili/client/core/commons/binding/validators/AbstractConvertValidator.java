package org.hbhk.aili.client.core.commons.binding.validators;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.client.core.commons.binding.IBindingArgsAware;
import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.validation.IConvertValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationUtils;
import org.hbhk.aili.client.core.commons.validators.ValueAcceptedValidator;

/**
 * 
 * 校验器的抽象基类，用来包装转换器
 */
public abstract class AbstractConvertValidator extends ValueAcceptedValidator implements IBindingArgsAware, IConvertValidator {
	private Map<String, String> args;
	private Object result;
	private Object errorKey;
	
	@Override
	public void setArgs(Map<String, String> args) {
		this.args = args;
	}
	
	
	
	public Map<String, String> getArgs() {
		return args;
	}



	public Object getResult() {
		return result;
	}



	public void setResult(Object result) {
		this.result = result;
	}



	public Object getErrorKey() {
		return errorKey;
	}



	/**
	 * 
	 * <p>Title: setErrorKey</p>
	 * <p>Description: 设置errorKey</p>
	 * @param errorKey
	 */
	public void setErrorKey(Object errorKey) {
		this.errorKey = errorKey;
	}
	
	@Override
	public List<ValidationError> validate() {
		try {
			result = getConverter().to(getValue());
			
			return null;
		} catch (ConversionException e) {
			return ValidationUtils.createErrors(getErrorMessage(), errorKey);
		}
	}
	
	@Override
	public Object getToResult() {
		return result;
	}
	
	protected abstract String getErrorMessage();
}
