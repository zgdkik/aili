package org.hbhk.maikkr.core.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.core.server.dao.ISiteInfoDao;
import org.hbhk.maikkr.core.server.service.ISiteInfoService;
import org.hbhk.maikkr.core.shared.pojo.SiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SiteInfoManager
 * @author  何波
 *
 */
@Transactional
@Service
public class SiteInfoService implements ISiteInfoService {

	@Autowired
	private ISiteInfoDao siteInfoDao;

	public SiteInfo save(SiteInfo model) {
		model.setId(UUIDUitl.getUuid());
		model.setCreatUser("admin");
		model.setCreateTime(new Date());
		return siteInfoDao.save(model);
	}

	public SiteInfo update(SiteInfo model) {
		model.setUpdateTime(new Date());
		model.setUpdateUser("admin");
		return siteInfoDao.update(model);
	}

	public SiteInfo getOne(SiteInfo model) {
		return siteInfoDao.getOne(model);
	}

	public List<SiteInfo> get(SiteInfo model) {
		return siteInfoDao.get(model);
	}

	public List<SiteInfo> get(SiteInfo model, Page page) {
		return siteInfoDao.get(model, page);
	}

	public Pagination<SiteInfo> findSiteInfoListByQueryMapWithPage(Page page,
			Sort[] sorts, Map<String, Object> paraMap) {
		return siteInfoDao.findSiteInfoListByQueryMapWithPage(page, sorts, paraMap);
	}

	
}
