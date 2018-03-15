package org.hbhk.aili.client.core.widget.print.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.widget.print.action.PrintCancleAction;
import org.hbhk.aili.client.core.widget.print.action.PrintConfirmAction;
import org.hbhk.aili.client.core.widget.print.action.TreeNodeSelectAction;
import org.hbhk.aili.client.core.widget.print.entity.BillTemplateInfo;
import org.hbhk.aili.client.core.widget.print.entity.BillTemplateSort;
import org.hbhk.aili.client.core.widget.print.exception.PrintException;
import org.hbhk.aili.client.core.widget.print.service.IBillTemplateInfoService;
import org.hbhk.aili.client.core.widget.print.service.impl.BillTemplateServiceImpl;

/**
 * 
 * Description: 表单模板选择器：主要提供了一个表单模板浏览与选择的视图， 供应用开发人员使用。
 * 选择器从外部接受表单模板的类别分类数据（数据可能为一个List或一个Map），浏览分类时可选择
 * 该分类下的所有模板并显示出模板的表格列表，选中一个模板后可预览并打印表单。
 * 
 * 该控件的预览与打印功能基于 jasperreports 开源类库，需要打印的模板数据格式必须要符合
 * jasperreports 中规定的模板格式。iReport 为我们提供了定义模板的可视化操作界面，在其
 * 提供的界面中可以定义我们的模板，当然，如果你已经熟悉了jasperreports 中模板的数据定义
 * 格式，你可以用文本编辑器定义与修改自己的模板文件。
 * 
 * 
 * 模板选择器所需要的数据：有两种不同的数据，
 * 1：表单模板的类别分类数据；
 * 2：表单模板列表数据； 
 * 此处的两种数据通常情况下存在于远程数据库中，
 * 也有可能存在于远程服务器的特定目录下。
 *  
 */
@SuppressWarnings("serial")
public class BillTemplateChooser extends JFrame {
	private static final Log log = LogFactory.getLog(BillTemplateChooser.class);
	private static final II18n i18n = I18nManager.getI18n(BillTemplateChooser.class);
	
	private int windowWidth;
	private int windowHeight;

	private JPanel contentPanel;   // 窗口容器面板
	private JSplitPane choosePane;
	private JScrollPane navigatorPane;
	private JScrollPane tablePane;
	private JPanel buttonPanel;    // 按钮区域
	
	private JTree navigatorTree;
	private JTable itemTable;	

	private JButton btnConfirm = new JButton(i18n.get("Confirm"));
	private JButton btnCancle = new JButton(i18n.get("Cancel"));

	private transient List<BillTemplateSort> billTemplateSortList;
	
	private transient IBillTemplateInfoService billTemplateService = new BillTemplateServiceImpl();


	public BillTemplateChooser() {
		try {
			this.setTitle(i18n.get("TemplateChooser"));
		} catch(Exception e) {
			// donothing
		}		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// 设置窗口大小及位置
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.windowWidth = 500;
		this.windowHeight = 550;
		int x = (screen.width - this.windowWidth) / 2;
		int y = (screen.height - this.windowHeight) / 2;
		this.setSize(this.windowWidth, this.windowHeight);
		this.setResizable(false);
		this.setLocation(x, y);

		this.drawContentPanel();
		
		try {
			this.bindAction();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.pack();
		this.setVisible(true);
		log.info("模板选择窗口初始化完成");
	}
	
	/**
	 * 设置窗口界面的总体布局，使用网格包布局管理器
	 */
	private JPanel drawContentPanel() {
		contentPanel = (JPanel)getContentPane();
	//	contentPanel.setBackground(new Color(8, 5, 75));
		
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		// 设置标题及标志区域的布局
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(20, 10, 10, 10);
		contentPanel.add(this.drawChoosePane(), constraints);
		/*
		// 设置上下区域分割线
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(5, 10, 5, 10);
		JPanel linePanel = new JPanel();
		linePanel.add(new JSeparator());		
		contentPanel.add(linePanel, constraints);
		*/
		// 设置按钮区域的布局
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(10, 10, 10, 10);
		contentPanel.add(this.drawButtonPanel(), constraints);
		
		return contentPanel;
	}
	

	/**
	 * 创建模板主区域
	 * 
	 * @return 
	 */
	private JSplitPane drawChoosePane() {
		navigatorPane = this.drawNavigatorPane();
		tablePane = this.drawTemplateTablePane();

		choosePane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, navigatorPane, tablePane);

		return choosePane;
	}

