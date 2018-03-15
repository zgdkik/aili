package org.hbhk.maikkr.core.server.dao;

import java.util.Map;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.core.shared.pojo.SiteInfo;

/**
 * SiteInfoDao
 * @author  何波
 *
 */
public interface ISiteInfoDao extends GenericEntityDao<SiteInfo,String>{

	/**
	 * 分页获取有效的SiteInfo列表
	 * 强制加上status =1
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	@NativeQuery(model = SiteInfo.class, value = "findSiteInfoListByQueryMapWithPage")
	Pagination<SiteInfo> findSiteInfoListByQueryMapWithPage(Page page,Sort[] sorts,@QueryParam Map<String, Object> paraMap);
	
	
}
