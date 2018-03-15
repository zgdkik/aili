package com.yimidida.ymdp.dao.server.support;

import java.io.Serializable;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class Page implements Serializable {

	private static final long serialVersionUID = -4902532758803288395L;

	private int currentPage = 1;
	private int pageSize = 10;

	

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return (currentPage - 1)*pageSize;
	}

}
