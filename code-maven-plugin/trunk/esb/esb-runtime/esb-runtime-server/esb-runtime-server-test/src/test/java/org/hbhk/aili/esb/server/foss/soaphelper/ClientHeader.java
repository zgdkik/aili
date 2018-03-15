package org.hbhk.aili.esb.server.foss.soaphelper;

/**
 * 用于webservice 客户端.
 * 
 * @author qiancheng
 */
public class ClientHeader {
	
	/** 版本号，必填字段. */
    protected String version="1.0";
    
    /** 交互模式,必填字段. */
    protected String exchangePattern="InOUT";
    
    /** 消息格式,必填字段. */
    protected String messageFormat="SOAP";
    
    /** 客户端系统名，必填字段. */
    protected String sourceSystem="FOSS";
    
    /** 服务编码，必填字段. */
    protected String esbServiceCode;
    
    /** 业务关键字，选填字段. */
    protected String businessId;
    
    /** 辅助业务关键字，选填字段. */
    protected String businessDesc1;
    
    /** 辅助业务关键字，选填字段. */
    protected String businessDesc2;
    
    /** 辅助业务关键字，选填字段. */
    protected String businessDesc3;
    
    /** 用户名,选填字段. */
    protected String userName;
    
    /** 密码，选填字段. */
    protected String password;
	
	/**
	 * Gets the 版本号，必填字段.
	 * 
	 * @return the 版本号，必填字段
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Gets the 业务关键字，选填字段.
	 * 
	 * @return the 业务关键字，选填字段
	 */
	public String getBusinessId() {
		return businessId;
	}
	
	/**
	 * Sets the 业务关键字，选填字段.
	 * 
	 * @param businessId
	 *            the new 业务关键字，选填字段
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	/**
	 * Gets the 辅助业务关键字，选填字段.
	 * 
	 * @return the 辅助业务关键字，选填字段
	 */
	public String getBusinessDesc1() {
		return businessDesc1;
	}

	/**
	 * Gets the 辅助业务关键字，选填字段.
	 * 
	 * @return the 辅助业务关键字，选填字段
	 */
	public String getBusinessDesc2() {
		return businessDesc2;
	}

	/**
	 * Gets the 辅助业务关键字，选填字段.
	 * 
	 * @return the 辅助业务关键字，选填字段
	 */
	public String getBusinessDesc3() {
		return businessDesc3;
	}

	/**
	 * Gets the 服务编码，必填字段.
	 * 
	 * @return the 服务编码，必填字段
	 */
	public String getEsbServiceCode() {
		return esbServiceCode;
	}
	
	/**
	 * Sets the 服务编码，必填字段.
	 * 
	 * @param esbServiceCode
	 *            the new 服务编码，必填字段
	 */
	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}

	/**
	 * Gets the 交互模式,必填字段.
	 * 
	 * @return the 交互模式,必填字段
	 */
	public String getExchangePattern() {
		return exchangePattern;
	}
	
	/**
	 * Gets the 消息格式,必填字段.
	 * 
	 * @return the 消息格式,必填字段
	 */
	public String getMessageFormat() {
		return messageFormat;
	}

	/**
	 * Gets the 用户名,选填字段.
	 * 
	 * @return the 用户名,选填字段
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the 用户名,选填字段.
	 * 
	 * @param userName
	 *            the new 用户名,选填字段
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the 密码，选填字段.
	 * 
	 * @return the 密码，选填字段
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the 密码，选填字段.
	 * 
	 * @param password
	 *            the new 密码，选填字段
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the 客户端系统名，必填字段.
	 * 
	 * @return the 客户端系统名，必填字段
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}
	
	/**
	 * Sets the 辅助业务关键字，选填字段.
	 * 
	 * @param businessDesc1
	 *            the new 辅助业务关键字，选填字段
	 */
	public void setBusinessDesc1(String businessDesc1) {
		this.businessDesc1 = businessDesc1;
	}
	
	/**
	 * Sets the 辅助业务关键字，选填字段.
	 * 
	 * @param businessDesc2
	 *            the new 辅助业务关键字，选填字段
	 */
	public void setBusinessDesc2(String businessDesc2) {
		this.businessDesc2 = businessDesc2;
	}
	
	/**
	 * Sets the 辅助业务关键字，选填字段.
	 * 
	 * @param businessDesc3
	 *            the new 辅助业务关键字，选填字段
	 */
	public void setBusinessDesc3(String businessDesc3) {
		this.businessDesc3 = businessDesc3;
	}
	
}
