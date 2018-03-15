package org.hbhk.aili.common.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.entity.Client;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.common.server.dao.IClientDao;
import org.hbhk.aili.common.server.service.IClientService;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService implements IClientService{
	
	@Autowired
	private IClientDao clientDao;

	@Override
	@Transactional
	public int insert(Client t) {
		if (StringUtils.isEmpty(t.getId())) {
			t.setCreateTime(new Date());
			t.setId(UuidUtil.getUuid());
			t.setCreateUser(UserContext.getCurrentUser().getUserName());
			clientDao.insert(t);
		} else {
			t.setAppKey(null);
			t.setUpdateTime(new Date());
			t.setUpdateUser(UserContext.getCurrentUser().getUserName());
			clientDao.update(t);
		}
		return 0;
	}

	@Override
	@Transactional
	public int update(Client t) {
		return 0;
	}

	@Override
	public Client getById(String id) {
		return clientDao.getById(id);
	}

	@Override
	public List<Client> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return clientDao.deleteById(id);
	}

	@Override
	@Transactional
	public int updateStatusById(String id, int status) {
		return clientDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<Client> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		return clientDao.getPagination(params, page, sorts);
	}

	@Override
	@Transactional
	public void updateFilePath(Map<String, Object> params) {

		clientDao.updateFilePath(params);
	}

}
