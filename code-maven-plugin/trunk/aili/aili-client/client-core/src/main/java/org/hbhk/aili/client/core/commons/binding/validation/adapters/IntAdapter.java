package org.hbhk.aili.client.core.commons.binding.validation.adapters;

import java.util.List;

import javax.swing.JComponent;

import org.hbhk.aili.client.core.commons.binding.validation.IAnnotationAdapter;
import org.hbhk.aili.client.core.commons.binding.validation.annotations.Int;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationUtils;
import org.hbhk.aili.client.core.commons.validators.ValueAcceptedValidator;
import org.hbhk.aili.client.core.core.binding.BindingAssociation;
import org.hbhk.aili.client.core.core.binding.annotation.BindingArgsAware;
import org.hbhk.aili.client.core.widget.validatewidget.IBallValidateWidget;

public class IntAdapter extends BindingArgsAware implements IAnnotationAdapter<Int> {

	@Override
	public IValidator adapt(Int integer, Object errorKey) {
		return new IntegerValidator(errorKey, args.get("fieldName"));
	}
	
	private class IntegerValidator extends ValueAcceptedValidator {
		private Object errorKey;
		private String fieldName;
		
		public IntegerValidator(Object errorKey, String fieldName) {
			this.errorKey = errorKey;
			this.fieldName = fieldName;
		}
		
		@Override
		public List<ValidationError> validate() {
			
			//vallidate balltip need
			BindingAssociation bindingAssociation = (BindingAssociation) errorKey;
			
			JComponent jcomponent = (JComponent) bindingAssociation.getComponent();
			
			if(jcomponent instanceof  IBallValidateWidget ){
				((IBallValidateWidget) jcomponent).verifyPass();
			}
			//vallidate balltip need
			
			if (getValue() == null)
				return null;
			
			if (getValue() instanceof String) {
				try {
					Integer.parseInt((String)getValue());
					return null;
				} catch (Exception e) {
					return ValidationUtils.createErrors(String.format("%s must be an integer.", fieldName), errorKey);
				}	
			} else {
				return null;
			}
		}
		
	}

}
