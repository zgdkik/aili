package org.hbhk.aili.client.start.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientAppHttpUtil implements IClientAppDownload {
	
	private static final Log logger = LogFactory.getLog(ClientAppHttpUtil.class);
	private static ClientAppHttpUtil instance;
	private ClientAppHttpUtil() {
		
	}
	public static ClientAppHttpUtil getInstance(){
		if(instance==null){
			instance = new ClientAppHttpUtil();
		}
		return instance;
	}
	
	private static String downloadserver = "";
	String port;
	@Override
	public void updateDownloadServer(String serverurl,String port, String ftpmode, String ftpusername, String ftppassword){
		downloadserver = serverurl;
		this.port = port;
	}

    public void _download(String remote, String plocal) throws Exception {
        retry++;
        download(remote,plocal);
    }

	public static int retry = 0;
	@Override
	public void download(String remote, String plocal) throws Exception {
		
		if(downloadserver==null || "".equals(downloadserver ) || "null".equals(downloadserver)){
			throw new Exception("下载服务器地址错误，不能进行下载");
		}
		
		String vurl = "http://"+downloadserver+":"+port+"/"+remote;
		try {
			URL url = new URL(vurl);
			// 打开连接
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			logger.info("http 下载文件"+vurl);
			//httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
			httpConnection.setConnectTimeout(0);
			httpConnection.setReadTimeout(0);
			
			InputStream input = httpConnection.getInputStream();
			byte[] b = new byte[1024];
			// 读取网络文件,写入指定的文件中
			int pos = 0;
			
			File destFile = new File(plocal);
			File dir = destFile.getParentFile();
			if (!dir.exists())
				dir.mkdirs();
			
			FileOutputStream out = new FileOutputStream(plocal);
			while ((pos = input.read(b)) != -1) {
				out.write(b, 0, pos);
			}
			out.flush();
			out.close();
			httpConnection.disconnect();
            retry = 0;
		} catch (Exception e) {
			logger.info("Download file [" + vurl + "] faild",e);
			if(e instanceof ConnectException && retry<2){
				String msg = e.getMessage();
				if(msg.indexOf("Connection timed out")!=-1){
                    _download(remote, plocal);
				}
			}
			else {
				retry = 0;
				throw new Exception("retry Download file "+retry+" times [" + vurl + "] faild", e);
			}
		}

	}
	
	@Override
	public boolean connect() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void resetConnectionConfig() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void disconnect() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		try{
			ClientAppHttpUtil util = ClientAppHttpUtil.getInstance();
			util.updateDownloadServer(null, null, null, null, null);
			util.download("test.txt","d:\\foss-exe4j.zip");
		}catch (Exception e) {
			
		}
	}
	
	@Override
	public String getRemoteAppHomeRootPath(String inputPath) {
		if(inputPath==null){
			return "appHome/";	
		}
		else {
			return inputPath;
		}
	}
}