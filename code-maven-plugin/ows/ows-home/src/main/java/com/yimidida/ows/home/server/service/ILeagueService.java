package com.yimidida.ows.home.server.service;


import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.League;

/**
 * 网点

 */
public interface ILeagueService extends IBaseService<League, String> {
	Pagination<League> getLeague(Map<String, Object> params,Page page);
}
