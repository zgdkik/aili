package org.hbhk.aili.base.shared.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类，分装类数据集和总记录数
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:李光辉,date:2013-4-27 下午4:42:12,content: </p>
 * @author 李光辉
 * @date 2013-4-27 下午4:42:12
 * @since
 * @version
 */
public class PaginationSupport<T> implements Serializable {

	private static final long serialVersionUID = 5509745613729843870L;

	/** 总记录数 */
	private Long totalCount;
	
	/** 结果集 */
	private List<T> datas;

	public PaginationSupport() { }

	public PaginationSupport(Long totalCount, List<T> datas) {
		this.totalCount = totalCount;
		this.datas = datas;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
}
