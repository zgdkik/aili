package com.yimidida.ows.home.server.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.dao.IBaseDao;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.home.share.entity.ImgManage;


/**
 * 图片管理DAO
 * @author zhangm
 *
 */
public interface ImageManageDao extends IBaseDao<ImgManage, String>{

	/**
	 * 修改状态-停用
	 * @param id
	 * @return
	 */
	int blockStatusByid(String id);

	/**
	 * 修改状态-启用
	 * @param id
	 * @return
	 */
	int updateStatusByid(String id);

	/**
	 * 后台-分页查询
	 * @param paraMap
	 * @param page
	 * @param object
	 * @return
	 */
	Pagination<ImgManage> getAllImgManage(Map<String, Object> paraMap, Page page, Object object);

	/**
	 * 前台-查询核心产品数据
	 * @param map
	 * @return
	 */
	List<ImgManage> getShowImgById(Map<String, String> map);

}
