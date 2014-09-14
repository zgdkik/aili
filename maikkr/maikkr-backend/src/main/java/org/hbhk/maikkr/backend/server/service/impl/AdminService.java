package org.hbhk.maikkr.backend.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.EncryptUtil;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.surpport.Sort;
import org.hbhk.aili.orm.share.model.Pagination;
import org.hbhk.maikkr.backend.server.dao.IAdminDao;
import org.hbhk.maikkr.backend.server.service.IAdminService;
import org.hbhk.maikkr.backend.shared.pojo.AdminConstants;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

	@Autowired
	private IAdminDao adminDao;

	public AdminInfo login(AdminInfo admin) {
		String pwd = EncryptUtil.encodeSHA1(admin.getPwd());
		admin.setPwd(pwd);
		AdminInfo a = adminDao.getOne(admin);
		if (a == null) {
			return null;
		}
		if (a.getPwd().equals(admin.getPwd())) {
			return a;
		}
		return null;
	}

	public AdminInfo regist(AdminInfo admin) {
		AdminInfo model = new AdminInfo();
		model.setEmail(admin.getEmail());
		if(adminDao.getOne(model)!=null){
			throw  new BusinessException("用户已经存在");
		}
		String id = "admin_" + System.currentTimeMillis();
		admin.setId(id);
		String user = (String) RequestContext.getSession().getAttribute(AdminConstants.adminkey);
		admin.setCreatUser(user);
		admin.setCreateTime(new Date());
		admin.setPwd(EncryptUtil.encodeSHA1(admin.getPwd()));
		return adminDao.save(admin);
	}

	public Pagination<AdminInfo> queryAdminsByPage(Page page, Sort sort,
			Map<String, Object> params) {
		return adminDao.queryAdminsByPage(page, sort, params);
	}

	public AdminInfo save(AdminInfo model) {
		return null;
	}

	public AdminInfo update(AdminInfo model) {
		model.setUpdateTime(new Date());
		String user = (String) RequestContext.getSession().getAttribute(AdminConstants.adminkey);
		model.setUpdateUser(user);
		return adminDao.update(model);
	}

	public AdminInfo getOne(AdminInfo model) {
		return adminDao.getOne(model);
	}

	public List<AdminInfo> get(AdminInfo model, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AdminInfo> get(AdminInfo model) {
		// TODO Auto-generated method stub
		return null;
	}

}