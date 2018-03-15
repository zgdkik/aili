package org.hbhk.aili.client.core.widget.print.action;

import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.widget.print.entity.BillTemplateInfo;
import org.hbhk.aili.client.core.widget.print.service.IBillTemplateInfoService;
import org.hbhk.aili.client.core.widget.print.service.impl.BillTemplateServiceImpl;
import org.hbhk.aili.client.core.widget.print.ui.BillTemplateChooser;

/**
 * All rights reserved.
 */
public class TreeNodeSelectAction implements TreeSelectionListener {
	private static final Log log = LogFactory.getLog(TreeNodeSelectAction.class);
	private BillTemplateChooser chooser;
	
	private List<BillTemplateInfo> billTemplateInfoList;
	
	private IBillTemplateInfoService billTemplateService = new BillTemplateServiceImpl();
	
	public TreeNodeSelectAction(){}
	
	public TreeNodeSelectAction(BillTemplateChooser chooser) {
		this.chooser = chooser;
	}
	
	/**
	 * 
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		log.info("getClass().getResource : " + getClass().getResource("/").toString());
		JTree tree = (JTree) e.getSource();
		DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		
		String nodeName = selectionNode.toString();
		log.info("nodeName: " + nodeName);
		
		String command = e.getPath().toString();
		log.info("command " + command);
		
		
		try {
			billTemplateInfoList = billTemplateService.getBillTemplateInfoList(0L);
			chooser.setTemplateTableData(billTemplateInfoList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
