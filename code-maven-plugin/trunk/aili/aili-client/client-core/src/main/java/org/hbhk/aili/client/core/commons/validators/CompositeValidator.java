package org.hbhk.aili.client.core.commons.validators;

import java.util.ArrayList;
import java.util.List;

import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
/**
 * <b style="font-family:微软雅黑"><small>Description:复合验证器</small></b>   </br>
 */
public class CompositeValidator extends ValueAcceptedValidator {
	private List<IValidator> validators;
	
	public CompositeValidator() {
		validators = new ArrayList<IValidator>();
	}
	
	public void addValidator(IValidator validator) {
		if (validator instanceof IValueAcceptor) {
			((IValueAcceptor)validator).setValue(getValue());
		}
		
		validators.add(validator);
	}
	
	public void removeValidator(IValidator validator) {
		validators.remove(validator);
	}
	
	@Override
	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		for (IValidator validator : validators) {
			List<ValidationError> result = validator.validate();
			if (result != null && result.size() != 0) {
				errors.addAll(result);
			}
		}
		
		return errors;
	}
	
	public boolean isNullValidator() {
		return validators.size() == 0;
	}
	
	public void setValue(Object target) {
		super.setValue(target) ;
		for (IValidator validator : validators) {
			if (validator instanceof ValueAcceptedValidator) {
				((ValueAcceptedValidator)validator).setValue(target);
			}
		}
	}

}
