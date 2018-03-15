package org.hbhk.aili.client.start.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.start.util.ClassPathResourceUtil;
import org.hbhk.aili.client.start.util.Md5;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.LightGray;

public class ClientApp extends JFrame {
	
	private static final Log log = LogFactory.getLog(ClientApp.class);
	private static final long serialVersionUID = -4117347642193675467L;
	private static final int width = 600;
	private static final int height = 250;
	public static File currWorkDir = new File(".");
	public static String downloadserver = "139.196.180.16";
	public static String httpDownloadserver = "139.196.180.16";
	public static String downloadtype = ClientAppConstants.key_download_type_ftp;
	public static String downloadftpport = "21";
	public static String downloadftpmode = ClientAppConstants.key_ftp_mode_Passive;
	public static String downloadftpusername = "hbhk";
	public static String downloadftppassword = "135246";
	public static String downloadpath = null;
	public static String dbOutputFloder = "database";
	public static String dbFileName = dbOutputFloder+File.separator+"hbhkdb.zip";
	
	public static String guistartvalue = "hbhk1aili2gui3start4";

	public static final String imagePrefix="/org/hbhk/aili/client/start/img/";
	
	public static String VERSION ="1.0";
	
	public static void main(String[] args) {
		args = new String[]{"139.196.180.16","http", "appHome/", "hbhk", "135246","8090", "192.168.20.21"};
		String param = System.getProperty("hbhk");
		log.info("input param hbhk : " + param);
//		if(!guistartvalue.equals(param)){
//			ClientAppAlertUtil util = ClientAppAlertUtil.getInstance(null);
//			util.showError("", ClientAppMessages.getString("ui.error.msg.000004"));
//			System.exit(0);
//		}
		
		if(args!=null && args.length ==7){
			downloadserver = args[0];
			try{
				downloadtype = args[1];
			}catch (Exception e) {
				downloadtype = ClientAppConstants.key_download_type_http;
			}
			try{
				downloadpath = args[2];
			}catch (Exception e) {
				downloadpath = null;
			}
			try{
				downloadftpusername = args[3];
			}catch (Exception e) {
				downloadftpusername = "anonymous";
			}
			
			try{
				downloadftppassword = args[4];
			}catch (Exception e) {
				downloadftppassword = "";
			}
			
			try{
				downloadftpport = args[5];
			}catch (Exception e) {
				downloadftpport = "80";
			}
		}
		
		ClientApp client = null;
		try{
			log.info("启动 客户端下载环境");
			client = new ClientApp();
			ProcesserKiller.killOtherTasks(client);
			client.startClient();
			client.forceToAutoUpdate();
		}catch (Exception e) {
			String errtitle = ClientAppMessages.getString("ui.error.msg.000001");
			log.error(errtitle,e);
			ClientAppAlertUtil util = ClientAppAlertUtil.getInstance(client);
			util.showError(errtitle, ClientAppMessages.getString("ui.error.msg.000003"));
		}
	}

	public ClientApp() throws Exception {
		initUILooksAndFeel();
		initDefaultAppAction();
		initUIComponents();
		this.setResizable(false);
	}
	
