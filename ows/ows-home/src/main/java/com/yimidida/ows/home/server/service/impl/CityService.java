package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.ICityDao;
import com.yimidida.ows.home.server.service.ICityService;
import com.yimidida.ows.home.share.entity.City;

/**
 *  城市业务层
 * @author zhangm
 *
 */
@Service
public class CityService implements ICityService{
	@Autowired ICityDao cityDao;
	@Override
	public int insert(City t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(City t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public City getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> get(Map<String, Object> params) {
		return cityDao.get(params);
	}

	@Override
	public List<City> getPage(Map<String, Object> params, int pageNum, int pageSize) {
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
	public Pagination<City> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getCityByName(Map<String, Object> params) {
		
		return cityDao.getCityByName(params);
	}

	
}