	/**
	 * 创建模板导航树区域
	 * 
	 * @return
	 */
	private JScrollPane drawNavigatorPane() {
		try {
			billTemplateSortList = billTemplateService.getPreformatBillTemplateSortList();
			Map<String, Object> templateData = billTemplateService.invert(billTemplateSortList);
			
			navigatorTree = new JTree(new Hashtable<String, Object>(templateData));
			navigatorPane = new JScrollPane(navigatorTree);    // new JScrollPane 时要把其中放置的组件加进来
		} catch (PrintException e) {
			e.printStackTrace();
		}

		if(navigatorPane == null) {
			navigatorPane = new JScrollPane();
		}

		// 设置Tree的选择模式：一次只能选择一个节点
		navigatorTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		navigatorTree.addTreeSelectionListener(new TreeNodeSelectAction());
		
		navigatorPane.setMinimumSize(new Dimension(230, 400));
		navigatorPane.setMaximumSize(new Dimension(230, 400));
		navigatorPane.setPreferredSize(new Dimension(230, 400));
		
		return navigatorPane;
	}

	/**
	 * 创建模板列表区域
	 * @return
	 */
	private JScrollPane drawTemplateTablePane() {
		if(tablePane == null) {
			tablePane = new JScrollPane();
		}
		tablePane.setMinimumSize(new Dimension(230, 400));
		tablePane.setMaximumSize(new Dimension(230, 400));
		tablePane.setPreferredSize(new Dimension(230,400));
		
		return tablePane;
	}
	
	/**
	 * 创建按钮区域
	 * @return
	 */
	private JPanel drawButtonPanel() {
		if(buttonPanel == null) {
			buttonPanel = new JPanel();
		}
	//	buttonPanel.setBackground(new Color(60, 50, 200));
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		// 添加确定按钮
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(5, 300, 5, 10);
		buttonPanel.add(btnConfirm, constraints);
		
		// 添加取消按钮
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets = new Insets(5, 10, 5, 5);
		buttonPanel.add(btnCancle, constraints);
		
		return buttonPanel;
	}
	
	/**
	 * 设置模板表格中数据
	 */
	public void setTemplateTableData(List<BillTemplateInfo> billTemplateInfoList) {
		String[][] templateItem;    // 表格中显示的模板名称
		
		try {
			Object[] itemTemp = billTemplateInfoList.toArray();
			
			templateItem = new String[itemTemp.length][1];
			for(int i = 0; i < itemTemp.length; i++) {
				BillTemplateInfo template = (BillTemplateInfo)itemTemp[i];
				templateItem[i][0] = template.getTitle();
			}
			
			itemTable = new JTable(templateItem, new String[] {""});			

			if(tablePane == null) {
				tablePane = new JScrollPane();
			}
			tablePane.setMinimumSize(new Dimension(230, 400));
			tablePane.setMaximumSize(new Dimension(230, 400));
			tablePane.setPreferredSize(new Dimension(230,400));			
			
			tablePane = new JScrollPane(itemTable);
			
		} catch (PrintException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 在按钮上绑定事件处理
	 */
	private void bindAction() throws Exception {
		try {
			btnConfirm.addActionListener(new PrintConfirmAction(this));
			btnCancle.addActionListener(new PrintCancleAction(this));
		} catch(Exception e) {
			throw new Exception("BindAction Error" + e.getMessage(),e);
		}
	}
	
	public static void main(String[] args) {
		new BillTemplateChooser();
	}
}
