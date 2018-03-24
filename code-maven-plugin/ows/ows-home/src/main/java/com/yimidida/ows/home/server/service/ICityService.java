package com.yimidida.ows.home.server.service;


import java.util.List;
import java.util.Map;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.City;

/**
 * 城市 接口
 * @author zhangm
 *
 */
public interface ICityService extends IBaseService<City, String> {
	List<City> getCityByName(Map<String, Object> params);
}
