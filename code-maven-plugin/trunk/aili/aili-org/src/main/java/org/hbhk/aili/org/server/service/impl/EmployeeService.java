package org.hbhk.aili.org.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.mybatis.server.support.Page;
import org.hbhk.aili.mybatis.server.support.Pagination;
import org.hbhk.aili.mybatis.server.support.Sort;
import org.hbhk.aili.org.server.dao.IEmployeeDao;
import org.hbhk.aili.org.server.service.IEmployeeService;
import org.hbhk.aili.org.share.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeService implements IEmployeeService{
	
	@Autowired
	private  IEmployeeDao employeeDao;

	@Override
	@Transactional
	public int insert(EmployeeEntity t) {
		String id = t.getId();
		if(StringUtils.isEmpty(id)){
			t.setCreateTime(new Date());
			t.setId(UuidUtil.getUuid());
			t.setCreateUser(UserContext.getCurrentUser().getUserName());
			return employeeDao.insert(t);
		}else{
			t.setUpdateTime(new Date());
			t.setUpdateUser(UserContext.getCurrentUser().getUserName());
			t.setEmpCode(null);
			return employeeDao.update(t);
		}
		
	}

	@Override
	@Transactional
	public int update(EmployeeEntity t) {
		return employeeDao.update(t);
	}

	@Override
	public EmployeeEntity getById(String id) {
		return employeeDao.getById(id);
	}

	@Override
	public List<EmployeeEntity> get(Map<String, Object> params) {
		return employeeDao.get(params);
	}

	@Override
	public List<EmployeeEntity> getPage(Map<String, Object> params,
			int pageNum, int pageSize) {
		return employeeDao.getPage(params, pageNum, pageSize);
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public int deleteById(String id) {
		return employeeDao.deleteById(id);
	}

	@Override
	@Transactional
	public int updateStatusById(String id, int status) {
		return employeeDao.updateStatusById(id, status);
	}

	@Override
	public Pagination<EmployeeEntity> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		return employeeDao.getPagination(params, page, sorts);
	}

}