	private void initDefaultAppAction() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				doSystemExit();
			}
		});
	}
	
	private void initUILooksAndFeel() {
		PlasticLookAndFeel.setPlasticTheme(new LightGray());
		try {
			UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			
		}
	}
	
	private JProgressBar progressBar = null;
	private JLabel progressLabel = null;
	private JButton olrecoverbtn = null;
	private void initUIComponents() throws Exception {
		this.setTitle(ClientAppMessages.getString(ClientAppConstants.key_ui_app_title));
		ClassPathResourceUtil loadutil = new ClassPathResourceUtil();
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource(imagePrefix+"logo.png"));
		this.setIconImage(icon.getImage());
		// create logo panel 
		BufferedImage image = ImageIO.read(loadutil.getInputStream(imagePrefix+"bg.png"));
		ClientImagePane logoPanel = new ClientImagePane(image, ClientImagePane.CENTRE);
		logoPanel.setPreferredSize(new Dimension(599,80));
		logoPanel.setBorder(BorderFactory.createEmptyBorder());

		// create console panel
		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(BorderFactory.createEtchedBorder());
		consolePanel.setLayout(new BorderLayout());
		consolePanel.setPreferredSize(new Dimension(599,170));

		String startmessage = ClientAppMessages.getString("ui.launch.msg.000001" ) +"   : [" + VERSION+"]";
		JLabel processtitlelbl = new JLabel("<html><div style='margin:10px;font-size:12pt;font-family:微软雅黑;'>"+startmessage+"</div></html>");
		consolePanel.add(processtitlelbl,BorderLayout.NORTH);
		
		progressLabel = new JLabel("<html><div style='margin:10px;'></div></html>");
		progressLabel.setPreferredSize(new Dimension(200, 30));
		consolePanel.add(progressLabel,BorderLayout.CENTER);
		
		progressBar = new JProgressBar();
        progressBar.setSize(599, 24);
        consolePanel.add(progressBar,BorderLayout.SOUTH);
		
		// create control button panel
		JPanel controlBtnPanel = new JPanel();
		controlBtnPanel.setBorder(BorderFactory.createEtchedBorder());
		controlBtnPanel.setPreferredSize(new Dimension(599,40));
		controlBtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		olrecoverbtn = new JButton(ClientAppMessages.getString(ClientAppConstants.key_ui_btn_onlinerecover));
		olrecoverbtn.addActionListener(new OnlineRecoverAction());
		JButton closebtn = new JButton(ClientAppMessages.getString(ClientAppConstants.key_ui_btn_close));
		closebtn.addActionListener(new CloseAppAction());
		JButton setupbtn = new JButton(ClientAppMessages.getString(ClientAppConstants.key_ui_btn_setup));
		setupbtn.addActionListener(new SetupClientAppAction());
		
		controlBtnPanel.add(olrecoverbtn);
		controlBtnPanel.add(closebtn);
		controlBtnPanel.add(setupbtn);
				
		setLayout(new BorderLayout());
		getContentPane().add(logoPanel, BorderLayout.NORTH);
		getContentPane().add(consolePanel, BorderLayout.CENTER);
		getContentPane().add(controlBtnPanel, BorderLayout.SOUTH);
	}
	
	public void setProgressPercent(int pos){
		if(progressBar!=null){
			progressBar.setValue(pos);
		}
	}
	
	public void setProgressLabel(String text){
		if(progressLabel!=null){
			progressLabel.setText("<html><div style='margin:10px;font-size:13pt;font-family:微软雅黑;'>"+text+"</div></html>");
		}
	}
	
	public void setOlRecoverbtnEnable(boolean enable){
		if(olrecoverbtn!=null){
			this.olrecoverbtn.setEnabled(enable);
		}
	}
	
	public void startClient() throws Exception {
		setSize(new Dimension(width,height));
		double width_ = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height_ = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width_ - width) / 2,
				(int) (height_ - height) / 2);
		
		//this.setModal(true);
		setResizable(true);
		this.setVisible(true);
	}
	
	public void hideClientUI(){
		this.setVisible(false);
		this.dispose();
	}
	
	public boolean doOnlineRecover(String pdownloadserver,
			String pdownloadtype, String pdownloadftpport,
			String pdownloadftpmode, String pdownloadftpusername,
			String pdownloadftppassword) {
		try{
			
			if(mainDownLoadThread!=null && mainDownLoadThread.getStatus()==1){
				int result = JOptionPane.showConfirmDialog(getClientAppFrame(),
						ClientAppMessages.getString("ui.confirm.msg.000001"), "", JOptionPane.YES_NO_OPTION);

				if (JOptionPane.NO_OPTION == result) {
					return false;
				}
				else {
					mDownThreadStatus = 0;
				}
			}
			
			if(pdownloadserver!=null ){
				downloadserver = pdownloadserver;
			}
			if(pdownloadtype!=null){
				downloadtype = pdownloadtype;
			}
			if(ClientAppConstants.key_download_type_ftp.equals(downloadtype)){
				downloadftpport = pdownloadftpport;
			}
			if(pdownloadftpmode!=null){
				downloadftpmode = pdownloadftpmode;
			}
			if(pdownloadftpusername!=null){
				downloadftpusername = pdownloadftpusername;
			}
			if(pdownloadftppassword!=null){
				downloadftppassword = pdownloadftppassword;
			}
			
			Thread th = new Thread() {
				public void run() {
					try{
						setOlRecoverbtnEnable(false);
						sleep(2000);
						Map<String,String> remoteMap = loadRemoteAppFileMD5Hex();
						List<String> alllist = new ArrayList<String>();
						alllist.addAll(remoteMap.keySet());
						updateFossGuiAppHome(alllist);
					}catch (Exception exp) {
						setOlRecoverbtnEnable(true);
						log.error("Online Recover Action",exp);
						ClientAppAlertUtil util = ClientAppAlertUtil.getInstance(getClientAppFrame());
						util.showError(ClientAppMessages.getString("ui.error.msg.000002"), ClientAppMessages.getString("ui.error.msg.000003"));
					}
				}
			};
			th.start();
			
		}catch (Exception exp) {
			setOlRecoverbtnEnable(true);
			log.error("Online Recover Action",exp);
		}finally {
			
		}
		return true;
	}
	
	class OnlineRecoverAction implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			doOnlineRecover(null,null,null,null,null,null);
		}
	}
	
	class StopDownLoadAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class LaunchFossGuiAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}	
	}
	
	class ShowClientAppLogAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class CloseAppAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			doSystemExit();
		}
	}
	
	class SetupClientAppAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				ClientAppSetupDialog setupDialog = ClientAppSetupDialog.getInstance(getClientAppFrame());
				setupDialog.resetDefaultSetup(downloadserver,downloadtype,downloadftpport,downloadftpmode,downloadftpusername,downloadftppassword);
				setupDialog.pop();
			}catch (Exception exp) {
				exp.printStackTrace();
				log.error("启动参数配置界面错误",exp);
			}
		}
	}
	
	private void doSystemExit(){
		int result = JOptionPane.showConfirmDialog(this, ClientAppMessages
				.getString(ClientAppConstants.key_ui_message_confirm_exit), "",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			this.dispose();
			System.exit(0);
		}
	}
	
	public ClientApp getClientAppFrame(){
		return this;
	}
	
	public boolean isFTPConnectionOK(){
		
		return false;
	}
	
	class DownloadFinishHandler {
		public void doSomething(int result) throws Exception {
			try {
				if (result == 1) {
					hideClientUI();
					launchClient();
				}
			} catch (Exception e) {
				log.error("客户端更新完成，启动客户端出错",e);
				throw new Exception("客户端更新完成，启动客户端出错："+e.toString());
			} finally{
				if(result==1){
					System.exit(0);
				}
			}
		}
	}
	
	public static int mDownThreadStatus = 0; // 0=stop, 1=running 
	public static DownloadThread mainDownLoadThread = null;
	class DownloadThread extends Thread {
		
		List<String> updateList = null;
		public DownloadThread(){
		}
		
		public void setUpdateList(List<String> pUpdateList){
			this.updateList = pUpdateList;
		}
		
		private DownloadFinishHandler handler = null;
		public void setDownloadFinishHandler(DownloadFinishHandler phandler){
			this.handler = phandler;
		}
		
		@Override
		public synchronized void start() {
			if(mDownThreadStatus==0){
				mDownThreadStatus=1;
			}
			
			if(!this.isAlive()){
				super.start();
			}
		}
		
		public int getStatus(){
			return mDownThreadStatus;
		}
		
		@Override
		public void run() {
			while(true){
				try{
					Thread.sleep(1000);
					log.info("下载监控主线程，1s监控一次.");
				}catch (Exception e) {
					
				}
				if(mDownThreadStatus==1){
					AutoUpdateProcessMoniter moniter = null;
					try{
						deleteAll(new File(getFossGuiAppHomeTmpAbsPath()));
						
						(new File(getFossGuiAppHomeTmpAbsPath())).mkdirs();
						
						boolean docontinue = false;
						if(updateList!=null && !updateList.isEmpty()){
							IClientAppDownload util = ClientAppDownload.getAppDownload(downloadtype);
							util.resetConnectionConfig();
							if(ClientAppConstants.key_download_type_http.equals(downloadtype)){
								util.updateDownloadServer(httpDownloadserver,downloadftpport,null,downloadftpusername,downloadftppassword);
							}else{
								util.updateDownloadServer(downloadserver,downloadftpport,downloadftpmode,downloadftpusername,downloadftppassword);
							}
							try{
								setProgressLabel(ClientAppMessages.getString("ui.launch.msg.000005"));
								util.connect();
								int count = 0;
								for(String path : updateList){
									try{
										if(mDownThreadStatus==0){
											docontinue = true;
											break;
										}else {
											int pctg = (count++)*100/updateList.size();									
											String p_msg_2 = ClientAppMessages.getString("ui.launch.msg.000002")+"("+pctg+"%)&nbsp;&nbsp;&nbsp;"+path;
											log.info(p_msg_2);
											setProgressLabel(p_msg_2);
											setProgressPercent(pctg);									
											String local_path = getFossGuiAppHomeTmpAbsPath()+File.separator+path;
											util.download(util.getRemoteAppHomeRootPath(downloadpath)+path, local_path);
										}
									}catch(Exception ex){
										log.error(ex.getMessage(),ex);
										throw ex;
									}
								}
							}finally{
								util.disconnect();
							}
							//检查数据库fossdb.zip文件是否有更新
							File dbFile = new File(getFossGuiAppHomeTmpAbsPath()+File.separator+dbFileName);
							if(dbFile.exists()){
								//解压数据文件
								unZipIt(dbFile, getFossGuiAppHomeTmpAbsPath()+File.separator+dbOutputFloder);
							}
						}
						
						if(docontinue){
							setProgressLabel("");
							setProgressPercent(0);
							continue;
						}
						
						String p_msg = ClientAppMessages.getString("ui.launch.msg.000003");
						moniter = new AutoUpdateProcessMoniter(p_msg);
						moniter.start();
						log.info(p_msg);
						setProgressLabel(p_msg);
						setProgressPercent(100);
						
						// copy bak file to apphome force overwrite
						File bakdir = new File(getFossGuiAppHomeTmpAbsPath());
						File targetdir = new File(getFossGuiAppHomeAbsPath());
						copyFile(bakdir,targetdir);
						deleteAll(bakdir);
						moniter.end();
						mDownThreadStatus = 0;
						if(this.handler!=null){
							handler.doSomething(1);
						}
					}catch(Exception e){
						String errstr = ClientAppMessages.getString("ui.error.msg.000002");
						log.error(errstr,e);
						ClientAppAlertUtil util = ClientAppAlertUtil.getInstance(getClientAppFrame());
						util.showError(errstr, ClientAppMessages.getString("ui.error.msg.000003"));
						setOlRecoverbtnEnable(true);
					}finally{
						if(moniter!=null) {
							moniter.end();
						}
					}	
				}
			}
		}
	}
	
	public void unZipIt(File zipFile, String outputFolder){
        byte[] buffer = new byte[1024];
        
        try{
            log.info("Extract starting.");
            //create output directory is not exists
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }
            //get the zip file content
            ZipInputStream zis = null;
            try{
	            zis= new ZipInputStream(new FileInputStream(zipFile));
	            //get the zipped file list entry
	            ZipEntry ze = zis.getNextEntry();
	            
	            while(ze!=null){
	                String fileName = ze.getName();
	                File newFile = new File(outputFolder + File.separator +fileName);
	                log.info("file unzip: "+ newFile.getAbsoluteFile());
	                
	
	            	if(ze.isDirectory()){
	            		newFile.mkdirs();
	            	}else{
	            		//create all non exists folders
	                    //else you will hit FileNotFoundException for compressed folder
	            		if(!newFile.getParentFile().exists()){
	            			newFile.getParentFile().mkdirs();
	            		}
	                    FileOutputStream fos =null;
	                    try{
	                    	fos = new FileOutputStream(newFile);
		                    int len;
		                    while((len = zis.read(buffer)) > 0){
		                        fos.write(buffer,0,len);
		                    }
		                    fos.flush();
	                    }finally{
	                    	if(fos!=null){
	                    		fos.close();
	                    	}
	                    }
	            	}
	               
	                ze = zis.getNextEntry();
	            }
	            
	           
            }finally{
            	if(zis!=null){
            		zis.closeEntry();
            		zis.close();
            	}
            }
            log.info("Extract completed.");
        }catch(IOException e){
        	log.error("解压缩文件出错",e);
        }
    }
	
	public String getFossGuiAppHomeAbsPath() throws Exception {
		return currWorkDir.getAbsolutePath()+File.separator+"appHome";
	}
	
	public String getFossGuiAppHomeTmpAbsPath() throws Exception {
		return currWorkDir.getAbsolutePath()+File.separator+"appHome_tmp";
	}
	
	public List<String> diffToIncrementList() throws Exception {
		
		AutoUpdateProcessMoniter moniter = new AutoUpdateProcessMoniter(ClientAppMessages.getString("ui.launch.msg.000006"));
		moniter.start();
		Map<String,String> remoteMap = null;
		try{
			remoteMap = loadRemoteAppFileMD5Hex();
		}catch(Exception e){
			log.error(e.getMessage(),e);
			return new ArrayList<String>();
			//下载出现异常 直接返回一个空的list什么也不下载直接进入appHome启动
		}
		moniter.end();
		
		moniter = new AutoUpdateProcessMoniter(ClientAppMessages.getString("ui.launch.msg.000004"));
		moniter.start();
		Map<String,String> localMap = scanLocalAppFileToMD5Hex(remoteMap);
		Map<String, String> localDateMap  = scanLocalAppFileToDate(remoteMap);
		moniter.end();
		
		List<String> incrementList = new ArrayList<String>();
		Set<String> keyset = remoteMap.keySet();
		for(String key : keyset){
			if("F".equals(remoteMap.get(key))){
				incrementList.add(key);
			}else if(	
				(localMap.containsKey(key)||localDateMap.containsKey(key) )
				&& remoteMap.get(key)!=null
				){//远程和本地都有文件
				String remoteKey =(String) remoteMap.get(key);
				if("N".equals(remoteKey)){
					continue;
				}else if (remoteKey.startsWith("D")  &&   !"NA".equals(localDateMap.get(key)) ){//by date
					String remoteTime = remoteKey.substring(1);
					String localTime = localDateMap.get(key);
					
					try{
						long remoteTimelong = Long.parseLong(remoteTime);
						long localTimelong =  Long.parseLong(localTime);
						
						if(localTimelong<=remoteTimelong){
							incrementList.add(key);
						}
							
					}catch(Exception e){
						log.error("parse date",e);
					}
					
				}else if(!"NA".equals(localMap.get(key))){//md5
					String rmd5 = remoteMap.get(key);
					String lmd5 = localMap.get(key);
					if(!rmd5.equals(lmd5)){
						incrementList.add(key);
					}
				}
			
			}else {
				incrementList.add(key);
			}
		}
		
		log.info("获取远程版本与本机  Gui差异程序包清单,差异文件："+incrementList.size()+"个");
		if(log.isDebugEnabled()){
			for(String f : incrementList){
				log.info("差异文件："+f);		
			}
		}		
		return incrementList;
	}
	
	private Map<String, String> loadRemoteAppFileMD5Hex() throws Exception {
		Map<String, String> m = new HashMap<String, String>();

		IClientAppDownload util = ClientAppDownload.getAppDownload(downloadtype);
		util.resetConnectionConfig();
		if(ClientAppConstants.key_download_type_http.equals(downloadtype)){
			util.updateDownloadServer(httpDownloadserver,downloadftpport,downloadftpmode,downloadftpusername,downloadftppassword);
		}else{
			util.updateDownloadServer(downloadserver,downloadftpport,downloadftpmode,downloadftpusername,downloadftppassword);
		}
		String local = System.getProperty("user.home") + File.separator + "update.properties";
		try{
			util.connect();
			util.download(util.getRemoteAppHomeRootPath(downloadpath)+"update.properties", local);
		}catch(Exception e){
			log.error("http下载 出现异常情况 自动切换为ftp下载模式", e);
			try {
				util.disconnect();
				downloadtype = ClientAppConstants.key_download_type_ftp;
				util = ClientAppDownload.getAppDownload(downloadtype);
				util.resetConnectionConfig();
				util.updateDownloadServer(downloadserver,downloadftpport,downloadftpmode,downloadftpusername,downloadftppassword);
				util.connect();
				util.download(util.getRemoteAppHomeRootPath(downloadpath)+"update.properties", local);
				
			} catch (Exception e2) {
				log.error("http和 ftp服务连接失败", e2);
				throw new RuntimeException("http和 ftp服务连接失败");
			}
			
		}finally{
			util.disconnect();
		}
		BufferedReader reader = new BufferedReader(new FileReader(local));
		String string = reader.readLine();
		while (string != null) {
			if (string.indexOf("=") != -1) {
				String key = string.split("=")[0];
				String value = string.split("=")[1];
				log.info("key="+key +" value="+value);
				m.put(key, value);
			}
			string = reader.readLine();
		}
		reader.close();
		log.info("查询服务端远程appHome目录，共有需要根据MD5下载文件："+m.size()+"个");
		return m;
	}
	
	
	private Map<String, String> scanLocalAppFileToDate(Map<String,String> remoteMap) throws Exception {
		Map<String, String> dateMap = new HashMap<String, String>();
		File apphome = new File(getFossGuiAppHomeAbsPath());
		if(apphome.exists()){
			_scanLocalAppFileToDate(apphome,dateMap,"",null ,remoteMap);
		}
		log.debug("扫描本机appHome目录，共有需要根据日期下载文件："+dateMap.size()+"个");
		return dateMap;
	}
	
	
	private void _scanLocalAppFileToDate(File pf,Map<String, String> dateMap,String parentfolder,String excludeItems,
			Map<String,String> remoteMap) throws Exception {
		File[] files = pf.listFiles();
		for(File file : files){
			String fname = file.getName();
			String key = parentfolder+File.separator+fname;
			
			if(parentfolder.length()==0){
				key = fname;
			}	
			
			if(key.startsWith("\\")){
				key = key.substring(1);
			}
			key = key.replace("\\",  "/");
			
			if(excludeItems!=null && excludeItems.indexOf(","+fname+",")!=-1){
				continue;
			}else if(file.isFile() && ( remoteMap.get(key)==null || !remoteMap.get(key).startsWith("D")  ) ){
				dateMap.put(key,"NA");
			}else {
				if(file.isFile()){
					long time = 0;
					boolean success = false;
					try{
						time = parseFileDate(file, time, "yyyy-MM-dd  HH:mm");
						success = true;
					}catch(Exception e){
						log.error(e.getMessage(), e);
					}
					if(!success){
						try{
							time = parseFileDate(file, time, "yyyy/MM/dd  HH:mm");	
						}catch(Exception e){
							log.error("parseDate format exception yyyy/MM/dd  HH:mm", e);
						}
					}
					dateMap.put(key,time+"");
				}else if(file.isDirectory()){
					_scanLocalAppFileToDate(file, dateMap, parentfolder+File.separator+fname ,excludeItems, remoteMap);
				}	
			}
		}
	}

	/**
	 * @param file
	 * @param time
	 * @return
	 */
	private long parseFileDate(File file, long time, String format) throws Exception {
		String value = parseDate(file);//2013-02-21  18:28
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(value);
		time = date.getTime();
		return time;
	}	

	
	private String parseDate(File file) {
		String dateStr = "";
		try{
			Process ls_proc = Runtime.getRuntime().exec(  
					"cmd.exe /c dir " + file.getAbsolutePath() + " /tc");// 通过DOS获得的创建时间  
			
			InputStream is = ls_proc.getInputStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(is));  
			String str;  
			int i = 0;  
			while ((str = br.readLine()) != null) {  
				i++;  
				if (i == 6) {  
					dateStr = str.substring(0, 17);
					log.warn( "str" + str);
					log.warn( "dateStr" + dateStr);
					log.debug("Create time:" + str.substring(0, 17));
					
				}  
			}  
		}catch(Exception e){
			log.error("parseDate exception", e);
		}
		return dateStr;
		
	}

	private Map<String, String> scanLocalAppFileToMD5Hex(Map<String,String> remoteMap) throws Exception {
		Map<String, String> md5HexMap = new HashMap<String, String>();
		File apphome = new File(getFossGuiAppHomeAbsPath());
		if(apphome.exists()){
			_scanLocalAppFileToMD5Hex(apphome,md5HexMap,"",null,remoteMap);
		}
		log.debug("扫描本机appHome目录，共文件："+md5HexMap.size()+"个");
		return md5HexMap;
	}
	
	private void _scanLocalAppFileToMD5Hex(File pf,Map<String, String> md5HexMap,String parentfolder,String excludeItems,Map<String,String> remoteMap) throws Exception {
		File[] files = pf.listFiles();
		for(File file : files){
			String fname = file.getName();
			String key = parentfolder+File.separator+fname;
			if(parentfolder.length()==0){
				key = fname;
			}
			
			if(key.startsWith("\\")){
				key = key.substring(1);
			}
			key = key.replace("\\",  "/");
			
			if(excludeItems!=null && excludeItems.indexOf(","+fname+",")!=-1){
				continue;
			}else if(file.isFile() && (remoteMap.get(key)==null || (remoteMap.get(key)).startsWith("D")
				||"F".equals(remoteMap.get(key))||"N".equals(remoteMap.get(key)))
			){ //这些文件不用扫描md5
				md5HexMap.put(key,"NA");
			}else {
				if(file.isFile()){
				
					String value = parseMd5Hex(new FileInputStream(file));
					
					md5HexMap.put(key,value);
				}else if(file.isDirectory()){
					_scanLocalAppFileToMD5Hex(file, md5HexMap, parentfolder+File.separator+fname ,excludeItems, remoteMap);
				}	
			}
		}
	}	
	
	private String parseMd5Hex(FileInputStream fin ) throws IOException {
		Md5 md5 = new Md5 (fin);
		byte b[]= md5.getDigest();
		return md5.parseToValue(b);
	}
	
	class AutoUpdateProcessMoniter extends Thread {
		
		String msg = "";
		public AutoUpdateProcessMoniter(String msg){
			this.msg = msg;
		}
		
		private boolean end = false;
		public void end(){
			this.end = true;
			setProgressLabel("");
		}
		@Override
		public void run() {
			try{
				int points = 5;
				while(true){
					String strpoints = "";
					int i=0;
					while(i<6-points){
						strpoints+="&nbsp;●";
						i++;
					}
					points++;
					if(points==6){
						points = 1;
					}
					setProgressLabel(this.msg+strpoints);
					sleep(500);
					if(this.end){
						break;
					}
				}
			}catch(Exception e){}
		}
	}
	
	public void forceToAutoUpdate() throws Exception {
		try{
			log.info("自动运行，强制检查 Gui最新版本");	
			setOlRecoverbtnEnable(false);
			List<String> incrementList = diffToIncrementList();
			setProgressLabel("");
			updateFossGuiAppHome(incrementList);
		}catch(Exception e){
			throw e;
		}finally{
			setOlRecoverbtnEnable(true);
		}
		
	}
	
	public void updateFossGuiAppHome(List<String> updateList) throws Exception {
		if(mainDownLoadThread==null){
			mainDownLoadThread = new DownloadThread();
		}
		
		Collections.sort(updateList);
		mainDownLoadThread.setUpdateList(updateList);
		mainDownLoadThread.setDownloadFinishHandler(new DownloadFinishHandler());
		mainDownLoadThread.start();
	}
	
	public boolean backupFossGui(){
		// backup foss gui for recover
		
		return true;
	}
	
	public void launchClient() throws Exception {
		String path = currWorkDir.getAbsolutePath() + File.separator + "appHome" + File.separator + "aili.exe";
		Runtime.getRuntime().exec("cmd /c " + path);
	}
	
	public boolean deleteAll(File file) {
		if (file != null && file.exists()) {
			if (file.isDirectory()) {
				// 如果是目录, 先删除其子目录
				File[] children = file.listFiles();
				if (children != null && children.length > 0){
					for (int i = 0; i < children.length; i++){
						deleteAll(children[i]); // 递归删除
					}
				}
			}
			if (!file.delete()) {
				return false; // 删除文件
			}
		}
		return true;

	}
	
	public void copyFile(File fromDir, File toDir) throws Exception {
		if(fromDir.isDirectory()){
			File[] files = fromDir.listFiles();
			for(File file : files){
				if(file.isDirectory()){
					copyFile(file,toDir);
				}else if(file.isFile()){
					copyFile(file.getAbsolutePath(), file.getAbsolutePath().replace("appHome_tmp", "appHome"));
				}
			}
		}
		else if(fromDir.isFile()){
			copyFile(fromDir.getAbsolutePath(), fromDir.getAbsolutePath().replace("appHome_tmp", "appHome"));
		}
	}
	
	public void copyFile(String fromFileName, String toFileName)
			throws IOException {
		String frmpath = fromFileName;
		String topath = toFileName;
		File fromFile = new File(frmpath);
		File toFile = new File(topath);

		if (!fromFile.exists())
			throw new IOException("FileCopy: " + "no such source file: "
					+ fromFileName);
		if (!fromFile.isFile())
			throw new IOException("FileCopy: " + "can't copy directory: "
					+ fromFileName);
		if (!fromFile.canRead())
			throw new IOException("FileCopy: " + "source file is unreadable: "
					+ fromFileName);

		if (toFile.isDirectory())
			toFile = new File(toFile, fromFile.getName());

		if (toFile.exists()) {
			if (!toFile.canWrite())
				throw new IOException("FileCopy: "
						+ "destination file is unwriteable: " + toFileName);
			toFile.delete();
		} else {
			File dir = toFile.getParentFile();
			if (!dir.exists())
				dir.mkdirs();
		}
		InputStream from = null;
		OutputStream to = null;
		try {
			from = new BufferedInputStream(new FileInputStream(fromFile), 8096);
			to = new BufferedOutputStream(new FileOutputStream(toFile), 8096);
			byte[] buffer = new byte[8096];
			int bytesRead = -1;

			while ((bytesRead = from.read(buffer)) != -1)
				to.write(buffer, 0, bytesRead); // write
		}catch(Exception e){
			log.error("copy file exception", e);
			
		} finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
					
				}
		}
	}
	
}