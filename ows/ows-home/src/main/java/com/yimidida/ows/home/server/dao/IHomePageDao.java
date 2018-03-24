package com.yimidida.ows.home.server.dao;

import java.util.List;

import org.mybatis.spring.dao.IBaseDao;

import com.yimidida.ows.home.share.entity.HomePage;

/**
 * bannerDAO层
 * @author zhangm
 *
 */
public interface IHomePageDao extends IBaseDao<HomePage, String> {

	/**
	 * 后台banner-根据bannerId进行删除
	 * @param bannerId
	 * @return
	 */
	String deleteByBannerId(String bannerId);

	/**
	 * 根据type进行查询所有符合标准的banner
	 * @param bannerType
	 * @return
	 */
	List<HomePage> getHomeBanner(String bannerType);

}
