package org.hbhk.aili.client.main.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.boot.app.Application;
import org.hbhk.aili.client.boot.app.UserEntity;
import org.hbhk.aili.client.boot.autorun.AutoRun;
import org.hbhk.aili.client.boot.autorun.AutoRunPhase;
import org.hbhk.aili.client.boot.autorun.AutoRunUtil;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.task.impl.TaskService;
import org.hbhk.aili.client.core.commons.util.ImageUtil;
import org.hbhk.aili.client.core.component.task.TaskBackgroundDialog;
import org.hbhk.aili.client.core.component.task.TaskStatusButton;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.application.IApplicationAware;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.core.core.workbench.EditorConfig;
import org.hbhk.aili.client.core.core.workbench.IPluginAware;
import org.hbhk.aili.client.core.core.workbench.IWorkbench;
import org.hbhk.aili.client.core.core.workbench.impl.synthetica.DockableFrame;
import org.hbhk.aili.client.core.core.workbench.impl.synthetica.Workbench;
import org.hbhk.aili.client.main.client.action.LockScreenActionListener;
import org.java.plugin.Plugin;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.plaf.basic.BasicStatusBarUI;


/**
 * * @设计目标：
 * @2、 易于扩展，在增加页面元素时，可以通过扩展增加
 * @3、 菜单功能：1）、可以通过plugin 来增加菜单；2）可以自定义布局且可以保存 3）可绑定按钮
 * @4、 消息实现：1）图标闪动 2）消息弹出
 */
