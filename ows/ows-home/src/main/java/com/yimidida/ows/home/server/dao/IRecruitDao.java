package com.yimidida.ows.home.server.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.home.share.entity.Recruit;

public interface IRecruitDao extends IBaseDao<Recruit, String> {

	Pagination<Recruit> queryRecruitList(Map<String, Object> map, Page p);
}
