package org.hbhk.aili.base.shared.domain;

import java.io.Serializable;

/**
 * 分页查询的参数
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:李光辉,date:2013-5-6 上午10:00:35,content:创建 </p>
 * @author 李光辉
 * @date 2013-5-6 上午10:00:35
 * @since
 * @version
 */
public class PaginationParam<T> implements Serializable {

	private static final long serialVersionUID = 2845751055692010798L;
	
	/** 分页最大记录数 */
    private int limit;
    
    /** 分页开始记录数 */
    private int start;
    
    /** 查询条件 */
    private T param;

	public PaginationParam() {
	}

	public PaginationParam(int limit, int start, T param) {
		this.limit = limit;
		this.start = start;
		this.param = param;
	}

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}

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

}
