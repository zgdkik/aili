package org.hbhk.aili.client.core.widget.msgbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.util.WindowUtil;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.widget.itservice.common.ITServiceConstants;
import org.hbhk.aili.client.core.widget.itservice.common.ITServiceInfoDialog;
import org.hbhk.aili.client.core.widget.service.IUploadITService;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

/**
 * IT服务台问题上报弹出窗口
 *
 */
public class MsgBoxForITService  extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2784171166858283440L;

	/**
	 * 一键上报
	 */
	private JButton btnUpload;
	
	/**
	 * 暂不处理
	 */
	private JButton btnClose;
	
	/**
	 * 提示消息
	 */
	private String showMessage;
	
	/**
	 * 国际化对象
	 */
	private II18n i18n = I18nManager.getI18n(MsgBoxForITService.class);
	
	/**
	 * IT服务台一键上报Service
	 */
	private IUploadITService uploadITService;
	
	/**
	 * 文本显示区域
	 */
	private JTextArea textArea;

	/**
	 * 构造函数
	 * @param message
	 */
	public MsgBoxForITService(String message){
		super(ApplicationContext.getApplication().getWorkbench().getFrame());
		this.showMessage=message;
		//uploadITService=FactoryBean.getIUploadITService();
		init(message);
	}
	
	/**
	 * 初始化界面
	 */
	private void init(String message) {
		getContentPane().setLayout(
			new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("10dlu:grow"),
				ColumnSpec.decode("45dlu"),
				ColumnSpec.decode("10dlu"),
				ColumnSpec.decode("45dlu"),
				ColumnSpec.decode("10dlu:grow"),
				ColumnSpec.decode("10dlu"),},
			new RowSpec[] {
				RowSpec.decode("10dlu"),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.MINIMUM, Sizes.constant("15dlu", false), Sizes.constant("100dlu", false)), 1),
				RowSpec.decode("30dlu"),}));
			
			textArea = new JTextArea();
			textArea.setText(message);
			textArea.setEditable(false);			
			getContentPane().add(textArea, "2, 2, 5, 1, fill, fill");
			
			btnUpload = new JButton(i18n.get("pickup.changing.itservice.report"));
			getContentPane().add(btnUpload, "3, 3");		
			btnUpload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean isStart=uploadITService.isStartItServiceUpload();
					//判断是否启用一键上报
					if(isStart){
						// 创建弹出窗口
						ITServiceInfoDialog itDialog = new ITServiceInfoDialog(showMessage,ITServiceConstants.BUSINESS);					
						// 剧中显示弹出窗口
						WindowUtil.centerAndShow(itDialog);
						// 关闭本窗口
						dispose();
					}else{
						MsgBox.showInfo(i18n.get("pickup.itservice.upload.notStart"));
					}					
				}
			});
			
			btnClose = new JButton(i18n.get("pickup.changing.itservice.cancel"));
			getContentPane().add(btnClose, "5, 3");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			pack();
			
			setModal(true);
	}
	
}
