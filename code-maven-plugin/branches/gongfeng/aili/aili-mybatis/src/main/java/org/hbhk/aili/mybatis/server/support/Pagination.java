package org.hbhk.aili.mybatis.server.support;

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
	private List<T> dataList;
	private int count;
	private int totalPages;
	private int pageNum;
	private int pageSize;
	private String sortStr;

	public Pagination() {
	}

	public Pagination(List<T> dataList, int count) {
		this.dataList = dataList;
		this.count = count;
	}

	public Pagination(List<T> dataList, int count, int totalPages, int start,
			int size) {
		this.dataList = dataList;
		this.count = count;
		this.totalPages = totalPages;
		this.pageNum = start;
		this.pageSize = size;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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

	

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
