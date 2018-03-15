package org.hbhk.aili.base.shared.selector;

import org.hbhk.aili.base.shared.domain.PaginationParam;
import org.hbhk.aili.base.shared.domain.PaginationSupport;

/**
 * 单选公共选择器
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:李光辉,date:2013-5-3 下午4:11:33,content:创建 </p>
 * @author 李光辉
 * @date 2013-5-3 下午4:11:33
 * @since
 * @version
 */
public interface SingleSelector<T, K> extends CommonSelector {
	
	/**
	 * 获取分页的数据集
	 * @author 李光辉
	 * @date 2013-5-3 下午4:15:17
	 * @param name 查询条件
	 * @return 分页后的数据集和总记录数
	 * @see
	 */
	PaginationSupport<T> selectPagedDatas(PaginationParam<K> pageparam);
	
}
