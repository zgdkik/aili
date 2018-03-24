package com.yimidida.ows.home.server.controller;

import java.util.List;
import java.util.Map;

import org.hsqldb.lib.HashMap;
import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.annotation.QueryPage;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.vo.QueryPageVo;
import com.yimidida.ows.home.server.service.IImgManageService;
import com.yimidida.ows.home.share.entity.ImgManage;
import com.yimidida.ymdp.core.share.entity.ResultEntity;

/**
 * 图片管理web
 * @author zhangm
 *
 */
@Controller
@RequestMapping("/imgManage")
public class ImgManageController extends AbstractController{
	
	@Autowired
	private IImgManageService imgManageService;
	
	/**
	 * 后台-查询所有
	 * @param model
	 * @param queryPageVo
	 * @return
	 */
	@RequestMapping("/getAllImgManage")
	@ResponseBody
	public Pagination<ImgManage> getAllImgManage(@QueryPage  QueryPageVo queryPageVo ){
		Pagination<ImgManage> pageOrder=imgManageService.getAllImgManage(queryPageVo.getParaMap(), queryPageVo.getPage(), null);
		return pageOrder;
	}
	
	/**
	 * 后台-保存＆修改图片
	 * @return
	 */
	@RequestMapping("saveAndUpdateImgManage")
	@ResponseBody
	public ResultEntity saveAndUpdateImgManage(ImgManage img){
		int flag = imgManageService.saveAndUpdateImgManage(img);  //flag为1保存成功
		return returnSuccess(flag);
	}
	
	/**
	 * 后台-修改时的数据回显
	 * @param id
	 * @return
	 */
	@RequestMapping("getImgById")
	@ResponseBody
	public ResultEntity getImgById(String id){
		ImgManage imgManage = imgManageService.getById(id);
		return returnSuccess(imgManage);
	}
	
	/**
	 *  后台-删除
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public ResultEntity deleteById(String id){
		int flag = imgManageService.deleteById(id);
		return returnSuccess(flag);
	}
	
	/**
	 * 后台-停用
	 * @param id
	 * @return
	 */
	@RequestMapping("blockStatusByid")
	@ResponseBody
	public ResultEntity blockStatusByid(String id){
		int flag = imgManageService.blockStatusByid(id);
		return returnSuccess(flag);
	}
	
	/**
	 * 后台-启用
	 * @param id
	 * @return
	 */
	@RequestMapping("updateStatusByid")
	@ResponseBody
	public ResultEntity updateStatusByid(String id){
		int flag = imgManageService.updateStatusByid(id);
		return returnSuccess(flag);
	}
	
	/**
	 * 前台-用于核心产品的展示
	 * @param status
	 * @return
	 */
	@RequestMapping("getShowImgById")
	@ResponseBody
	public ResultEntity getShowImgById(String status){
		List<ImgManage> list = imgManageService.getShowImgById(status);
		return returnSuccess(list);
	}
}
