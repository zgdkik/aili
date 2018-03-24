package com.yimidida.ows.home.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.ICityDao;
import com.yimidida.ows.home.server.dao.IDepartmentDao;
import com.yimidida.ows.home.server.service.ICityService;
import com.yimidida.ows.home.server.service.IDepartmentService;
import com.yimidida.ows.home.share.entity.City;
import com.yimidida.ows.home.share.entity.Department;

/**
 *  省 业务层
 * @author zhangm
 *
 */
@Service
public class DepartmentService implements IDepartmentService{
	@Autowired IDepartmentDao departmentDao;

	@Override
	public int insert(Department t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Department t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Department getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> get(Map<String, Object> params) {
		return departmentDao.get(params);
	}

	@Override
	public List<Department> getPage(Map<String, Object> params, int pageNum,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pagination<Department> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDepartmentName(String deptCode) {
		// TODO Auto-generated method stub
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("deptCode", deptCode);
		List<Department> depts=departmentDao.get(params);
		if(depts.size()>0){
			return depts.get(0).getDeptName();
		}
		else{
			return "";
		}
	}
	
	
}
