package org.hbhk.aili.client.core.commons.binding.validation.adapters;
import java.awt.Color;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

import org.hbhk.aili.client.core.commons.binding.validation.IAnnotationAdapter;
import org.hbhk.aili.client.core.commons.binding.validation.annotations.Length;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationUtils;
import org.hbhk.aili.client.core.commons.validators.ValueAcceptedValidator;
import org.hbhk.aili.client.core.core.binding.BindingAssociation;
import org.hbhk.aili.client.core.core.binding.annotation.BindingArgsAware;
import org.hbhk.aili.client.core.widget.validatewidget.IBallValidateWidget;
public class LengthAdapter extends BindingArgsAware implements IAnnotationAdapter<Length> {

	@Override
	public IValidator adapt(Length length, Object errorKey) {
		int min = length.min();
		int max = length.max();
		
		if (min < 0 || max < 0 || max < min) {
			return null;
		}
		
		return new LengthValidator(min, max, errorKey, args.get("fieldName"));
	}
	
	private class LengthValidator extends ValueAcceptedValidator {
		private int min;
		private int max;
		private Object errorKey;
		private String fieldName;
		private Border defaultBorder;
		
		public LengthValidator(int min, int max, Object errorKey, String fieldName) {
			this.min = min;
			this.max = max;
			this.errorKey = errorKey;
			this.fieldName = fieldName;
			BindingAssociation bindingAssociation = (BindingAssociation) errorKey;
			
			JComponent jcomponent = (JComponent) bindingAssociation.getComponent();
			defaultBorder = jcomponent.getBorder();
		}
		
		@Override
		public List<ValidationError> validate() {

			
			JComponent jcomponent = null;
			BindingAssociation bindingAssociation = (BindingAssociation) errorKey;
			
			jcomponent = (JComponent) bindingAssociation.getComponent();
			
			if(jcomponent instanceof  IBallValidateWidget ){
				((IBallValidateWidget) jcomponent).verifyPass();
			}
			
			if (getValue() instanceof String) {
				String sTarget = (String)getValue();
				if (sTarget.length() < min || sTarget.length() > max) {
					//BindingAssociation bindingAssociation = (BindingAssociation) errorKey;
					
					//jcomponent = (JComponent) bindingAssociation.getComponent();
					
					jcomponent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
					
					return ValidationUtils.createErrors(String.format("%s's length must between %d and %d",
							fieldName, min, max), errorKey);
				}
			}
			if (defaultBorder!=null) {
				//BindingAssociation bindingAssociation = (BindingAssociation) errorKey;
				
				//jcomponent = (JComponent) bindingAssociation.getComponent();
				
				jcomponent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
				jcomponent.setBorder(defaultBorder);
			}
			
			return null;
		}
	}
}
