package org.hbhk.aili.client.start.client;

public class ClientAppDownload {

	public static IClientAppDownload getAppDownload(String vtype) throws Exception {
		if(ClientAppConstants.key_download_type_http.equals(vtype)){
			return ClientAppHttpUtil.getInstance();
		}
		else if(ClientAppConstants.key_download_type_ftp.equals(vtype)){
			return ClientAppFTPUtil.getInstance();
		}
		throw new Exception("没有设定客户端程序下载方式");
	}
}