package org.hbhk.aili.client.core.widget.itservice.common;

import java.util.Date;

/**
 * 文件的基本信息
 *
 */
public class FileInfo {
	/**
	 * 全局唯一id
	 */
	private String uuid;
	
	/**
	 * 相对路径
	 */
	private String relativePath;
	
	/**
	 * 大小，单位是字节
	 */
	private long length;
	
	/**
	 * 最后修改时间
	 */
	private Date modifyDate;
	
	/**
	 * 源名称
	 */
	private String originName;
	
	/**
	 * 校验码
	 */
	private String checkSum;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	

}
