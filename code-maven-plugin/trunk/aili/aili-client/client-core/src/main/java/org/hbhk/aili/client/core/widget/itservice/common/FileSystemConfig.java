package org.hbhk.aili.client.core.widget.itservice.common;

import org.apache.commons.lang.StringUtils;

/**
 * 配置的文件系统相关静态信息，例如根目录，文件数目限制，文件大小限制，文件总大小限制
 * 
 */
public class FileSystemConfig {

	/**
	 * 根目录路径
	 */
	private String rootDir = "/tmp/dpap/";

	/**
	 * 单个文件的最大长度, 默认5MB
	 */
	private long maxFileSize = 5 * 1024 * 1024L;

	/**
	 * 单目录下的最大文件数目，默认200
	 */
	private int maxFolderFiles = 200;

	/**
	 * 根目录下总的最大文件数目 1,000,000
	 */
	private int maxTotalFiles = 1000000;

	/**
	 * 根目录下总的最大容量,默认1T
	 */
	private long maxTotalSize = 1024 * 1024 * 1024 * 1024L;
	
	
	/**
	 * 文件系统扫描线程的间隔时间，单位s.默认3600
	 * @return
	 */
	private int scanInterval = 3600;
	
	/**
	 * 在rootDir下添加日期文件夹,按照日期归档
	 */
	private boolean archiveByDate = false;
	
	public int getScanInterval() {
		return scanInterval;
	}

	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	}

	public String getRootDir() {
	    if(StringUtils.isBlank(rootDir)) { 
	    	return rootDir;
	    }
        StringBuilder sb = new StringBuilder(8);
        if(!rootDir.endsWith("/")) {
            sb.append(rootDir).append("/");
        } else {
            sb.append(rootDir);
        }
		return sb.toString();
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public int getMaxFolderFiles() {
		return maxFolderFiles;
	}

	public void setMaxFolderFiles(int maxFolderFiles) {
		this.maxFolderFiles = maxFolderFiles;
	}

	public int getMaxTotalFiles() {
		return maxTotalFiles;
	}

	public void setMaxTotalFiles(int maxTotalFiles) {
		this.maxTotalFiles = maxTotalFiles;
	}

	public long getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public long getMaxTotalSize() {
		return maxTotalSize;
	}

	public void setMaxTotalSize(long maxTotalSize) {
		this.maxTotalSize = maxTotalSize;
	}

    public boolean getArchiveByDate() {
        return archiveByDate;
    }

    public void setArchiveByDate(boolean archiveByDate) {
        this.archiveByDate = archiveByDate;
    }

}
