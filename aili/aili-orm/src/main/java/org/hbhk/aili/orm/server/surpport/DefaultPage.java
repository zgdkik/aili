package org.hbhk.aili.orm.server.surpport;

import org.apache.ibatis.session.RowBounds;

/**
 * <pre>
 * 描述：数据库分页类
 * </pre>
 */
public class DefaultPage extends RowBounds {
	/**
	 * 最多显示页码数
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	// 页码大小
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	// 总记录数
	private Integer totalItems = 0;
	// 当前页码
	private Integer pageNo = 1;

	private boolean isShowTotal = true;

	public DefaultPage() {

	}

	public DefaultPage(Integer pageNo, Integer pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	@Override
	public int getOffset() {
		return getStartIndex();
	}

	@Override
	public int getLimit() {
		return getPageSize();
	}

	public Integer getStartIndex() {
		return (pageNo - 1) * pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public boolean isShowTotal() {
		return isShowTotal;
	}

	public void setShowTotal(boolean isShowTotal) {
		this.isShowTotal = isShowTotal;
	}

}
