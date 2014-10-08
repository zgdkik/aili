package org.hbhk.maikkr.backend.server.service;

import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.shared.pojo.SiteInfo;
import org.hbhk.maikkr.user.server.service.ICommonService;

/**
 * SiteInfoManager
 * @author  何波
 *
 */
public interface ISiteInfoService  extends ICommonService<SiteInfo> {

	Pagination<SiteInfo> findSiteInfoListByQueryMapWithPage(Page page,Sort[] sorts, Map<String, Object> paraMap);
	
}
