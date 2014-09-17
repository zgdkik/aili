package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.ICareDao;
import org.hbhk.maikkr.user.server.service.ICareService;
import org.hbhk.maikkr.user.share.pojo.CareInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareService implements ICareService {

	@Autowired
	private ICareDao careDao;

	public CareInfo save(CareInfo model) {
		if (careDao.getOne(model) != null) {
			throw new BusinessException("你已经关注了此用户");
		}
		model.setCreatUser(UserContext.getCurrentContext().getCurrentUserName());
		model.setCreateTime(new Date());
		model.setId(UUIDUitl.getUuid());

		return careDao.save(model);
	}

	public CareInfo update(CareInfo model) {
		model.setUpdateTime(new Date());
		String user = UserContext.getCurrentContext().getCurrentUserName();
		model.setUpdateUser(user);
		return careDao.update(model);
	}

	public CareInfo getOne(CareInfo model) {
		return careDao.getOne(model);
	}

	public List<CareInfo> get(CareInfo model) {
		List<CareInfo> careInfos = careDao.get(model);
		return careInfos;
	}

	public List<CareInfo> get(CareInfo model, Page page) {
		return careDao.get(model, page);
	}

	public int myCareCount() {
		CareInfo model = new CareInfo();
		String user = UserContext.getCurrentContext().getCurrentUserName();
		model.setCreatUser(user);
		List<CareInfo> careInfos = careDao.get(model);
		if (CollectionUtils.isNotEmpty(careInfos)) {
			return careInfos.size();
		}
		return 0;
	}

}
