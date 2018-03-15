package org.hbhk.aili.client.core.widget.print.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.widget.print.ui.BillTemplateChooser;

public class PrintCancleAction implements ActionListener {
	private static final Log log = LogFactory.getLog(PrintCancleAction.class);
	private BillTemplateChooser chooser;
	
	public PrintCancleAction(){}
	
	public PrintCancleAction(BillTemplateChooser chooser) {
		this.chooser = chooser;
	}
	
	/** 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		chooser.setVisible(false);
		chooser.dispose();
		chooser = null;
		log.debug("模板选择窗口关闭完成");
	}

}
