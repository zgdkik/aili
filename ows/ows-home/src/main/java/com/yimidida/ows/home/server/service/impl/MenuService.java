package com.yimidida.ows.home.server.service.impl;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.FixedOrderComparator;
import org.springframework.stereotype.Service;

import com.yimidida.ows.home.server.service.IMenuService;
import com.yimidida.ows.home.share.vo.MenuList;
import com.yimidida.ows.home.share.vo.MenuVo;
import com.yimidida.ows.user.share.entity.PrivilegeEntity;

@Service
public class MenuService implements IMenuService{
	/**
	 * 将菜单按照规则输出
	 */
	@Override
	public MenuVo getMenuVo(List<PrivilegeEntity> list) {
		//获取根菜单
		MenuVo menuVo=new MenuVo();
		List<MenuList> menuLists=new ArrayList<MenuList>();
		List<PrivilegeEntity> baseList=new ArrayList<PrivilegeEntity>();
		for (PrivilegeEntity p : list) {
			if("platform".equals(p.getParentCode())){
				baseList.add(p);
			}
		}
		
		baseList=sortIntMethod(baseList);
		for (PrivilegeEntity p1 : baseList) {
			//大菜单集合
			//单个大菜单  包括父节点
			List<PrivilegeEntity> menuList=new ArrayList<PrivilegeEntity>();
			//单个大菜单的子节点
			List<PrivilegeEntity> childList=new ArrayList<PrivilegeEntity>();
			//获取父节点的子节点
			for (PrivilegeEntity p2 : list) {
				if(p1.getPrivilegeCode().equals(p2.getParentCode())){
					childList.add(p2);
				}
			}
			//子节点排序
			childList=sortIntMethod(childList);
			//单个大菜单合成
			for (PrivilegeEntity cp : childList) {
				menuList.add(cp);
			}
			MenuList mList=new MenuList();
			//大菜单添加父节点 信息
			mList.setPrivilegeCode(p1.getPrivilegeCode());
			mList.setGwUrl(p1.getGwUrl());
			mList.setFunctionName(p1.getFunctionName());
			mList.setParentCode(p1.getParentCode());
			mList.setMenuList(menuList);
			menuLists.add(mList);
		}
		menuVo.setList(menuLists);
		return menuVo;
	}
	@SuppressWarnings("unchecked")  
	public static List<PrivilegeEntity> sortIntMethod(List<PrivilegeEntity> list){  
	    Collections.sort(list, new Comparator(){  
	        @Override  
	        public int compare(Object o1, Object o2) {  
	        	PrivilegeEntity p1=(PrivilegeEntity)o1;  
	        	PrivilegeEntity p2=(PrivilegeEntity)o2;  
	            if(Integer.parseInt(p1.getDisplayOrder())>Integer.parseInt(p2.getDisplayOrder())){  
	                return 1;  
	            }else if(Integer.parseInt(p1.getDisplayOrder())==Integer.parseInt(p2.getDisplayOrder())){  
	                return 0;  
	            }else{  
	                return -1;  
	            }  
	        }             
	    }); 
	    return list;
	}  
	
	

	
	

	

}
