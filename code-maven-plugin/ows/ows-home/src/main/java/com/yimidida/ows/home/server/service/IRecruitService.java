package com.yimidida.ows.home.server.service;

import java.util.List;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.Recruit;

public interface IRecruitService extends IBaseService<Recruit, String>  {
	
	Pagination<Recruit> getAllrecruit(Page page);

	Pagination<Recruit> queryRecruitList(String compCode, int page, int rows);
	
}
