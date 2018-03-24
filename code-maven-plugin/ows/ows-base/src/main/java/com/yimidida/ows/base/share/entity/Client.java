package com.yimidida.ows.base.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

@Table("t_auth_client")
public class Client extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4976585605175948773L;

	@Column("app_key")
	private String appKey;
	
	@Column("app_name")
	private String appName;
	
	@Column("app_secret")
	private String appSecret;
	
	@Column("version")
	private Integer version;
	
	@Column("version_name")
	private String versionName;
	
	@Column("download_url")
	private String downloadUrl;
	
	/**
	 * 是否强制更新  0 不是   1 是
	 */
	@Column("force_update")
	private Integer forceUpdate;
	
	@Column("release_time")
	private Date releaseTime;
	
	@Column("file_path")
	private String filePath;
	
	
	

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Integer getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Integer forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	

}
