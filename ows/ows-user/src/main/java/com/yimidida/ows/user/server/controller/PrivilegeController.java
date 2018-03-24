package com.yimidida.ows.user.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.server.controller.AbstractController;
import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.base.share.vo.TreeVo;
import com.yimidida.ows.common.util.UuidUtil;
import com.yimidida.ows.user.server.service.IPrivilegeService;
import com.yimidida.ows.user.share.entity.PrivilegeEntity;
import com.yimidida.ymdp.core.share.entity.ResultEntity;
import com.yimidida.ymdp.core.share.ex.BusinessException;

@Controller
@RequestMapping("/privilege")
public class PrivilegeController extends AbstractController {

	@Autowired
	private IPrivilegeService functionService;
	
	
	/**
	 * 
	 * Description: 权限菜单首页
	 */
	@RequestMapping("/privilegelist")
	public String showMenuIndex() {
		
		return "/privilege/privilegelist";
	}
	
	/**
	 * 
	 * Description: 加载权限树
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeVo> getTree(String id,String name) {
		List<TreeVo> list = new ArrayList<>();
		if (id == null) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("privilegeCode", "0");
			map.put("status", 1);
			List<PrivilegeEntity> functionEntityList=functionService.getMenuService(map);
			for(PrivilegeEntity privilegeEntity:functionEntityList){
				TreeVo t = new TreeVo();
				t.setId(privilegeEntity.getPrivilegeCode());
				t.setName(privilegeEntity.getFunctionName());
				if(FrontendConstants.node.indexOf (privilegeEntity.getModuleType())<0){
					t.setIsParent(true);
				}
				list.add(t);
			}
		} else {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("parentCode", id);
			map.put("status", 1);
			List<PrivilegeEntity> functionEntityList=functionService.getMenuService(map);
			for(PrivilegeEntity privilegeEntity:functionEntityList){
				TreeVo t = new TreeVo();
				t.setId(privilegeEntity.getPrivilegeCode());
				t.setName(privilegeEntity.getFunctionName());
				map.put("code", privilegeEntity.getPrivilegeCode());
				if(FrontendConstants.node.indexOf (privilegeEntity.getModuleType())<0 ){
					t.setIsParent(true);
				}
				list.add(t);
			}
		}
		return list;
	}

	
	/**
	 * Description: 显示单条菜单信息
	 */
	@RequestMapping("/getMenuInfo/{privilegeCode}")
	@ResponseBody
	public ResultEntity getMenuInfoTow(Model model, HttpServletRequest request,
			HttpServletResponse response,@PathVariable("privilegeCode") String privilegeCode) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("privilegeCode", privilegeCode);
		map.put("status", 1);
		PrivilegeEntity privilegeEntity=functionService.getMenu(map);
		return returnSuccess(privilegeEntity);
	}
	
	
	/**
	 * 
	 * Description: 新增权限菜单
	 */
	@RequestMapping("/editMenu")
	@ResponseBody
	public ResultEntity addMenu(@Valid PrivilegeEntity privilegeEntity ,BindingResult result) {
		validator(result);
		String id = privilegeEntity.getId();
		if(StringUtils.isEmpty(id)){
			privilegeEntity.setId(UuidUtil.getUuid());
			privilegeEntity.setCreateUser(UserContext.getCurrentUser().getUserName());
			privilegeEntity.setCreateTime(new Date());
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("privilegeCode", privilegeEntity.getPrivilegeCode());
			map.put("status", 1);
			List<PrivilegeEntity> functionEntityList=functionService.getMenuService(map);
			
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("url", privilegeEntity.getUrl());
			List<PrivilegeEntity> functionEntityList1=functionService.getMenuService(map1);
			if(functionEntityList.size()>0){
				throw new BusinessException("权限编码已经存在");
			}
			if(functionEntityList1.size()>0){
				throw new BusinessException("权限URL已经存在");
			}
			privilegeEntity.setFunctionType(privilegeEntity.getModuleType());
			functionService.addMenuService(privilegeEntity);
			return returnSuccess("新增成功");
		}else{
			privilegeEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
			privilegeEntity.setUpdateTime(new Date());
			privilegeEntity.setParentCode(null);
			privilegeEntity.setPrivilegeCode(null);
			functionService.updateMenuService(privilegeEntity);
			return returnSuccess("修改成功");
		}
}
	
	/**
	 * 
	 * Description: 删除权限菜单
	 * @param privilegeEntity
	 */
	@RequestMapping("/deleteMenu/{privilegeCode}")
	@ResponseBody
	public ResultEntity deleteMenu(@PathVariable String privilegeCode) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("parentCode",privilegeCode);
		map.put("status", 1);
		List<PrivilegeEntity> functionEntityList = functionService.getMenuService(map);
		
		String str="";
		//判断菜单下是否有子菜单
		if(functionEntityList.size()>0){
			str="不能删除还存在子菜单的功能";
			throw  new BusinessException(str);
		}else{
			int aa = functionService.getCountByFunctionCodeService(privilegeCode);
			if(aa>0){
				str="不能删除还存在角色关联的功能";
				throw  new BusinessException(str);
			}else{
				map.remove("parentCode");
				map.put("status", 1);
				map.put("privilegeCode", privilegeCode);
				PrivilegeEntity privilegeEntity=functionService.getMenu(map);
				if(privilegeEntity==null){
					str="权限已经不存在";
					throw  new BusinessException(str);
				}
			    int num=functionService.deleteMenuService(privilegeEntity.getId());
				if(num<0){
					str="删除失败";
					throw  new BusinessException(str);
				}
			}
		}
		return returnSuccess("删除成功");
	}
	
	
	

}
