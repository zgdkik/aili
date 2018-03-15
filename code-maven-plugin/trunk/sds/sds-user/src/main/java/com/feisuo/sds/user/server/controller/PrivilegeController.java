package com.feisuo.sds.user.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.core.share.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.server.controller.AbstractController;
import com.feisuo.sds.base.share.vo.TreeVo;
import com.feisuo.sds.common.util.UuidUtil;
import com.feisuo.sds.user.server.service.IPrivilegeService;
import com.feisuo.sds.user.share.entity.PrivilegeEntity;
import com.feisuo.sds.user.share.vo.PrivilegeResultVo;

/**
 * 
 * ClassName: FunctionController
 * Description: TODO
 * Author: dongshenghua
 * Date: 2015年11月26日
 */
@Controller
@RequestMapping("/auth")
public class PrivilegeController extends AbstractController {

	@Autowired
	private IPrivilegeService functionService;
	
	
	/**
	 * 
	 * Description: 权限菜单首页
	 * @return
	 * Created by dongshenghua 2015年11月26日
	 */
	@RequestMapping("/showMenuIndex")
	public String showMenuIndex() {
		
		return "/auth/showMenuForm";
	}
	
	/**
	 * 
	 * Description: 加载权限树
	 * @param id
	 * @param name
	 * @return
	 * Created by dongshenghua 2015年12月2日
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeVo> getTree(String id,String name) {
		List<TreeVo> list = new ArrayList<>();
		if (id == null) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("functionCode", "0");
			map.put("status", 1);
			map.put("active", "Y");
			List<PrivilegeEntity> functionEntityList=functionService.getMenuService(map);
			for(PrivilegeEntity privilegeEntity:functionEntityList){
				TreeVo t = new TreeVo();
				t.setId(privilegeEntity.getPrivilegeCode());
				t.setName(privilegeEntity.getFunctionName());
				if("Y".equals(privilegeEntity.getFunctionType())){
					t.setIsParent(true);
				}
				list.add(t);
			}
		} else {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("parentCode", id);
			map.put("status", 1);
			map.put("active", "Y");
			List<PrivilegeEntity> functionEntityList=functionService.getMenuService(map);
			for(PrivilegeEntity privilegeEntity:functionEntityList){
				TreeVo t = new TreeVo();
				t.setId(privilegeEntity.getPrivilegeCode());
				t.setName(privilegeEntity.getFunctionName());
				map.put("parentCode", privilegeEntity.getPrivilegeCode());
				List<PrivilegeEntity> functionEntityList1=functionService.getMenuService(map);
				
				if("Y".equals(privilegeEntity.getFunctionType()) ||(functionEntityList1!=null && functionEntityList1.size()>0)){
					t.setIsParent(true);
				}
				list.add(t);
			}
		}
		return list;
	}

	
	/**
	 * 
	 * Description: 显示单条菜单信息
	 * @return
	 * Created by dongshenghua 2015年11月26日
	 */
	@RequestMapping("/getMenuInfo/{functionCode}")
	@ResponseBody
	public ResultEntity getMenuInfoTow(Model model, HttpServletRequest request,
			HttpServletResponse response,@PathVariable("functionCode") String functionCode) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("functionCode", functionCode);
		PrivilegeEntity privilegeEntity=functionService.getMenuByIdService(map);
		if(privilegeEntity.getActive()!=null && "Y".equals(privilegeEntity.getActive())){
			privilegeEntity.setActive("是");
		}else{
			privilegeEntity.setActive("否");
		}
		
		if(privilegeEntity.getCheckable()!=null && "Y".equals(privilegeEntity.getCheckable())){
			privilegeEntity.setCheckable("是");
		}else{
			privilegeEntity.setCheckable("否");
		}
		
		if(privilegeEntity.getFunctionType()!=null && "Y".equals(privilegeEntity.getFunctionType())){
			privilegeEntity.setFunctionType("模块");
		}else{
			privilegeEntity.setFunctionType("菜单");
		}
		
