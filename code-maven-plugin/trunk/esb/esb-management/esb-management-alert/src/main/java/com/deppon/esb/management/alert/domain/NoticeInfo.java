package com.deppon.esb.management.alert.domain;

import java.util.Date;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class NoticeInfo.
 * 
 * @Description 预警通知信息
 * @author HuangHua
 * @date 2012-6-6下午3:31:52
 */
public class NoticeInfo extends BaseEntity {
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -4365879841688942256L;
	
	/** 常量定义 NOTICE_TYPE_PERFORMANCE. */
	public static final int NOTICE_TYPE_PERFORMANCE = 0;
	
	/** 常量定义 NOTICE_TYPE_EXCEPTION. */
	public static final int NOTICE_TYPE_EXCEPTION = 1;
	
	/** 常量定义 NOTIC_TYPE_QUEUE. */
	public static final int NOTIC_TYPE_QUEUE = 2;
	
	/** 常量定义 NOTIC_TYPE_QUEUE. */
	public static final int NOTIC_TYPE_STATUS = 3;
	
	/** 服务编码. */
	private String svcCode;
	
	/** 统计时间. */
	private Date statisticsTime;
	
	/** 发送时间. */
	private Date sendTime;
	
	/** 内容. */
	private String content;
	
	/** 类型. */
	private int type;

	/**
	 * 获取 服务编码.
	 * 
	 * @return the 服务编码
	 */
	public String getSvcCode() {
		return svcCode;
	}

	/**
	 * 设置 服务编码.
	 * 
	 * @param svcCode
	 *            the new 服务编码
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

	/**
	 * 获取 统计时间.
	 * 
	 * @return the 统计时间
	 */
	public Date getStatisticsTime() {
		return statisticsTime;
	}

	/**
	 * 设置 统计时间.
	 * 
	 * @param statisticsTime
	 *            the new 统计时间
	 */
	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

	/**
	 * 获取 发送时间.
	 * 
	 * @return the 发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 设置 发送时间.
	 * 
	 * @param sendTime
	 *            the new 发送时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 获取 内容.
	 * 
	 * @return the 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置 内容.
	 * 
	 * @param content
	 *            the new 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取 类型.
	 * 
	 * @return the 类型
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置 类型.
	 * 
	 * @param type
	 *           统计类型：0：性能，1：异常，2：队列, 3:状态
	 */
	public void setType(int type) {
		this.type = type;
	}

}
