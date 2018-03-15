package org.hbhk.aili.client.core.widget.itservice.common;

import java.io.File;

public interface IFileSystemListener {
	/**
	 * 文件创建前
	 * onFilePreparedCreated
	 * @param file
	 * @return void
	 * @since JDK1.6
	 */
	void onFilePreparedCreated(File file);
	
	/**
	 * 文件创建后
	 * onFileCreated
	 * @param file
	 * @return void
	 * @since JDK1.6
	 */
	void onFileCreated(File file);
	
	/**
	 * 文件删除前
	 * onFilePreparedClean
	 * @param file
	 * @return void
	 * @since JDK1.6
	 */
	void onFilePreparedDeleted(File file);
	
	/**
	 * 文件删除后
	 * onFileClean
	 * @param file
	 * @return void
	 * @since JDK1.6
	 */
	void onFileDeleted(File file);
}
