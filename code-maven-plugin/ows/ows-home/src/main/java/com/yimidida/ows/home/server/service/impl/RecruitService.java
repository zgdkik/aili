package com.yimidida.ows.home.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.home.server.dao.IRecruitDao;
import com.yimidida.ows.home.server.service.IRecruitService;
import com.yimidida.ows.home.share.entity.Recruit;

@Service
public class RecruitService implements IRecruitService {
	@Autowired IRecruitDao recruitDao;

	

	@Override
	public Pagination<Recruit> getAllrecruit(Page page) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("compCode", UserContext.getCurrentUser().getCompCode());
		param.put("compCode", "ddwl");
		return recruitDao.queryRecruitList(param, page);
	}



	@Override
	public int insert(Recruit t) {
		return recruitDao.insert(t);
	}



	@Override
	public int update(Recruit t) {
		return recruitDao.update(t);
	}



	@Override
	public Recruit getById(String id) {
		// TODO Auto-generated method stub
		return recruitDao.getById(id);
	}



	@Override
	public List<Recruit> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Recruit> getPage(Map<String, Object> params, int pageNum,
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
		return recruitDao.deleteById(id);
	}



	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Pagination<Recruit> getPagination(Map<String, Object> params,
			Page page, Sort... sorts) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Pagination<Recruit> queryRecruitList(String compCode, int page, int rows) {
		Page p=new Page();
		p.setPageNum(page);
		p.setPageSize(rows);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("compCode", compCode);
		return recruitDao.queryRecruitList(map,p);
	}

	
	

	

}
