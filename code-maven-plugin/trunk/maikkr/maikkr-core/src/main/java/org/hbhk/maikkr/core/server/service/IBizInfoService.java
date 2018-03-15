package org.hbhk.maikkr.core.server.service;

import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.core.shared.pojo.BizInfo;
import org.hbhk.maikkr.user.server.service.ICommonService;

/**
 * BizInfoManager
 * 
 * @author 何波
 * 
 */
public interface IBizInfoService extends ICommonService<BizInfo> {

	/**
	 * 分页获取有效的BizInfo列表 强制加上status =1
	 * 
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts
	 * @return
	 */

	Pagination<BizInfo> findEffectBizInfoListByQueryMapWithPage(Page page,
			Sort[] sorts, Map<String, Object> paraMap);

}
