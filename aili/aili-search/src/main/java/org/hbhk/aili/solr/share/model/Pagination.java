package org.hbhk.aili.solr.share.model;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 520741651051423364L;
	private List<T> datas;
	private int totalCount;
	private int totalPages;
	private int pageNum;
	private int pageSize;

	public Pagination() {
	}

	public Pagination(List<T> datas, int totalCount) {
		this.datas = datas;
		this.totalCount = totalCount;
	}

	public Pagination(List<T> datas, int totalCount, int totalPages, int start,
			int size) {
		this.datas = datas;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
		this.pageNum = start;
		this.pageSize = size;
	}

	public int getTotalPages() {
		return totalPages;
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


	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
}
