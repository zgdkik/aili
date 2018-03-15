package org.mybatis.spring.support;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 520741651051423364L;
	private List<T> list;
	private int count;
	private int totalPages;
	private int pageNum;
	private int pageSize;
	private String sortStr;

	public Pagination() {
	}

	public Pagination(List<T> list, int count) {
		this.list = list;
		this.count = count;
	}

	public Pagination(List<T> list, int count, int totalPages, int start,
			int size) {
		this.list = list;
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


	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
