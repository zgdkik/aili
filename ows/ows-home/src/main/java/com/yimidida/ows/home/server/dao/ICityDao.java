package com.yimidida.ows.home.server.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.City;

/**
 * 城市DAO层
 * @author zhangm
 *
 */
public interface ICityDao extends IBaseDao<City, String> {
	List<City> getCityByName(Map<String, Object> params);


}
