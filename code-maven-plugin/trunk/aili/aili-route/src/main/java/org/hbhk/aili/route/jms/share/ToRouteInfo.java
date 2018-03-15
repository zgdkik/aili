package org.hbhk.aili.route.jms.share;

import java.io.Serializable;

public class ToRouteInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5279052442277233778L;
	
	
	private String id;
	
	private String routeId;
	
	private String to;
	/**
	 * 使用的组件名称
	 */
	private String toComponent;

	/**
	 * 接口编码
	 */
	private String  interfaceCode;
	
	private String  interfaceName;
	
	private String  interfaceDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getToComponent() {
		return toComponent;
	}

	public void setToComponent(String toComponent) {
		this.toComponent = toComponent;
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

	public String getInterfaceDesc() {
		return interfaceDesc;
	}

	public void setInterfaceDesc(String interfaceDesc) {
		this.interfaceDesc = interfaceDesc;
	}


}
