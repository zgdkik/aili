package org.hbhk.aili.client.core.commons.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsEvent {
	private List<ValidationError> errors;
	
	public ValidationErrorsEvent() {
		this(new ArrayList<ValidationError>());
	}
	
	public ValidationErrorsEvent(List<ValidationError> errors) {
		this.errors = errors;
	}
	
	public List<ValidationError> getErrors() {
		return errors;
	}
}
