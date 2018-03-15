package com.yimidida.ymdp.dao.server.support;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 520741651051423364L;
	private List<T> records;
	private int totalRecord;
	private int totalPage;
	private int currentPage;
	private int pageSize;
	private String sortStr;

	public Pagination() {
	}

	public Pagination(List<T> records, int totalRecord) {
		this.records = records;
		this.totalRecord = totalRecord;
	}

	public Pagination(List<T> records, int totalRecord, int totalPages, int start,
			int size) {
		this.records = records;
		this.totalRecord = totalRecord;
		this.totalPage = totalPages;
		this.currentPage = start;
		this.pageSize = size;
	}

	
	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortStr() {
		return sortStr;
	}

	public void setSortStr(String sortStr) {
		this.sortStr = sortStr;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	
}
