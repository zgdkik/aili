package com.yimidida.ows.common.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.share.entity.BizBaseEntity;
/**
 * 短信模板
 * @author IT-000036-zhangxingwang
 * @date 2016-4-11 17:04:01
 */
@Table("T_SMS_TEMPLATE")
public class SmsTemplateEntity extends BizBaseEntity {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -2114944327191117726L;
	//模板编码
	@Column("TEMP_CODE")
	private String tempCode;

	//模板名称
	@Column("TEMP_NAME")
	private String tempName;

	//内容
	@Column("TEMP_CONTENT")
	private String tempContent;
	
	//短信、邮件、其他
	@Column("SERVICE_TYPE")
	private String serviceTypeCode;
	
	//公司代码
	@Column("COMP_CODE")
	private String compCode;
	
	//开始日期
	@Column("BEGIN_TM")
	private Date beginTime;
	
	//结束日期
	@Column("END_TM")
	private Date endTime;

	/**
	 * @return the tempCode
	 */
	public String getTempCode() {
		return tempCode;
	}

	/**
	 * @param tempCode the tempCode to set
	 */
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}

	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}

	/**
	 * @param tempName the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	/**
	 * @return the tempContent
	 */
	public String getTempContent() {
		return tempContent;
	}

	/**
	 * @param tempContent the tempContent to set
	 */
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
