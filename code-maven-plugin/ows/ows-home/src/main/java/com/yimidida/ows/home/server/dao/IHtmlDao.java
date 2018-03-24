package com.yimidida.ows.home.server.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;


import com.yimidida.ows.home.share.entity.Html;

public interface IHtmlDao extends IBaseDao<Html, String> {

	List<Html> getHtmlByMenuId(Map<String, Object> param);

}
