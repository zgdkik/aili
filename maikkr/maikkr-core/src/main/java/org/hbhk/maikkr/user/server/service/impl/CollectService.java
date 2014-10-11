package org.hbhk.maikkr.user.server.service.impl;

import java.util.Date;
import java.util.List;

import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.share.util.UUIDUitl;
import org.hbhk.maikkr.user.server.dao.IBlogDao;
import org.hbhk.maikkr.user.server.dao.ICollectDao;
import org.hbhk.maikkr.user.server.service.ICollectService;
import org.hbhk.maikkr.user.share.pojo.BlogInfo;
import org.hbhk.maikkr.user.share.pojo.CollectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectService implements ICollectService {

	@Autowired
	private ICollectDao collectDao;
	@Autowired
	private IBlogDao blogDao;

	public CollectInfo save(CollectInfo model) {
		if (collectDao.getOne(model) != null) {
			throw new BusinessException("你已经收藏!");
		}
		model.setId(UUIDUitl.getUuid());
		model.setCreateTime(new Date());
		model.setCreatUser(UserContext.getCurrentContext().getCurrentUserName());
		BlogInfo blog = new BlogInfo();
		blog.setId(model.getBlogId());
		BlogInfo b = blogDao.getOne(blog);
		blog.setBlogCollect(b.getBlogCollect() + 1);
		blogDao.update(blog);
		return collectDao.save(model);
	}

	public CollectInfo update(CollectInfo model) {
		CollectInfo c = getOne(model);
		model.setId(c.getId());
		model.setUpdateTime(new Date());
		model.setBlogId(null);
		model.setStatus(2);
		return collectDao.update(model);
	}

	public CollectInfo getOne(CollectInfo model) {
		return collectDao.getOne(model);
	}

	public List<CollectInfo> get(CollectInfo model) {
		return collectDao.get(model);
	}

	public List<CollectInfo> get(CollectInfo model, Page page) {
		return collectDao.get(model, page);
	}

}
