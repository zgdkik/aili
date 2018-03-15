package org.hbhk.aili.client.core.commons.binding.validation.adapters;

import java.awt.Color;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

import org.hbhk.aili.client.core.commons.binding.validation.IAnnotationAdapter;
import org.hbhk.aili.client.core.commons.binding.validation.annotations.Mobile;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationUtils;
import org.hbhk.aili.client.core.commons.validators.ValueAcceptedValidator;
import org.hbhk.aili.client.core.core.binding.BindingAssociation;
import org.hbhk.aili.client.core.core.binding.annotation.BindingArgsAware;
import org.hbhk.aili.client.core.widget.validatewidget.IBallValidateWidget;
 
public class MobileAdapter extends BindingArgsAware implements IAnnotationAdapter<Mobile> {

	@Override
	public IValidator adapt(Mobile annotation, Object errorKey) {
		
		return new MobileValidator(errorKey);
	}
	
	private class MobileValidator extends ValueAcceptedValidator{
		
		private Object errorKey;
		private BorderDecorate borderDecorate;
		public MobileValidator(Object errorKey) {
			this.errorKey = errorKey;
		}
		@Override
		public List<ValidationError> validate() {
			
			BindingAssociation bindingAssociation = (BindingAssociation) errorKey;
			
			JComponent jcomponent = (JComponent) bindingAssociation.getComponent();
			
			if(jcomponent instanceof  IBallValidateWidget ){
				((IBallValidateWidget) jcomponent).verifyPass();
			}
			
			
			if (getValue()==null) {
				return null;
			}
			
			if (!(getValue() instanceof String)) {
				return ValidationUtils.createErrors(String.format("%s not is a string",getValue().getClass()), errorKey);
			}
			String mobile = (String) getValue();
			//校验手机
			Pattern pattern = Pattern.compile("1[0-9]{10}");
			Matcher matcher = pattern.matcher(mobile);
			if (!matcher.matches()) {
				
				if (errorKey instanceof BindingAssociation) {
					
					if (borderDecorate == null) {
						borderDecorate = new BorderDecorate((BindingAssociation)errorKey);
					}
					borderDecorate.setBorder(Color.RED);
				}
				return ValidationUtils.createErrors(String.format("%s 号码错误\n",getValue()), errorKey);
			}
			if (borderDecorate!=null) {
				borderDecorate.resetBorder();
			}
			return null;
		}
		
	}
	
	class BorderDecorate {

		private Border oldBorder;
		private JComponent component;

		public BorderDecorate(BindingAssociation association) {
			component = (JComponent) association.getComponent();
			oldBorder = component.getBorder();
		}

		public void setBorder(Color color) {
			Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, color);
			component.setBorder(border);
		}

		public void resetBorder() {
			component.setBorder(oldBorder);

		}
	}
}
