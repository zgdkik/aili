package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.IProvinceDao;
import com.yimidida.ows.home.server.service.IProvinceService;
import com.yimidida.ows.home.share.entity.Province;

/**
 *  首页的banner展示业务层
 * @author zhangm
 *
 */
@Service
public class ProvinceService implements IProvinceService{
	@Autowired IProvinceDao provinceDao;
	@Override
	public int insert(Province t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Province t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Province getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Province> get(Map<String, Object> params) {
		return provinceDao.get(params);
	}

	@Override
	public List<Province> getPage(Map<String, Object> params, int pageNum, int pageSize) {
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
	public Pagination<Province> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
