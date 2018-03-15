package org.hbhk.aili.client.main.client.action;

import java.awt.event.ActionEvent;
import java.util.List;

import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.component.buttonaction.IButtonActionListener;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;
import org.hbhk.aili.client.main.client.ui.TestUI;
import org.hbhk.aili.client.main.client.vo.TestBindVo;

public class TestButtonAction  implements IButtonActionListener<TestUI>{

	private TestUI ui;
	@Override
	public void actionPerformed(ActionEvent e) {
		List<ValidationError> validationErrors = ui.getBinder().validate();
		TestBindVo bindVo =  ui.getBinder().getBean();
		String n = bindVo.getName1();
		MsgBox.showInfo(n, "tittle");
	}

	@Override
	public void setInjectUI(TestUI ui) {
		this.ui= ui;
	}

}
