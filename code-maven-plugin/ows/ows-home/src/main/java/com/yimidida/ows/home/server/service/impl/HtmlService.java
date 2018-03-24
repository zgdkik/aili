package com.yimidida.ows.home.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.IHtmlDao;
import com.yimidida.ows.home.server.service.IHtmlService;
import com.yimidida.ows.home.share.entity.Html;

@Service
public class HtmlService implements IHtmlService {
	@Autowired IHtmlDao htmlDao;
	@Override
	public int insert(Html t) {
		return htmlDao.insert(t);
	}

	

	@Override
	public int update(Html t) {
		// TODO Auto-generated method stub
		return htmlDao.update(t);
	}

	@Override
	public Html getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Html> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Html> getPage(Map<String, Object> params, int pageNum,
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
	public Pagination<Html> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Html> getHtmlByMenuId(String menuId) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("menuId", menuId);
		return htmlDao.getHtmlByMenuId(param);
	}

	

}
