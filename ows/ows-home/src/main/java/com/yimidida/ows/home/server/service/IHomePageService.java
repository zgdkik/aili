package com.yimidida.ows.home.server.service;


import java.util.List;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.HomePage;
import com.yimidida.ows.home.share.entity.Html;

/**
 * banner的业务层接口
 * @author zhangm
 *
 */
public interface IHomePageService extends IBaseService<HomePage, String> {

	/**
	 * 根据bannerId进行删除
	 * @param bannerId
	 * @return
	 */
	String deleteByBannerId(String bannerId);

	/**
	 * 根据type进行查询所有符合标准的banner
	 * @param h
	 * @return
	 */
	List<HomePage> getHomeBanner(String bannerType);
	
}
