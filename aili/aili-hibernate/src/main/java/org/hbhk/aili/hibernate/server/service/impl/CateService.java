package org.hbhk.aili.hibernate.server.service.impl;

import javax.annotation.Resource;

import org.hbhk.aili.hibernate.server.dao.impl.CateDao;
import org.hbhk.aili.hibernate.server.service.ICateService;
import org.hbhk.aili.hibernate.share.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CateService  implements ICateService {
	
	@Resource
	CateDao cateDao;

	@Override
	public void save(Category category) {
		
		cateDao.save(category);
	}

}
