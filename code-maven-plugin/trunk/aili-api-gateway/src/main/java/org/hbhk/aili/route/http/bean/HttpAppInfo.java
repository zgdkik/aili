package org.hbhk.aili.route.http.bean;

import java.io.Serializable;
import java.util.List;

public class HttpAppInfo implements Serializable {

	private static final long serialVersionUID = 1878396409479801850L;

	private String appCode;

	private String appName;
	
	private  String ip;
	
	private  Integer port;
	
	private String contextPath;
	
	private String interfaceCode;

	private String interfaceName;

	private String url;
	
	private String version;
	

	private List<HttpInterfaceInfo> interfaces;

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<HttpInterfaceInfo> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<HttpInterfaceInfo> interfaces) {
		this.interfaces = interfaces;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

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