public class MainFrame extends DockableFrame implements IPluginAware,
		IApplicationAware {
	
	/**
	 * 
	 */
	private static final String DISCONNECTIONGIF = "disconnection.gif";

	/**
	 * 
	 */
	private static final String CONNECTIONGIF = "connection.gif";

	/**
	 * 600
	 */
	private static final int SIXHUNDRED = 600;

	/**
	 * 500
	 */
	private static final int FIVEHUNDRED = 500;

	/**
	 * 134 for position
	 */
	private static final int ONETHREEFOUR = 134;

	/**
	 * 98 for position
	 */
	private static final int NINETYEIGHT = 98;

	/**
	 * 94 for position
	 */
	private static final int NINETYFOUR = 94;

	/**
	 * COLOR
	 */
	private static final int COLORCONSTANT = 0x00373C64;

	/**
	 * 155
	 */
	private static final int ONEFIVEFIVE = 155;

	/**
	 * 129
	 */
	private static final int ONETWONIVE = 129;

	/**
	 * 126
	 */
	private static final int ONETWOSIX = 126;

	/**
	 * 20
	 */
	private static final int TWENTY = 20;
	
	
	private static final Log log = LogFactory.getLog(MainFrame.class);
	

	private static final long serialVersionUID = 1L;

	/**
	 * 国际化对象
	 */
	private II18n i18n = I18nManager.getI18n(MainFrame.class); 

	/**
	 * plugin 插件对象
	 */
	private Plugin plugin;
	
	/**
	 * Appication上下文对象
	 */
	private IApplication application;
	
	/**
	 * head panel 面板
	 */
	private JPanel headPanel;
	
	private static String netWorkIconGif;

	/**
	 * taskService 服务对象
	 */
	private transient ITaskService taskService;
	
	
	private static JButton netWorkIcon;
	
//	IRemoteServer remoteServer = DefaultRemoteServiceFactory.getInstance().getRemoteServer();
//	NetworkMonitor networkMonitor = remoteServer.getTransport().getNetworkMonitor();
//	
	/**
	 * 
	 * 构造一个主框架
	 */
	public MainFrame() {
		super();
		//set default size
		//获取屏幕分辨率大小
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//获取屏幕容器边界
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());  
		//宽
		int frameWidth = 1280;
		//高
		int frameHeight = Math.min(1024, screen.height - insets.bottom);
		/**
		 * 创建Dimension对象size，该对象封装了一个构件的高度和宽度
		 */
		Dimension size = new Dimension(1280, 1024);
		/**
		 * 设置最小尺寸
		 */
		setMaximumSize(size);
		//set not resize 
		//修改后样式
		/**
		 * 创建并设置各种颜色
		 */
		Color backGround = new ColorUIResource(new Color(249, 249, 249));
		Color inactiveBackground = new ColorUIResource(new Color(225, 225, 225));
		Color foreground = new ColorUIResource(new Color(0x424242));
		Color listBackground = new ColorUIResource(Color.white);
		/**
		 * 设置面板背景
		 */
		UIManager.put("Panel.background", backGround);
		/**
		 * 设置复选框背景
		 */
		UIManager.put("CheckBox.background", backGround);
		/**
		 * 设置单选按钮背景
		 */
		UIManager.put("RadioButton.background", backGround);
		/**
		 * 设置集合背景
		 */
		UIManager.put("List.background", backGround);
		/**
		 * 设置对话框背景
		 */
		UIManager.put("OptionPane.background",  listBackground);
		/**
		 * 设置文本域背景
		 */
		UIManager.put("TextArea.background", listBackground);
		/**
		 * 设置面板禁用的背景
		 */
		UIManager.put("TextField.disabledBackground", inactiveBackground);
		/**
		 * 设置文本框不活动的背景
		 */
		UIManager.put("TextField.inactiveBackground", inactiveBackground);
		/**
		 * 设置可伸缩性文本框不活动的背景
		 */
		UIManager.put("FormattedTextField.inactiveBackground", inactiveBackground);
		/**
		 * 设置组合框禁用的背景
		 */
		UIManager.put("ComboBox.disabledBackground",  inactiveBackground);
		/**
		 * 设置无效的窗口标题栏的背景色的 Color结构
		 */
		UIManager.put("inactiveCaption",  inactiveBackground);
		/**
		 * 设置设置组合框的按钮背景
		 */
		UIManager.put("ComboBox.buttonBackground",  inactiveBackground);
		/**
		 * 设置组合框的暗色按钮
		 */
		UIManager.put("ComboBox.buttonDarkShadow",  inactiveBackground);
		/**
		 * 设置组合框的高亮按钮
		 */
		UIManager.put("ComboBox.buttonHighlight",  inactiveBackground);
		/**
		 * 设置组合框界面
		 */
		UIManager.put("ComboBoxUI", "com.deppon.foss.framework.client.widget.combbox.RollingCycleComboBoxUI");
		/**
		 * 设置对话框界面
		 */
		UIManager.put("OptionPaneUI", "javax.swing.plaf.basic.BasicOptionPaneUI");
		/**
		 * 设置文本域界面
		 */
		UIManager.put("TextAreaUI", "javax.swing.plaf.basic.BasicTextAreaUI");
		//Table
		/**
		 * 设置表格头部界面
		 */
		UIManager.put("TableHeaderUI", "javax.swing.plaf.basic.BasicTableHeaderUI");
		/**
		 * 设置表格头部背景
		 */
		UIManager.put("TableHeader.background", inactiveBackground);
		/**
		 * 设置表格的drop单元格背景
		 */
		UIManager.put("Table.dropCellBackground", inactiveBackground);
		
		/**
		 * BUTTON
		 */
		
		/**
		 * 设置按钮的禁用工具条背景
		 */
		UIManager.put("Button.disabledToolBarBorderBackground",  inactiveBackground);
		/**
		 * 设置按钮的禁用风格
		 */
		UIManager.put("Button.disabledShadow",  inactiveBackground);
		/**
		 * 设置按钮的禁用Gray范围
		 */
		UIManager.put("Button.disabledGrayRange",  inactiveBackground);
		/**
		 * 设置标签的前景色
		 */
		UIManager.put("Label.foreground", foreground);
		
		//Title
		/**
		 * 设置标题边框的标题颜色
		 */
		UIManager.put("TitledBorder.titleColor",new ColorUIResource(0x4176b6));
		/**
		 * 设置标题边框的字体样式
		 */
		UIManager.put("TitledBorder.font",new FontUIResource(new Font("宋体", Font.BOLD, 13)));
		
		//create service instance
		/**
		 * 创建任务服务
		 */
		taskService = new TaskService();
		//create dialog
		/**
		 * 创建模式对话框
		 */
//		new TaskForegroundDialog(taskService);
	}
	
	/**
	 * 
	 * 设置plugin插件
	 */
	@Override
	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	

	/**
	 * 
	 * 加载workbean需要加载的注释
	 */
	public void init() {
		//创建共组台对象
		IWorkbench workbench = new Workbench(ApplicationContext.getApplication(),
				plugin.getManager(), this);
		//set work bench
		/**
		 * 设置工作台
		 */
		this.setWorkbench(workbench);
		//set work bence to application context
		ApplicationContext.getApplication().setWorkbench(workbench);
		//get head
		/**
		 * 获取面板
		 */
		headPanel=getHeadPanel();
		//north panel
		/**
		 * 向面板添加headPanel
		 */
		((Workbench)workbench).getFrontPanel().add(headPanel, BorderLayout.NORTH);
		//south panel
		/**
		 * 向面板添加状态条
		 */
		((Workbench)workbench).getFrontPanel().add(getStatusBar(), BorderLayout.SOUTH);
		/**
		 * 向背景面板添加JLabel
		 */
		((Workbench)workbench).getBackgroundPanel().add(new JLabel(ImageUtil.getImageIcon(this.getClass(), "banner1.png")), BorderLayout.NORTH);
		/**
		 * 向背景面板添加边框图片
		 */
		((Workbench)workbench).getBackgroundPanel().add(getBorderImage(), BorderLayout.CENTER);
		/**
		 * 设置背景面板的背景
		 */
		((Workbench)workbench).getBackgroundPanel().setBackground(Color.red);
		//create config
		/**
		 * 创建编辑器配置对象
		 */
		EditorConfig editConfig = new EditorConfig();
		//set title
		/**
		 * 设置标题
		 */
		editConfig.setTitle("功能菜单");
		//set plugin id
		/**
		 * 设置插件ID
		 */
		editConfig.setPluginId("org.hbhk.aili.client.mainframe");
		//set id
		/**
		 * 设置ID
		 */
		editConfig.setId("module.mainframe");
		//open editors
		/**
		 * 打开编辑器
		 */
		String mainUIClsName = MainUI.class.getName();
		application.openEditor(editConfig,mainUIClsName);
		
		// start 新增:将下载功能放入任务中
		/*
		 * 自动运行
		 * 当登陆状态是在线的时候才启动，因为现在用了两套服务地址，需要把开单服务和下载服务一起控制
		 */
		//if(WaybillConstants.ONLINE_LOGIN.equals(SessionContext.get(WaybillConstants.LOGIN_TYPE).toString())){
			List<AutoRun> autoRuns = AutoRunUtil.scanAutoRuns((Application)ApplicationContext.getApplication());
			for(AutoRun auto : autoRuns){
				if(AutoRunPhase.BACKGROUND_LOGIN.equals(auto.phase)){
					taskService.execute(auto.autoRunner,false,false);
				}
			}
		//}
		workbench.setTaskService(taskService);
		// end 新增:将下载功能放入任务中
	}

	private JPanel getBorderImage() {
	    	/**
		 * 新建一个面板
		 */
		JPanel panel = new JPanel();
		/**
		 * 设置面板背景
		 */
		panel.setBackground(new Color(0xb5b5b5));
		return panel;
	}


	/**
	 * 
	 * 设置框的头部
	 */
	private JPanel getHeadPanel() {
		//create panel
	    	/**
		 * 创建菜单面板
		 */
		JPanel headPanel2 = new MenuBar(this);//zxy 20140423 MANA-2018 修改:传入frame
		//设置透明
		headPanel2.setOpaque(false);
		//return obj
		return headPanel2;
	}

	/**
	 * @功能：statusBar
	 */
	private JXStatusBar getStatusBar() {
		//contraint
	    	/**
		 * 创建JXStatusBar.Constraint对象
		 */
		JXStatusBar.Constraint ct = new JXStatusBar.Constraint(new Insets(4, TWENTY,
				4, TWENTY));
		
		/**
		 * inner class for status bar
		 */
		/**
		 * 创建JXStatusBar对象
		 */
		JXStatusBar statusBar = new JXStatusBar(){			
			/**序列*/
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {			    	
			    	/**
			    	 * 设置控件透明
			    	 */
				setOpaque(false);
				/**
			    	 * 创建Graphics2D对象 
			    	 */
				Graphics2D g2 = (Graphics2D) g;
				/**
			    	 * 创建Paint对象 
			    	 */
				Paint oldPaint = g2.getPaint();
				/**
			    	 * 设置色彩模式
			    	 */
				g2.setPaint(new Color(0xb5b5b5));
				/**
			    	 * 调用fillRect()使所指定的颜色、渐变和模式来填充指定的矩形
			    	 */
				g2.fillRect(0, 0, getWidth(), getHeight());
				/**
			    	 * 设置色彩模式
			    	 */
				g2.setPaint(oldPaint);
				/**
			    	 * 调用父类的paintComponent方法进行界面重绘
			    	 */
				super.paintComponent(g);
			}
		};
		
		//add client property
		/**
		 * 向此组件添加“客户端属性”
		 */
		statusBar.putClientProperty(BasicStatusBarUI.AUTO_ADD_SEPARATOR,
				Boolean.FALSE);
		//userentity
		/**
		 * 获取当前用户信息
		 */
		UserEntity user = (UserEntity) SessionContext.getCurrentUser();
		/**
		 * 定义OrgAdministrativeInfoEntity对象
		 */
//		OrgAdministrativeInfoEntity loginOrg = null;
//		/**
//		 * 定义EmployeeEntity对象
//		 */
//		EmployeeEntity emp = null;
//		/**
//		 * 判断user是否为空
//		 */
//		if(user!=null){
//		    	/**
//		    	 * 获取员工信息
//		    	 */
//			emp = (EmployeeEntity)user.getEmployee();
//			/**
//		    	 * 如果员工信息不为空，则获取其所在部门
//		    	 */
//			if(emp!=null){
//				loginOrg = emp.getDepartment();
//			}
//		}
		//创建一个颜色类
		Color lblColor = new Color(0x343c61);
		/**
	    	 * 设置状态条
	    	 */
		statusBar.add(new JLabel(), new JXStatusBar.Constraint(new Insets(0, TWENTY,
				0, 0)));
		//VERSION
		/**
	    	 * 判断当前用户会话环境的当前版本是否为空
	    	 */
		if(SessionContext.getVersion()!=null){
			//version label
		    	/**
		    	 * 创建JLabel面板
		    	 */
			JLabel versionLabel=new JLabel(i18n.get("mainFrame.version") + "：" + SessionContext.getVersion());
			//color
			/**
		    	 * 设置前景色
		    	 */
			versionLabel.setForeground(lblColor);
			//add version to status bar
			/**
		    	 * 添加版本标签
		    	 */
			statusBar.add(versionLabel);
			//add to separator
			/**
		    	 * 添加JSeparator对象
		    	 */
			statusBar.add(new JSeparator(JSeparator.VERTICAL), ct);
		}
		StringBuffer userNameStr = new StringBuffer("");
		/**
    	 * 判断员工信息是否为空或者员工名称是否为空
    	 */
//		if(emp==null||emp.getEmpName()==null){
//		    	//追加用户名
//			userNameStr.append(user.getUserName());
//		}else{
//		    	//追加用户名和员工姓名
//			userNameStr.append(user.getUserName()).append("  ").append(emp.getEmpName());
//		}
		//根据sonar修改(update wqj by 2013-04-12)
//		if(emp!=null && emp.getEmpName()!=null&&user!=null&&user.getUserName()!=null){
//			//追加用户名和员工姓名
//			userNameStr.append(user.getUserName()).append("  ").append(emp.getEmpName());	    	
//    	}else{
//    		if(user!=null&&user.getUserName()!=null){
//    		//追加用户名
//    		userNameStr.append(user.getUserName());
//    		}
//    	}
		
		//login name label creation
		/**
	    	 * 创建JLabel对象loginName
	    	 */
		JLabel loginName=new JLabel(i18n.get("mainFrame.currentUser")+"：" + userNameStr.toString());
		//text field color
		/**
	    	 * 设置前景色
	    	 */
		loginName.setForeground(lblColor);
		//add to bar
		/**
	    	 * 向状态条里面添加面板
	    	 */
		statusBar.add(loginName);
		//separator is |
		/**
	    	 * 创建JSeparator对象jSeparator
	    	 */
		JSeparator jSeparator = new JSeparator(JSeparator.VERTICAL);
		/**
	    	 * 设置背景
	    	 */
		jSeparator.setBackground(new Color(172, 172, 172));
		statusBar.add(jSeparator, ct);

		String loginDept = "";
		/**
	    	 * 判断字符串loginOrg是否为空，如果不为空，则获取name
	    	 */
//		if(loginOrg!=null) {
//			loginDept = loginOrg.getName();
//		}
		
		//department label
		/**
	    	 * 创建JLabel面板对象department
	    	 */
		JLabel department=new JLabel(i18n.get("mainFrame.currentDepartment")+"：" + loginDept);
		//text field color
		/**
	    	 * 设置前景色
	    	 */
		department.setForeground(lblColor);
		//add to bar
		statusBar.add(department);
		//separator is |
		/**
	    	 * 创建JSeparator对象
	    	 */
		jSeparator = new JSeparator(JSeparator.VERTICAL);
		/**
	    	 * 设置前景色
	    	 */
		jSeparator.setBackground(new Color(172, 172, 172));
		/**
	    	 * 添加至状态条
	    	 */
		statusBar.add(jSeparator, ct);

		//user status label
		/**
	    	 * 创建JLabel对象userstatus SessionContext.get("user_status").toString())
	    	 */
		JLabel userstatus=new JLabel(i18n.get("mainFrame.loginStatus")+"："
				+ "user_status");
		//add to bar
		/**
	    	 * 添加至状态条
	    	 */
		statusBar.add(userstatus,
				JXStatusBar.Constraint.ResizeBehavior.FILL);
		//text field color
		/**
	    	 * 设置前景色
	    	 */
		userstatus.setForeground(lblColor);

		///task dialog
		/**
	    	 * 创建后台任务对话框对象
	    	 */
		TaskBackgroundDialog taskBackgroundDialog = new TaskBackgroundDialog(
				taskService);
		//task status button
		/**
	    	 * 任务状态按钮对象
	    	 */
		TaskStatusButton taskStatusButton = new TaskStatusButton(taskService,
				taskBackgroundDialog);
		//add to bar
		/**
	    	 * 添加至状态条
	    	 */
		statusBar.add(taskStatusButton);

		statusBar.add(new JLabel(), new JXStatusBar.Constraint(new Insets(0, 7,
				0, 0)));
		
		//button create
		/**
	    	 * 创建JButton对象
	    	 */
		JButton btnLock = new JButton(ImageUtil.getImageIcon(this.getClass(), "icon_lock.png"));
		/**
	    	 * 设置边框
	    	 */
		btnLock.setBorder(null);
		//opaque is invalid
		/**
	    	 * 设置控件透明
	    	 */
		btnLock.setOpaque(false);
		//add action listener
		/**
	    	 * 添加监听器
	    	 */
		btnLock.addActionListener(new LockScreenActionListener(headPanel));
		//add button
		statusBar.add(btnLock);
//		if(NetworkStatus.ONLINE == networkMonitor.getStatus()){	
//			netWorkIconGif = CONNECTIONGIF;
//			netWorkIcon = new JButton(ImageUtil.getImageIcon(this.getClass(), netWorkIconGif));
//		}else{
//			netWorkIconGif = DISCONNECTIONGIF;
//			netWorkIcon = new JButton(ImageUtil.getImageIcon(this.getClass(), netWorkIconGif));
//		}
		
//		netWorkIcon.addActionListener(new NetWorkRefreshListener());
//			
//		
//		/**
//    	 * 设置边框
//    	 */
//		netWorkIcon.setBorder(null);
//		//opaque is invalid
//		/**
//	    	 * 设置控件透明
//	    	 */
//		netWorkIcon.setOpaque(false);
		
		/**
	    	 * 将按钮添加至状态条
	    	 */
	//	statusBar.add(netWorkIcon);
		
		Thread t = new  RefrashNetWorkIconThread("RefrashNetWorkIcon");
		Application.getExecutorService().submit(t);
		
		//border
		/**
	    	 * 设置边框
	    	 */
		statusBar.setBorder(new LineBorder( new Color(0xb5b5b5), 4,true));
		//status bar
		/**
		 * 向此组件添加“客户端属性”
		 */
		this.getRootPane().putClientProperty("Synthetica.statusBar", statusBar);
		//back color and style
		/**
		 * 设置背景
		 */
		statusBar.setBackground(new Color(NINETYFOUR,NINETYEIGHT,ONETHREEFOUR));
		//return status bar
		return statusBar;
	}
	
	class NetWorkRefreshListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b =getNetWorkIcon();
