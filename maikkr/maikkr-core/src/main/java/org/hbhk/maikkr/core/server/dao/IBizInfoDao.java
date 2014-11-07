package org.hbhk.maikkr.core.server.dao;

import java.util.Map;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.core.shared.pojo.BizInfo;

/**
 * BizInfoDao
 * @author  何波
 *
 */
public interface IBizInfoDao extends GenericEntityDao<BizInfo,String>{
	/**
	 * 分页获取有效的BizInfo列表
	 * 强制加上status =1
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	@NativeQuery(model = BizInfo.class)
	Pagination<BizInfo> findEffectBizInfoListByQueryMapWithPage(Page page,Sort[] sorts,@QueryParam Map<String, Object> paraMap);
	
	
}
