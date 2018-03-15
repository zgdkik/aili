package org.hbhk.aili.client.main.client.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.LoginException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hbhk.aili.client.boot.app.UserEntity;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.component.remote.BusinessException;
import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ModifyPasswordUI extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtuser;
	private JPasswordField txtcurrentPassword;
	private JPasswordField txtmodifyPassword;
	private JPasswordField txtpasswordAgen;
	// 日志对象
	protected final static Logger LOG = LoggerFactory
			.getLogger(ModifyPasswordUI.class.getName());
	/**
	 * 国际化
	 */
	private static final II18n i18n = I18nManager
			.getI18n(ModifyPasswordUI.class);

	public ModifyPasswordUI(Frame frame) {
		super(frame,true);
		init();
	}

	public ModifyPasswordUI() {
		init();
	}

	void init() {
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(47dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(156dlu;default)"),
				ColumnSpec.decode("max(4dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel label = new JLabel(i18n.get("modifyUI.user"));
		getContentPane().add(label, "2, 2, left, default");

		txtuser = new JTextField();
		getContentPane().add(txtuser, "4, 2, fill, default");
		txtuser.setColumns(10);

		JLabel label_1 = new JLabel(i18n.get("modifyUI.current"));
		getContentPane().add(label_1, "2, 4, left, default");

		txtcurrentPassword = new JPasswordField();
		getContentPane().add(txtcurrentPassword, "4, 4, fill, default");

		JLabel lblNewLabel = new JLabel(i18n.get("modifyUI.newPassword"));
		getContentPane().add(lblNewLabel, "2, 6, left, default");

		txtmodifyPassword = new JPasswordField();
		getContentPane().add(txtmodifyPassword, "4, 6, fill, default");

		JLabel label_2 = new JLabel(i18n.get("modifyUI.modifyCheck"));
		getContentPane().add(label_2, "2, 8, left, default");

		txtpasswordAgen = new JPasswordField();
		getContentPane().add(txtpasswordAgen, "4, 8, fill, default");

		JPanel panel = new JPanel();
		panel.setBorder(null);
		getContentPane().add(panel, "2, 10, 3, 1, fill, fill");

		JButton btnsave = new JButton(i18n.get("modifyUI.save"));

		UserEntity user = (UserEntity) SessionContext.getCurrentUser();

		txtuser.setText("user.getUserName()");
		txtuser.setEnabled(false);
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String currentPassword = new String(txtcurrentPassword
							.getPassword());
					String pwd = "";//CryptoUtil.digestByMD5(currentPassword);

					UserEntity user = (UserEntity) SessionContext
							.getCurrentUser();

					if (!pwd.equals(user.getPassword())) {

						throw new RuntimeException("modifyUI.passwordWrong");

					}

					String modifyPassword = new String(txtmodifyPassword
							.getPassword());
					String passwordAgen = new String(txtpasswordAgen
							.getPassword());

					if (!modifyPassword.equals(passwordAgen)) {
						throw new RuntimeException("modifyUI.twiceWrong");
					}

//					IWaybillHessianRemoting waybillRemotingService = DefaultRemoteServiceFactory
//							.getService(IWaybillHessianRemoting.class);
//
//					waybillRemotingService.modifyPassword(user.getUserName(),
//							modifyPassword);
					MsgBox.showInfo(i18n.get("modify.saveSuccess"));
					txtcurrentPassword.setText("");
					txtmodifyPassword.setText("");
					txtpasswordAgen.setText("");

				} catch (BusinessException w) {
					MsgBox.showInfo(i18n.get(w.getMessage()));
				}
			}
		});
		panel.add(btnsave);
	}
}