		if(privilegeEntity.getLeaf()!=null && "Y".equals(privilegeEntity.getLeaf())){
			privilegeEntity.setLeaf("是");
		}else{
			privilegeEntity.setLeaf("否");
		}
		if(privilegeEntity.getParentCode()!=null && !"".equals(privilegeEntity.getParentCode())){
			map.put("functionCode", privilegeEntity.getParentCode());
			PrivilegeEntity fun=functionService.getMenuByIdService(map);
			privilegeEntity.setParentCode(fun.getFunctionName());
		}
		
		
		return returnSuccess(privilegeEntity);
	}
	
	/**
	 * 
	 * Description: 显示菜单新增页面
	 * @return
	 * Created by dongshenghua 2015年11月26日
	 */
	@RequestMapping("/showAddMenuForm/{functionCode}")
	@ResponseBody
	public ResultEntity showAddMenuForm(@PathVariable("functionCode") String functionCode) {
		
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("functionCode", functionCode);
		
		    PrivilegeEntity privilegeEntity=functionService.getMenuByIdService(map);
		
		    PrivilegeResultVo functionRelust=new PrivilegeResultVo();
		    functionRelust.setFlag("1");
		    
		    List<PrivilegeEntity> functionEntityList=functionService.getMenuService(null);
		    functionRelust.setFunctionEntityList(functionEntityList);
		    
		    functionRelust.setFunctionEntity(privilegeEntity);
		    
		    return returnSuccess(functionRelust);
	}
	
	/**
	 * 
	 * Description: 显示修改页面信息
	 * @return
	 * Created by dongshenghua 2015年11月26日
	 */
	@RequestMapping("/showUpdateMenuForm/{functionCode}")
	@ResponseBody
	public ResultEntity showUpdateMenuForm(@PathVariable("functionCode") String functionCode) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("functionCode", functionCode);
		
		PrivilegeEntity privilegeEntity=functionService.getMenuByIdService(map);
		
	    PrivilegeResultVo functionRelust=new PrivilegeResultVo();
	    functionRelust.setFunctionEntity(privilegeEntity);
	    functionRelust.setFlag("2");
	    
	    List<PrivilegeEntity> functionEntityList=functionService.getMenuService(null);
	    functionRelust.setFunctionEntityList(functionEntityList);
	    
		return returnSuccess(functionRelust);
	}
	
	
	/**
	 * 
	 * Description: 新增权限菜单
	 * @return
	 * Created by dongshenghua 2015年11月26日
	 */
	@RequestMapping("/addMenu")
	@ResponseBody
	public ResultEntity addMenu(PrivilegeEntity privilegeEntity) {
		privilegeEntity.setId(UuidUtil.getUuid());
		privilegeEntity.setCreateUser(UserContext.getCurrentUser().getUserName());
		privilegeEntity.setCreateTime(new Date());
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("functionCode", privilegeEntity.getPrivilegeCode());
		List<PrivilegeEntity> functionEntityList=functionService.getMenuService(map);
		
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("uri", privilegeEntity.getUrl());
		List<PrivilegeEntity> functionEntityList1=functionService.getMenuService(map1);
		
		String str="";
		String count="0";
		if(functionEntityList.size()>0){
			count="1";
			str="功能编码已经存在";
		}else if(functionEntityList1.size()>0){
			count="1";
			str="功能URI已经存在";
		}else{
			int num=functionService.addMenuService(privilegeEntity);
			if(num>0){
				str="添加成功";
			}else{
				count="1";
				str="添加失败";
			}
		}
		return returnSuccess(str,count);
	}
	
	/**
	 * 
	 * Description: 修改权限菜单
	 * @param privilegeEntity
	 * @return
	 * Created by dongshenghua 2015年11月26日
	 */
	@RequestMapping("/updateMenu")
	@ResponseBody
	public ResultEntity updateMenu(PrivilegeEntity privilegeEntity) {
		privilegeEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
		privilegeEntity.setUpdateTime(new Date());
		
		int num=functionService.updateMenuService(privilegeEntity);
		String str="";
		if(num>0){
			str="修改成功";
		}else{
			str="修改失败";
		}
		return returnSuccess(str,str);
	}
	
	/**
	 * 
	 * Description: 删除权限菜单
	 * @param privilegeEntity
	 * @return
	 * Created by dongshenghua 2015年11月26日
	 */
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public ResultEntity deleteMenu(PrivilegeEntity privilegeEntity) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("parentCode", privilegeEntity.getPrivilegeCode());
		List<PrivilegeEntity> functionEntityList=functionService.getMenuService(map);
		
		String str="";
		String count="0";
		//判断菜单下是否有子菜单
		if(functionEntityList.size()>0){
			count="1";
			str="不能删除还存在子菜单的功能";
		}else{
			int aa=functionService.getCountByFunctionCodeService(privilegeEntity.getPrivilegeCode());
			if(aa>0){
				count="1";
				str="不能删除还存在角色关联的功能";
			}else{
			    int num=functionService.deleteMenuService(privilegeEntity.getId());
				if(num>0){
					str="删除成功";
				}else{
				    count="1";
					str="删除失败";
				}
			}
		}
		return returnSuccess(str,count);
	}
	
	
	

}
