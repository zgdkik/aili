package org.hbhk.aili.client.core.commons.binding.validation.adapters;

import java.util.List;

import javax.swing.JComponent;

import org.hbhk.aili.client.core.commons.binding.validation.IAnnotationAdapter;
import org.hbhk.aili.client.core.commons.binding.validation.annotations.NotNull;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationUtils;
import org.hbhk.aili.client.core.commons.validators.ValueAcceptedValidator;
import org.hbhk.aili.client.core.core.binding.BindingAssociation;
import org.hbhk.aili.client.core.core.binding.annotation.BindingArgsAware;
import org.hbhk.aili.client.core.widget.validatewidget.IBallValidateWidget;

public class NotNullAdapter extends BindingArgsAware implements IAnnotationAdapter<NotNull> {

	@Override
	public IValidator adapt(NotNull notNull, Object errorKey) {
		return new NotNullValidator(errorKey, args.get("fieldName"));
	}
	
	private class NotNullValidator extends ValueAcceptedValidator {
		private String fieldName;
		private Object errorKey;
		
		public NotNullValidator(Object errorKey, String fieldName) {
			this.fieldName = fieldName;
			this.errorKey = errorKey;
			

		}
		@Override
		public List<ValidationError> validate() {
			BindingAssociation bindingAssociation = (BindingAssociation) errorKey;
			
			JComponent jcomponent = (JComponent) bindingAssociation.getComponent();
			
			if(jcomponent instanceof  IBallValidateWidget ){
				((IBallValidateWidget) jcomponent).verifyPass();
			}
			
			
			boolean isNull = false;
			if (getValue() == null) {
				isNull = true;
			}
			if (getValue() instanceof String) {
				if ("".equals(((String) getValue()).trim()))
					isNull = true;
			}
			if (isNull) {
				
				
				return ValidationUtils.createErrors(String.format("%s 不能为空.", fieldName), errorKey);
			}
			return null;
		}
	}
}
