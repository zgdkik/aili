package org.hbhk.aili.route.http.bean;

import java.io.Serializable;

public class HttpInterfaceInfo implements Serializable{

	private static final long serialVersionUID = -3583181410103106875L;
	private String interfaceCode;

	private String interfaceName;

	private String url;
	
	private String version;
	
	
	public String getInterfaceCode() {
		return interfaceCode;
	}

	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	

}
