package com.yimidida.ows.home.server.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.Html;

public interface IHtmlService extends IBaseService<Html, String>  {
	List<Html> getHtmlByMenuId(String menuId);
	
	
}
