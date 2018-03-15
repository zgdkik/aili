package org.hbhk.aili.base.shared.domain;

import java.io.Serializable;

public class QueryModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6869820989286466660L;
	
	private int page;
	
	  /**
     * 分页最大记录数
     */
    protected int limit;
    
    /**
     * 分页开始记录数
     */
    protected int start;
    
    /**
     * 总记录数
     */
    protected Long totalCount;
    

    private String sort;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
    
	

}
