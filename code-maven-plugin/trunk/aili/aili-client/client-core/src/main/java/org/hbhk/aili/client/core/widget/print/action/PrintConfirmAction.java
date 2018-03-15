/**
 * 
 */
package org.hbhk.aili.client.core.widget.print.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.widget.print.exception.PrintException;
import org.hbhk.aili.client.core.widget.print.ui.BillTemplateChooser;
import org.hbhk.aili.client.core.widget.print.util.PlainPrintUtil;

/**
 * All rights reserved.
 */

@SuppressWarnings({"rawtypes","unused","unchecked"})
public class PrintConfirmAction implements ActionListener {
	
	private static final Log log = LogFactory.getLog(PrintConfirmAction.class);
	
	private PlainPrintUtil printService = new PlainPrintUtil();
	private BillTemplateChooser chooser;
	private String reportPath;
	
	public PrintConfirmAction(){}
	
	public PrintConfirmAction(BillTemplateChooser chooser) {
		this.chooser = chooser;
	}
	
	public PrintConfirmAction(BillTemplateChooser chooser, String reportPath) {
		this.chooser = chooser;
		this.reportPath = reportPath;
	}

	/** 
	 * 在模板选择框中，选中一个模板后，点击确定按钮触发的事件
	 * 事件：用模板生成可预览文件，并将该文件显示在打印预览中
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		 String xmlPath = "E:/workspace/aili00/aili/aili-client/client-core/src/main/java/org/hbhk/aili/client/core/widget/print/template/helloReport.jrxml";
		// String xmlPath = "src/com/deppon/foss/framework/client/widget/print/template/helloReport.jrprint";
		
		this.reportPath = xmlPath;
		
		try {
			printService.viewFromFile(this.reportPath, null, null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	

}
