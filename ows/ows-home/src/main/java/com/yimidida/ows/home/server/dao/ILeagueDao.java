package com.yimidida.ows.home.server.dao;

import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.home.share.entity.League;

/**
 * 首页bannerDAO层
 * @author zhangm
 *
 */
public interface ILeagueDao extends IBaseDao<League, String> {
	Pagination<League> getLeague(Map<String, Object> params,Page page);


}
