package com.yimidida.ows.home.server.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.dao.ILeagueDao;
import com.yimidida.ows.home.server.service.IAreaService;
import com.yimidida.ows.home.server.service.ILeagueService;
import com.yimidida.ows.home.share.entity.Area;
import com.yimidida.ows.home.share.entity.League;

/**
 *  首页的banner展示业务层
 * @author rhb
 *
 */
@Service
public class LeagueService implements ILeagueService{
	
	@Autowired ILeagueDao leagueDao;
	@Override
	public int insert(League t) {
		return leagueDao.insert(t);
	}

	@Override
	public int update(League t) {
		return leagueDao.update(t);
	}

	@Override
	public League getById(String id) {
		// TODO Auto-generated method stub
		return leagueDao.getById(id);
	}

	@Override
	public List<League> get(Map<String, Object> params) {
		return leagueDao.get(params);
	}

	@Override
	public List<League> getPage(Map<String, Object> params, int pageNum,
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
		return leagueDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Pagination<League> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination<League> getLeague(Map<String, Object> params,Page page) {
		return leagueDao.getLeague(params,page);
	}

	

	

}
