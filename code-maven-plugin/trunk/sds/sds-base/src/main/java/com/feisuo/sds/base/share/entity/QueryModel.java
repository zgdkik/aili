package com.feisuo.sds.base.share.entity;

import java.io.Serializable;
import java.util.Map;

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
    
    
    private Map<String, Object> params;

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

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	

}
