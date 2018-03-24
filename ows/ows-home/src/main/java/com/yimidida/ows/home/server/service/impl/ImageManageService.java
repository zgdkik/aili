package com.yimidida.ows.home.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.Page;
import org.mybatis.spring.support.Pagination;
import org.mybatis.spring.support.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.home.server.dao.ImageManageDao;
import com.yimidida.ows.home.server.service.IImgManageService;
import com.yimidida.ows.home.share.entity.ImgManage;

/**
 * 图片管理业务层
 * @author zhangm
 *
 */
@Service
public class ImageManageService implements IImgManageService{

	@Autowired
	private ImageManageDao imageManageDao;
	
	@Override
	public int insert(ImgManage t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ImgManage t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImgManage getById(String id) {
		return imageManageDao.getById(id);
	}

	@Override
	public List<ImgManage> get(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImgManage> getPage(Map<String, Object> params, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotalCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		return imageManageDao.deleteById(id);
	}

	@Override
	public int updateStatusById(String id, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* 
	 * 后台-数据展示
	 */
	@Override
	public Pagination<ImgManage> getPagination(Map<String, Object> params, Page page, Sort... sorts) {
		return imageManageDao.getPagination(params, page, null);
	}

	/* 
	 * 后台-图片数据插入与修改
	 */
	@Override
	public int saveAndUpdateImgManage(ImgManage img) {
		int flag = 0;
		String userName = UserContext.getCurrentUser().getUserName();   //获取当前用户
		String compCode = UserContext.getCurrentUser().getCompCode();    //获取当前公司
		Date now = new Date();   //获取当前时间
		if(StringUtils.isBlank(img.getId())){    //如果id为空则是添加   
			img.setId(UuidUtil.getUuid());   //设置id
			img.setCompCode(compCode);       //设置公司
			img.setCreateDate(now);          //设置当前时间
			img.setCreateUser(userName);     //设置当前用户
			img.setType(1);                  //首页核心产品给其状态type为:1
			img.setStatus(0);                //状态默认为0
			flag = imageManageDao.insert(img);
		}else{
			img.setChangeDate(now);          //更新当前时间
			img.setChangeUser(userName);     //更新操作用户
			flag = imageManageDao.update(img);
		}
		return flag;
	}

	/* 
	 * 修改-停用
	 */
	@Override
	public int blockStatusByid(String id) {
		int flag = imageManageDao.blockStatusByid(id);
		return flag;
	}

	/* 
	 * 修改-启用 
	 */
	@Override
	public int updateStatusByid(String id) {
		int flag = imageManageDao.updateStatusByid(id);
		return flag;
	}

	/* 
	 * 分页查询
	 */
	@Override
	public Pagination<ImgManage> getAllImgManage(Map<String, Object> paraMap, Page page, Object object) {
		Pagination<ImgManage> paginnation = imageManageDao.getAllImgManage(paraMap,page,null);
		return paginnation;
	}

	/* 
	 * 前台查询核心产品的数据
	 */
	@Override
	public List<ImgManage> getShowImgById(String status) {
		Map<String ,String> map = new HashMap<String, String>();
		map.put("status", status);
		return imageManageDao.getShowImgById(map);
	}

}
