package org.hbhk.aili.client.start.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.hbhk.aili.client.start.util.ClassPathResourceUtil;

public class GuiFTPServer {

	private static GuiFTPServer instance;
	FtpServer server;
	
	private GuiFTPServer() throws Exception{ 
		
		initFtpServerConfig(); 
		
	}

	public static GuiFTPServer getInstance() throws Exception{
		if(instance==null){
			instance = new GuiFTPServer();
		}
		return instance;
	}
	
	private static final int DEFAULT_FTP_PORT = 2121;
	public void initFtpServerConfig() throws Exception {
		ClassPathResourceUtil util = new ClassPathResourceUtil();
		String prefix ="org/hbhk/aili/client/start/webstart/ftp/";
    	InputStream in = util.getInputStream(prefix+"server.properties");
    	
    	Properties p = new Properties();
    	p.load(in);
    	
    	String ftpKeystoreFile = (String)p.get("ftp.keystore.file");//ftpserver.jks
    	String ftpKeystoreFilePassword = (String)p.get("ftp.keystore.file.password"); //password
    	
    	String ftpListenerType= (String)p.get("ftp.listener.type"); //default 
    	int ftpListenerPort = DEFAULT_FTP_PORT;
		try {
			ftpListenerPort = Integer.parseInt((String) p.get("ftp.listener.port"));
		} catch (Exception e) {
			ftpListenerPort = DEFAULT_FTP_PORT;
		}
    	String ftpUserManagerFile=(String)p.get("ftp.user.manager.file"); 
    	
    	FtpServerFactory serverFactory = new FtpServerFactory();
    	ListenerFactory factory = new ListenerFactory();
    	factory.setPort(ftpListenerPort);

    	SslConfigurationFactory ssl = new SslConfigurationFactory();
    	
    	InputStream keystroein = util.getInputStream(prefix+ftpKeystoreFile);
    	ssl.setKeystoreFile(getTmpConfigFile(ftpKeystoreFile,keystroein));
    	ssl.setKeystorePassword(ftpKeystoreFilePassword);
    	factory.setSslConfiguration(ssl.createSslConfiguration());
    	
    	serverFactory.addListener(ftpListenerType, factory.createListener());
    	PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
    	InputStream usermanagein = util.getInputStream(prefix+ftpUserManagerFile);
    	userManagerFactory.setFile(getTmpConfigFile(ftpUserManagerFile,usermanagein) );
    	serverFactory.setUserManager(userManagerFactory.createUserManager());

    	server = serverFactory.createServer();
	}
	
	private static final int BUFFER_SIZE = 1024;
	private File getTmpConfigFile (String fname,InputStream in) throws Exception {
		File f = new File(fname);
		if(f.exists()){
			f.delete();
		}
		
		f = new File(fname);
    	FileOutputStream fo = new FileOutputStream(f);
    	byte[] buffer = new byte[BUFFER_SIZE];
    	int pos = 0;
    	while((pos=in.read(buffer))!=-1){
    		fo.write(buffer, 0, pos);
    	}
    	fo.close();
    	return f;
	}
	
	public void start() throws Exception {
		// start the server
    	if(server!=null){
    		server.start();
    	}
	}
	
	public void stop(){
		if(server!=null){
			server.stop();
		}
	}
	
	public void suspend(){
		if(server!=null){
			server.suspend();
		}
	}
	
	public boolean isStopped(){
		if(server!=null){
			return server.isStopped();
		}
		else {
			return false;
		}
	}
	
	public boolean isSuspended(){
		if(server!=null){
			return server.isSuspended();
		}
		else {
			return false;
		}
	}
}