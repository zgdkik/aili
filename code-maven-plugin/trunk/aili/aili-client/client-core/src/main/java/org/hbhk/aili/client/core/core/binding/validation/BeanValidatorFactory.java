package org.hbhk.aili.client.core.core.binding.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hbhk.aili.client.core.commons.validation.IConvertValidator;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.core.binding.IBinder;
import org.hbhk.aili.client.core.core.binding.IBufferedBinder;

/**
 *   bean级别校验器工厂，用来组装bean级别的校验器。
 *   把所有属性级别的转换校验器，校验器以及类级别校验器，组装成一个最终的bean级别的校验器。
 */
public class BeanValidatorFactory implements IBeanValidatorFactory {
	private Map<String, IConvertValidator> convertValidators;
	private Map<String, IValidator> propertyValidators;
	private List<IValidator> globalValidators;
	private IBinder<?> binder;
	
	public BeanValidatorFactory(IBinder<?> binder) {
		convertValidators = new HashMap<String, IConvertValidator>();
		propertyValidators = new HashMap<String, IValidator>();
		globalValidators = new ArrayList<IValidator>();
		this.binder = binder;
	}

	@Override
	public IValidator getBeanValidator() {
		return new BeanValidator();
	}

	@Override
	public void addPropertyValidator(String propertyName, IValidator validator) {
		propertyValidators.put(propertyName, validator);
	}

	@Override
	public void addGlobalValidator(IValidator validator) {
		globalValidators.add(validator);
	}
	
	private class BeanValidator implements IValidator {

		@Override
		public List<ValidationError> validate() {
			List<ValidationError> errors = new ArrayList<ValidationError>();
			
			for (IConvertValidator convertValidator : convertValidators.values()) {
				validate(convertValidator, errors);
			}
			
			if (!errors.isEmpty()) {
				return errors;
			}
			
			for (IValidator validator : propertyValidators.values()) {
				validate(validator, errors);
			}
			
			if (!errors.isEmpty()) {
				return errors;
			}
			
			if (binder instanceof IBufferedBinder) {
				((IBufferedBinder<?>)binder).flush();
			}
			
			for (IValidator validator : globalValidators) {
				validate(validator, errors);
			}
			
			return errors;
		}

		private void validate(IValidator validator, List<ValidationError> errors) {
			List<ValidationError> result = validator.validate();
			if (result != null && result.size() != 0) {
				errors.addAll(result);
			}
		}
	}

	@Override
	public void addConvertValidator(String propertyName, IConvertValidator validator) {
		convertValidators.put(propertyName, validator);
	}

	@Override
	public IConvertValidator getConvertValidator(String propertyName) {
		return convertValidators.get(propertyName);
	}

	@Override
	public IValidator getPropertyValidator(String propertyName) {
		return propertyValidators.get(propertyName);
	}
}
