package org.hbhk.aili.client.core.widget.print.entity;

import java.io.File;
import java.io.InputStream;

/**
 * Description:报表模板或清单模板信息类
 */
public class BillTemplateInfo {
	private Long id;
	private String title;
	private File contentFile;
	private InputStream contentStream;
	private String sort;

	/**
	 * 
	 * <p>Title: BillTemplateInfo</p>
	 * <p>Description: 构造函数</p>
	 */
	public BillTemplateInfo(){
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the contentFile
	 */
	public File getContentFile() {
		return contentFile;
	}

	/**
	 * @param contentFile the contentFile to set
	 */
	public void setContentFile(File contentFile) {
		this.contentFile = contentFile;
	}

	/**
	 * @return the contentStream
	 */
	public InputStream getContentStream() {
		return contentStream;
	}

	/**
	 * @param contentStream the contentStream to set
	 */
	public void setContentStream(InputStream contentStream) {
		this.contentStream = contentStream;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
}