package org.hbhk.aili.client.main.client.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.hbhk.aili.client.core.component.buttonaction.annotation.ButtonAction;
import org.hbhk.aili.client.core.component.buttonaction.factory.ButtonActionFactory;
import org.hbhk.aili.client.core.component.focuspolicy.annotation.FocusSeq;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.application.IApplicationAware;
import org.hbhk.aili.client.core.core.binding.BindingFactory;
import org.hbhk.aili.client.core.core.binding.IBinder;
import org.hbhk.aili.client.core.core.binding.IBindingListener;
import org.hbhk.aili.client.core.core.binding.annotation.Bind;
import org.hbhk.aili.client.core.core.workbench.IEditor;
import org.hbhk.aili.client.core.widget.validatewidget.JTextFieldValidate;
import org.hbhk.aili.client.main.client.action.TestBindingListener;
import org.hbhk.aili.client.main.client.action.TestButtonAction;
import org.hbhk.aili.client.main.client.action.ValidationData;
import org.hbhk.aili.client.main.client.action.ValidationListner;
import org.hbhk.aili.client.main.client.vo.TestBindVo;

public class TestUI extends JPanel implements IApplicationAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5041443422278566132L;

	private IEditor editor;

	private IApplication application;

	@Bind("name1")
	@FocusSeq(seq=1)
	private JTextField name1;

	@Bind("sex")
	@FocusSeq(seq=2)
	private JTextField sex;
	
	@Bind("tfv")
	@FocusSeq(seq=3)
	private JTextFieldValidate tfv; 
	
	@ButtonAction(type = TestButtonAction.class)
	private JButton b1 = new JButton("我是按钮");
	

	// 绑定接口
	private IBinder<TestBindVo> binder;
	private IBindingListener listener;//事件监听

	private TestBindVo  bindVo;
	
	@Override
	public void setApplication(IApplication application) {
		this.application = application;
	}

	public IEditor getEditor() {
		return editor;
	}

	public void setEditor(IEditor editor) {
		this.editor = editor;
	}

	public void initUI() {
		if (SwingUtilities.isEventDispatchThread()) {
			initCommonWaybillEditUI();
		} else {
			// 由于是异步打开窗口 所以需要放在swing专用图形线程中，否则界面会出错
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					initCommonWaybillEditUI();
				}
			});
		}

	}

	private void initCommonWaybillEditUI() {
	
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		name1 = new JTextField();
		sex = new JTextField();
		// 设置标题及标志区域的布局
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.ipady = 0;
		this.add(name1, constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.ipady = 10;
		tfv = new JTextFieldValidate();
		this.add(tfv, constraints);
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.ipady = 20;
		b1 = new JButton("我是按钮");
		this.add(b1, constraints);
		bindListener();
		// constraints.gridx = 0;
		// constraints.gridy = 10;
		// constraints.gridwidth = 1;
		// constraints.gridheight = 2;
		// constraints.ipady = 30;
		// b1 = new JButton("我是按钮");
		// this.add(b1, constraints);
	}
	/**
	 * 
	 * @description:对按钮、文本框等控件进行绑定
	 */
	private void bindListener() {
		//FocusPolicyFactory.getIntsance().setFocusTraversalPolicy(this);
		ButtonActionFactory.getIntsance().bindButtonAction(this);
		// 如果需要在输入后失去焦点的时候进行绑定，可以用下面的方法
		binder = BindingFactory.getIntsance().moderateBind(
				TestBindVo.class, this, new ValidationData(), true);

		binder.addValidationListener(new ValidationListner());
		listener = new TestBindingListener(this);
		binder.addBindingListener(listener);
		this.setBindVo(binder.getBean());
		
	}

	public JTextField getName1() {
		return name1;
	}

	public void setName1(JTextField name1) {
		this.name1 = name1;
	}

	public JTextField getSex() {
		return sex;
	}

	public void setSex(JTextField sex) {
		this.sex = sex;
	}

	public IApplication getApplication() {
		return application;
	}

	public TestBindVo getBindVo() {
		return bindVo;
	}

	public void setBindVo(TestBindVo bindVo) {
		this.bindVo = bindVo;
	}

	public IBinder<TestBindVo> getBinder() {
		return binder;
	}

	public void setBinder(IBinder<TestBindVo> binder) {
		this.binder = binder;
	}

	public JTextFieldValidate getTfv() {
		return tfv;
	}

	public void setTfv(JTextFieldValidate tfv) {
		this.tfv = tfv;
	}

	
}
