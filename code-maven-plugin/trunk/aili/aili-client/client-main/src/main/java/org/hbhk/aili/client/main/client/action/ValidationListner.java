package org.hbhk.aili.client.main.client.action;

import java.awt.Component;

import org.hbhk.aili.client.core.commons.validation.IValidationListener;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationErrorEvent;
import org.hbhk.aili.client.core.core.binding.BindingAssociation;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;
import org.hbhk.aili.client.core.widget.validatewidget.IBallValidateWidget;

public class ValidationListner  implements IValidationListener {

	/**
	 * 功能：validationError 监听到错误后，弹出信息框
	 */
	@Override
	public void validationError(ValidationErrorEvent e) {
		for (ValidationError error : e.getErrors()) {

			final Component jComponent = ((BindingAssociation) error.getKey())
					.getComponent();
			if (jComponent instanceof IBallValidateWidget) {
				IBallValidateWidget field = (IBallValidateWidget) jComponent;
				field.verifyWrong(error.getMessage());
			} else {
				MsgBox.showError(error.getMessage());
			}
		}

	}

}
