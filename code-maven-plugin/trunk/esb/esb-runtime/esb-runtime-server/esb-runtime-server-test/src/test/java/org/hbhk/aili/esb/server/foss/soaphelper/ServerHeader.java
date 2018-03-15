package org.hbhk.aili.esb.server.foss.soaphelper;

import java.io.Serializable;

/**
 * 用于webserice服务端.
 * 
 * @author qiancheng
 */
public class ServerHeader
    implements Serializable
{

    /** The Constant serialVersionUID. */
    private final static long serialVersionUID = 11082014L;
    
    /** The version. */
    protected String version;
    
    /** The business id. */
    protected String businessId;
    
    /** The business desc1. */
    protected String businessDesc1;
    
    /** The business desc2. */
    protected String businessDesc2;
    
    /** The business desc3. */
    protected String businessDesc3;
    
    /** The request id. */
    protected String requestId;
    
    /** The response id. */
    protected String responseId;
    
    /** The source system. */
    protected String sourceSystem;
    
    /** The target system. */
    protected String targetSystem;
    
    /** The esb service code. */
    protected String esbServiceCode;
    
    /** The back service code. */
    protected String backServiceCode;
    
    /** The message format. */
    protected String messageFormat;
    
    /** The exchange pattern. */
    protected String exchangePattern;
    
    /** The user name. */
    protected String userName;
    
    /** The password. */
    protected String password;
	
	/** The result code. */
	protected int resultCode;
    
    /**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
    public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
    
    /**
	 * Gets the value of the version property.
	 * 
	 * @return the version possible object is {@link String }
	 */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
	 * Gets the value of the businessId property.
	 * 
	 * @return the business id possible object is {@link String }
	 */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * Sets the value of the businessId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessId(String value) {
        this.businessId = value;
    }

    /**
	 * Gets the value of the businessDesc1 property.
	 * 
	 * @return the business desc1 possible object is {@link String }
	 */
    public String getBusinessDesc1() {
        return businessDesc1;
    }

    /**
     * Sets the value of the businessDesc1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc1(String value) {
        this.businessDesc1 = value;
    }

    /**
	 * Gets the value of the businessDesc2 property.
	 * 
	 * @return the business desc2 possible object is {@link String }
	 */
    public String getBusinessDesc2() {
        return businessDesc2;
    }

    /**
     * Sets the value of the businessDesc2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc2(String value) {
        this.businessDesc2 = value;
    }

    /**
	 * Gets the value of the businessDesc3 property.
	 * 
	 * @return the business desc3 possible object is {@link String }
	 */
    public String getBusinessDesc3() {
        return businessDesc3;
    }

    /**
     * Sets the value of the businessDesc3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessDesc3(String value) {
        this.businessDesc3 = value;
    }

    /**
	 * Gets the value of the requestId property.
	 * 
	 * @return the request id possible object is {@link String }
	 */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
	 * Gets the value of the responseId property.
	 * 
	 * @return the response id possible object is {@link String }
	 */
    public String getResponseId() {
        return responseId;
    }

    /**
     * Sets the value of the responseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseId(String value) {
        this.responseId = value;
    }

    /**
	 * Gets the value of the sourceSystem property.
	 * 
	 * @return the source system possible object is {@link String }
	 */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

    /**
	 * Gets the value of the targetSystem property.
	 * 
	 * @return the target system possible object is {@link String }
	 */
    public String getTargetSystem() {
        return targetSystem;
    }

    /**
     * Sets the value of the targetSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSystem(String value) {
        this.targetSystem = value;
    }



    /**
	 * Gets the esb service code.
	 * 
	 * @return the esb service code
	 */
    public String getEsbServiceCode() {
		return esbServiceCode;
	}

	/**
	 * Sets the esb service code.
	 * 
	 * @param esbServiceCode
	 *            the new esb service code
	 */
	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}

	/**
	 * Gets the value of the backServiceCode property.
	 * 
	 * @return the back service code possible object is {@link String }
	 */
    public String getBackServiceCode() {
        return backServiceCode;
    }

    /**
     * Sets the value of the backServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackServiceCode(String value) {
        this.backServiceCode = value;
    }

    /**
	 * Gets the value of the messageFormat property.
	 * 
	 * @return the message format possible object is {@link String }
	 */
    public String getMessageFormat() {
        return messageFormat;
    }

    /**
     * Sets the value of the messageFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageFormat(String value) {
        this.messageFormat = value;
    }

    /**
	 * Gets the value of the exchangePattern property.
	 * 
	 * @return the exchange pattern possible object is {@link Integer }
	 */

	public String getExchangePattern() {
		return exchangePattern;
	}

	/**
	 * Sets the exchange pattern.
	 * 
	 * @param exchangePattern
	 *            the new exchange pattern
	 */
	public void setExchangePattern(String exchangePattern) {
		this.exchangePattern = exchangePattern;
	}

	/**
	 * Gets the result code.
	 * 
	 * @return the result code
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * Sets the result code.
	 * 
	 * @param resultCode
	 *            the new result code
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}   
}
