package org.hbhk.aili.client.start.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.start.util.ClassPathResourceUtil;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ClientAppSetupDialog extends JDialog {
	private static final Log log = LogFactory.getLog(ClientAppSetupDialog.class);
	
	private static final int width=400;
	private static final int height=250;

	private static final long serialVersionUID = -5845024483017058566L;
	private static ClientAppSetupDialog instance;
	private ClientAppSetupDialog() throws Exception {
		initUI();
	}
	
	public static ClientAppSetupDialog getInstance(ClientApp pParent) throws Exception {
		if(instance==null){
			instance = new ClientAppSetupDialog();
		}
		instance.parent = pParent;
		return instance;
	}
	
	private JTextField txtFtpServer = null;
	private JTextField txtFtpPort = null;
	private JTextField txtFtpUserName = null;
	private JPasswordField txtFtpPassword = null;
	
	private JTextField txtHessianServerPort = null;
	private JTextField txtHessianServicePath = null;
	private JTextField txtHessianServiceHost = null;
	private JTextField txtHessianServiceWaittimeout = null;
	private JTextField txtHessianConnectionWaittimeout = null;
	
	private JTextField txtDownloadServer = null;
	private JRadioButton radHttp = null;
	private JRadioButton radFtp = null;
	private JRadioButton radFtpActive = null;
	private JRadioButton radFtpPassive = null;
	private ClientApp parent = null;
	
	private void initparametersetupui(){
		
		JPanel mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		FormLayout layout = new FormLayout(
                "right:60dlu, 6dlu, 50dlu, 4dlu, center:50dlu", // columns  
                "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref"); // rows  
		
		JPanel inputpanel = new JPanel(layout);
        CellConstraints cc = new CellConstraints();
        //ftp设置
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_setup)), cc.xywh(1, 1, 5, 1));
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_server)), cc.xy(1, 3));
        inputpanel.add(txtFtpServer = new JTextField(), cc.xywh(3, 3, 3, 1));
        txtFtpServer.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_port)), cc.xy(1, 5));  
        inputpanel.add(txtFtpPort = new JTextField(), cc.xy(3, 5));
        txtFtpPort.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_username)), cc.xy(1, 7));  
        inputpanel.add(txtFtpUserName = new JTextField(), cc.xy(3, 7));
        txtFtpUserName.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_password)), cc.xy(1, 9));  
        inputpanel.add(txtFtpPassword = new JPasswordField(), cc.xy(3, 9));
        txtFtpPassword.setEnabled(false);
        
        inputpanel.add(new JLabel("---------------------------------------------------------------------------------"), cc.xywh(1, 10, 5, 1));
        //hessian配置
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_hessian_setup)), cc.xywh(1, 11, 5, 1));
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_hessian_service_path)), cc.xy(1, 13));
        inputpanel.add(txtHessianServicePath = new JTextField(), cc.xywh(3, 13, 3, 1));
        txtHessianServicePath.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_hessian_service_host)), cc.xy(1, 15));  
        inputpanel.add(txtHessianServiceHost = new JTextField(), cc.xywh(3, 15, 3, 1));
        txtHessianServiceHost.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_hessian_server_port)), cc.xy(1, 17));  
        inputpanel.add(txtHessianServerPort = new JTextField(), cc.xy(3, 17));
        txtHessianServerPort.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_hessian_service_waittimeout)), cc.xy(1, 19));  
        inputpanel.add(txtHessianServiceWaittimeout = new JTextField(), cc.xy(3, 19));
        txtHessianServiceWaittimeout.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_hessian_connection_waittimeout)), cc.xy(1, 21));  
        inputpanel.add(txtHessianConnectionWaittimeout = new JTextField(), cc.xy(3, 21));
        txtHessianConnectionWaittimeout.setEnabled(false);
  
        inputpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainpanel.add(inputpanel,BorderLayout.CENTER);
	}
	
	public void initUI(){
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_title));

		JPanel mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		FormLayout layout = new FormLayout(
                "right:60dlu, 6dlu, 50dlu, 6dlu, 50dlu, 4dlu ", // columns  
                "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 3dlu, pref"); // rows  
		
		JPanel inputpanel = new JPanel(layout);
        CellConstraints cc = new CellConstraints();
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_setup)), cc.xywh(2, 3, 4, 1));
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_server)), cc.xy(1, 5));
        inputpanel.add(txtDownloadServer = new JTextField(), cc.xywh(3, 5, 3 , 1));
        txtDownloadServer.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_type)), cc.xy(1, 7));  
        radHttp = new JRadioButton(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_type_http));
        radHttp.addActionListener(new RadioTypeClickActionListner());
        radFtp = new JRadioButton(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_type_ftp));
        radFtp.addActionListener(new RadioTypeClickActionListner());
        ButtonGroup radGrouptype = new ButtonGroup();
        radGrouptype.add(radFtp);
        radGrouptype.add(radHttp);
        inputpanel.add(radHttp, cc.xy(3, 7));
        inputpanel.add(radFtp, cc.xy(5, 7));
        radFtp.setEnabled(false);
        radHttp.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_ftp_mode)), cc.xy(1, 9));  
        radFtpActive = new JRadioButton(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_ftp_mode_active));
        radFtpPassive = new JRadioButton(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_download_ftp_mode_passive));
        ButtonGroup radGroupmode = new ButtonGroup();
        radGroupmode.add(radFtpActive);
        radGroupmode.add(radFtpPassive);
        inputpanel.add(radFtpPassive, cc.xy(3, 9));
        inputpanel.add(radFtpActive, cc.xy(5, 9));
        radFtpPassive.setEnabled(false);
        radFtpActive.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_port)), cc.xy(1, 11));  
        inputpanel.add(txtFtpPort = new JTextField(), cc.xy(3, 11));
        txtFtpPort.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_username)), cc.xy(1, 13));  
        inputpanel.add(txtFtpUserName = new JTextField(), cc.xy(3, 13));
        txtFtpUserName.setEnabled(false);
        
        inputpanel.add(new JLabel(ClientAppMessages.getString(ClientAppConstants.key_ui_setup_lbl_ftp_password)), cc.xy(1, 15));  
        inputpanel.add(txtFtpPassword = new JPasswordField(), cc.xywh(3, 15, 2, 1));
        txtFtpPassword.setEnabled(false);
        
        mainpanel.add(inputpanel,BorderLayout.CENTER);
 
       
        
        JButton closebtn = new JButton(ClientAppMessages.getString(ClientAppConstants.key_ui_btn_close));
        closebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideSetup();
			}
		});
        
        JPanel btnpanel = new JPanel(new FlowLayout());
      //  btnpanel.add(clearbtn);
        btnpanel.add(closebtn);
        
        mainpanel.add(btnpanel, BorderLayout.SOUTH);
		getContentPane().add(mainpanel);
	}
	
	class RadioTypeClickActionListner implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(radFtp.isSelected()){
				txtFtpPort.setEnabled(true);
				txtFtpUserName.setEnabled(true);
				txtFtpPassword.setEnabled(true);
				radFtpActive.setEnabled(true);
				radFtpPassive.setEnabled(true);
			}
			if(radHttp.isSelected()){
				txtFtpPort.setEnabled(false);
				txtFtpUserName.setEnabled(false);
				txtFtpPassword.setEnabled(false);
				radFtpActive.setEnabled(false);
				radFtpPassive.setEnabled(false);
			}
		}
	}
	
	public void resetDefaultSetup(String server, String type, String ftpport,
			String ftpmode, String ftpusername, String ftppassword)
			throws Exception {
		//loadFtpDefaultSetup();
		//loadHessianDefaultSetup();
		if(txtDownloadServer!=null){
			txtDownloadServer.setText(server);
		}
		if(ftpport!=null){
			txtFtpPort.setText(ftpport);
		}
		if(radFtp!=null && ClientAppConstants.key_download_type_ftp.equals(type)){
			radFtp.setSelected(true);
			txtFtpPort.setEnabled(false);
			txtFtpUserName.setEnabled(false);
			txtFtpPassword.setEnabled(false);
			radFtpActive.setEnabled(false);
			radFtpPassive.setEnabled(false);
		}
		if(radHttp!=null && ClientAppConstants.key_download_type_http.equals(type)){
			radHttp.setSelected(true);
			txtFtpPort.setEnabled(false);
			txtFtpUserName.setEnabled(false);
			txtFtpPassword.setEnabled(false);
			radFtpActive.setEnabled(false);
			radFtpPassive.setEnabled(false);
		}
		
		if(radFtpActive!=null && ClientAppConstants.key_ftp_mode_Active.equals(ftpmode)){
			radFtpActive.setSelected(true);
		}
		if(radFtpPassive!=null && ClientAppConstants.key_ftp_mode_Passive.equals(ftpmode)){
			radFtpPassive.setSelected(true);
		}
		
		if(ftpusername!=null){
			txtFtpUserName.setText(ftpusername);
		}
		if(ftppassword!=null){
			txtFtpPassword.setText(ftppassword);
		}
		
	}
	
	
	
	
	
	
	
	public void pop(){
		setSize(new Dimension(width,height));
		double width_ = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height_ = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width_ - width) / 2,
				(int) (height_ - height) / 2);
		
		this.setModal(true);
		setResizable(false);
		this.setVisible(true);
	}
	
	public void hideSetup(){
		this.setVisible(false);
	}
}