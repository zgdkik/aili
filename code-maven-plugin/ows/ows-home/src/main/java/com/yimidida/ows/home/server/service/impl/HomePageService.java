package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.IHomePageDao;
import com.yimidida.ows.home.server.service.IHomePageService;
import com.yimidida.ows.home.share.entity.HomePage;

/**
 *  首页的banner展示业务层
 * @author zhangm
 *
 */
@Service
public class HomePageService implements IHomePageService{

	@Autowired
	private IHomePageDao homePageDao;

	@Override
	public int insert(HomePage t) {
		return homePageDao.insert(t);
	}

	@Override
	public int update(HomePage t) {
		// TODO Auto-generated method stub
		return homePageDao.update(t);
	}

	@Override
	public HomePage getById(String id) {
		return homePageDao.getById(id);
	}

	@Override
	public List<HomePage> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HomePage> getPage(Map<String, Object> params, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String bannerId) {
		return homePageDao.deleteById(bannerId);
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pagination<HomePage> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return homePageDao.getPagination(params, page, sorts);
	}

	@Override
	public String deleteByBannerId(String bannerId) {
		return homePageDao.deleteByBannerId(bannerId);
	}

	@Override
	public List<HomePage> getHomeBanner(String bannerType) {
		return homePageDao.getHomeBanner(bannerType);
	}
}
