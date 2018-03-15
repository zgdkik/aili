package org.hbhk.aili.route.jms.share;

import java.io.Serializable;
import java.util.List;

public class RouteInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5279052442277233778L;
	
	private String id;
	
	
	private String form;
	/**
	 * 使用的组件名称
	 */
	private String fromComponent;
	
	
	private String version;
	
	/**
	 * 接口编码
	 */
	private String  interfaceCode;
	
	private String  interfaceName;
	
	private String  interfaceDesc;
	
	/**
	 * 一对多
	 */
	private List<ToRouteInfo> toRoutes;
	
	
	private String dynamicRouter;
	
	private String beforeProcess;
	
	private String afterProcess;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getFromComponent() {
		return fromComponent;
	}

	public void setFromComponent(String fromComponent) {
		this.fromComponent = fromComponent;
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

	public List<ToRouteInfo> getToRoutes() {
		return toRoutes;
	}

	public void setToRoutes(List<ToRouteInfo> toRoutes) {
		this.toRoutes = toRoutes;
	}

	public String getDynamicRouter() {
		return dynamicRouter;
	}

	public void setDynamicRouter(String dynamicRouter) {
		this.dynamicRouter = dynamicRouter;
	}

	public String getBeforeProcess() {
		return beforeProcess;
	}

	public void setBeforeProcess(String beforeProcess) {
		this.beforeProcess = beforeProcess;
	}

	public String getAfterProcess() {
		return afterProcess;
	}

	public void setAfterProcess(String afterProcess) {
		this.afterProcess = afterProcess;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	

}
