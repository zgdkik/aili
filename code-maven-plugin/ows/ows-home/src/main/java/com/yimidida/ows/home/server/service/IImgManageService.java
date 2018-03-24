package com.yimidida.ows.home.server.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;

import com.yimidida.ows.base.server.service.IBaseService;
import com.yimidida.ows.home.share.entity.ImgManage;

/**
 * 图片管理接口
 * @author zhangm
 *
 */
public interface IImgManageService extends IBaseService<ImgManage, String>{

	/**
	 * 后台-保存＆修改
	 * @param img
	 * @return
	 */
	int saveAndUpdateImgManage(ImgManage img);

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
	 * 分页查询
	 * @param paraMap
	 * @param page
	 * @param object
	 * @return
	 */
	Pagination<ImgManage> getAllImgManage(Map<String, Object> paraMap, Page page, Object object);

	/**
	 * 前台查询核心产品数据
	 * @param status
	 * @return
	 */
	List<ImgManage> getShowImgById(String status);

}
