package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.IAreaDao;
import com.yimidida.ows.home.server.service.IAreaService;
import com.yimidida.ows.home.share.entity.Area;

/**
 *  区县业务层
 * @author zhangm
 *
 */
@Service
public class AreaService implements IAreaService{
	@Autowired IAreaDao areaDao;
	@Override
	public int insert(Area t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Area t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Area getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Area> get(Map<String, Object> params) {
		
		return areaDao.get(params);
	}

	@Override
	public List<Area> getPage(Map<String, Object> params, int pageNum, int pageSize) {
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
	public Pagination<Area> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
