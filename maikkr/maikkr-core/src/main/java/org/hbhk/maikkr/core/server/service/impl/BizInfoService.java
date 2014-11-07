package org.hbhk.maikkr.core.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.core.server.dao.IBizInfoDao;
import org.hbhk.maikkr.core.server.service.IBizInfoService;
import org.hbhk.maikkr.core.shared.pojo.BizInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * BizInfoManager
 * @author  何波
 *
 */
@Transactional
public class BizInfoService implements IBizInfoService {

	@Autowired
	private IBizInfoDao bizInfoDao;


	/**
	 * 分页获取有效的BizInfo列表
	 * 强制加上status =1
	 * @param start
	 * @param size
	 * @param paraMap
	 * @param sorts 
	 * @return
	 */
	
	public Pagination<BizInfo> findEffectBizInfoListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> paraMap){
		return bizInfoDao.findEffectBizInfoListByQueryMapWithPage(page,sorts,paraMap);
	}


	public BizInfo save(BizInfo model) {
		model.setId(UUIDUitl.getUuid());
		model.setCreateTime(new Date());
		model.setCreatUser("admin");
		return bizInfoDao.save(model);
	}


	public BizInfo update(BizInfo model) {
		model.setUpdateTime(new Date());
		model.setUpdateUser("admin");
		return bizInfoDao.update(model);
	}


	public BizInfo getOne(BizInfo model) {
		return bizInfoDao.getOne(model);
	}


	public List<BizInfo> get(BizInfo model) {
		return bizInfoDao.get(model);
	}


	public List<BizInfo> get(BizInfo model, Page page) {
		return bizInfoDao.get(model,page);
	};
	
	
}
