package org.hbhk.aili.esb.server.foss.sms;

import java.util.Date;

/**
 * 短信发送对象.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午7:15:24
 */
public class SmsInfo {
	//电话号码
	/** The mobile. */
	private String mobile;
	//内容
	/** The msg content. */
	private String msgContent;
	//发送部门
	/** The send dept. */
	private String sendDept;
	//发送人
	/** The sender. */
	private String sender;
	//业务类型
	/** The msg type. */
	private String msgType;
	//系统来源
	/** The msg source. */
	private String msgSource;
	//唯一标识
	/** The union id. */
	private String unionId;
	//单号
	/** The waybill no. */
	private String waybillNo;
	//服务类型(短信、语音、短信语音)
	/** The service type. */
	private String serviceType;
	//最晚发送时间
	/** The latest send time. */
	private Date latestSendTime;
	//发送时间
	/** The send time. */
	private Date sendTime;
	
	/**
	 * Gets the mobile.
	 * 
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * Sets the mobile.
	 * 
	 * @param mobile
	 *            the new mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * Gets the msg content.
	 * 
	 * @return the msg content
	 */
	public String getMsgContent() {
		return msgContent;
	}
	
	/**
	 * Sets the msg content.
	 * 
	 * @param msgContent
	 *            the new msg content
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	/**
	 * Gets the send dept.
	 * 
	 * @return the send dept
	 */
	public String getSendDept() {
		return sendDept;
	}
	
	/**
	 * Sets the send dept.
	 * 
	 * @param sendDept
	 *            the new send dept
	 */
	public void setSendDept(String sendDept) {
		this.sendDept = sendDept;
	}
	
	/**
	 * Gets the sender.
	 * 
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	
	/**
	 * Sets the sender.
	 * 
	 * @param sender
	 *            the new sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	/**
	 * Gets the msg type.
	 * 
	 * @return the msg type
	 */
	public String getMsgType() {
		return msgType;
	}
	
	/**
	 * Sets the msg type.
	 * 
	 * @param msgType
	 *            the new msg type
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	/**
	 * Gets the msg source.
	 * 
	 * @return the msg source
	 */
	public String getMsgSource() {
		return msgSource;
	}
	
	/**
	 * Sets the msg source.
	 * 
	 * @param msgSource
	 *            the new msg source
	 */
	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}
	
	/**
	 * Gets the union id.
	 * 
	 * @return the union id
	 */
	public String getUnionId() {
		return unionId;
	}
	
	/**
	 * Sets the union id.
	 * 
	 * @param unionId
	 *            the new union id
	 */
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
	/**
	 * Gets the waybill no.
	 * 
	 * @return the waybill no
	 */
	public String getWaybillNo() {
		return waybillNo;
	}
	
	/**
	 * Sets the waybill no.
	 * 
	 * @param waybillNo
	 *            the new waybill no
	 */
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
	
	/**
	 * Gets the service type.
	 * 
	 * @return the service type
	 */
	public String getServiceType() {
		return serviceType;
	}
	
	/**
	 * Sets the service type.
	 * 
	 * @param serviceType
	 *            the new service type
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	/**
	 * Gets the latest send time.
	 * 
	 * @return the latest send time
	 */
	public Date getLatestSendTime() {
		return latestSendTime;
	}
	
	/**
	 * Sets the latest send time.
	 * 
	 * @param latestSendTime
	 *            the new latest send time
	 */
	public void setLatestSendTime(Date latestSendTime) {
		this.latestSendTime = latestSendTime;
	}
	
	/**
	 * Gets the send time.
	 * 
	 * @return the send time
	 */
	public Date getSendTime() {
		return sendTime;
	}
	
	/**
	 * Sets the send time.
	 * 
	 * @param sendTime
	 *            the new send time
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	

}
