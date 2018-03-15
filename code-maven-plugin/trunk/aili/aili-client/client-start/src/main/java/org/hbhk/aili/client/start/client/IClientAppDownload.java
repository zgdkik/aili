package org.hbhk.aili.client.start.client;

public interface IClientAppDownload {

	public void resetConnectionConfig() throws Exception;
	
	public void updateDownloadServer(String serverurl,String ftpport, String ftpmode, String ftpusername, String ftppassword ) throws Exception;
	
	public void download(String remote, String local) throws Exception;
	
	public boolean connect() throws Exception;
	
	public void disconnect() throws Exception;
	
	public String getRemoteAppHomeRootPath(String inputPath);
}