//			if(NetworkStatus.ONLINE == networkMonitor.getRefreshStatus()){
//				b.setIcon(ImageUtil.getImageIcon(this.getClass(),CONNECTIONGIF ));
//				b.repaint();
//				netWorkIconGif = CONNECTIONGIF;
//			}else{
//				b.setIcon(ImageUtil.getImageIcon(this.getClass(), DISCONNECTIONGIF));
//				b.repaint();
//				netWorkIconGif = DISCONNECTIONGIF;
//			}
		}
		
	}
	
	/**
	 * 定时循环线程 刷新网络状态
	 *
	 */
	class RefrashNetWorkIconThread extends Thread{
		
		public RefrashNetWorkIconThread() {
			super();
		}

		public RefrashNetWorkIconThread(String name) {
			super(name);
		}

		public void run(){
			try{
				//登录后才刷新
				while (SessionContext.getCurrentUser()!=null){
					final JButton b =getNetWorkIcon();
					if(b!=null){
//						NetworkStatus status = networkMonitor.getRefreshStatus();
//						
//						if(NetworkStatus.ONLINE == status){	
//							if(netWorkIconGif!=null && !netWorkIconGif.equals(CONNECTIONGIF)){
//								SwingUtilities.invokeLater(new Runnable(){
//								
//									public void run() {
//										b.setIcon(ImageUtil.getImageIcon(this.getClass(),CONNECTIONGIF ));
//										b.repaint();
//									}});
//								netWorkIconGif = CONNECTIONGIF;
//							}
//						}else{
//							if(netWorkIconGif!=null && !netWorkIconGif.equals(DISCONNECTIONGIF)){
//								SwingUtilities.invokeLater(new Runnable(){
//									
//									public void run() {
//										b.setIcon(ImageUtil.getImageIcon(this.getClass(), DISCONNECTIONGIF));
//										b.repaint();
//									}});
//								netWorkIconGif = DISCONNECTIONGIF;
//							}
//						}
					}
						
					Thread.sleep(3*60*1000);
				}
			}catch(Exception e){
				log.error(e.getMessage());
			}
		
		};
	}
	
	
	/**
	 * @return the netWorkIcon
	 */
	public static JButton getNetWorkIcon() {
		return netWorkIcon;
	}

	/**
	 * 
	 * 测试主框架
	 */
	public static void main(String[] agrs) {
	    	/**
		 * 创建JFrame对象
		 */
		JFrame frame = new MainFrame();
		/**
		 * 设置是否可见
		 */
		frame.setVisible(true);
		/**
		 * 设置大小
		 */
		frame.setSize(FIVEHUNDRED, SIXHUNDRED);
		/**
		 * 设置默认的关闭对话框
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**
		 * 设置Extended状态
		 */
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	/**
	 * 
	 * 设置应用实例
	 */
	@Override
	public void setApplication(IApplication application) {
		this.application = application;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

 
	  
}