package org.hbhk.aili.client.main.client.service.imp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.util.ImageUtil;
import org.hbhk.aili.client.core.core.jpf.utils.JpfUtils;
import org.hbhk.aili.client.main.client.MenuNodeInfo;
import org.hbhk.aili.client.main.client.action.AbstractOpenUIAction;
import org.hbhk.aili.client.main.client.action.IMainFrame;
import org.hbhk.aili.client.main.client.action.OpenMenuAction;
import org.hbhk.aili.client.main.client.service.IMenuService;
import org.hbhk.aili.client.main.client.ui.MainUI;
import org.hbhk.aili.client.main.client.ui.OpenWaybillEditUIAction;
import org.java.plugin.Plugin;

/**
 * 菜单服务：针对GUI端的菜单，对远程的菜单进行封装，以便适用于GUI端的菜单
 * 
 */
public class MenuService implements IMenuService {
	/**
	 * log 从日志工厂获取日志类
	 */
	private static final Log LOG = LogFactory.getLog(MainUI.class);
	
	/**
	 * 获取menu数据
	 */
	@Override
	public Map<String,  MenuNodeInfo> getMenusData(Plugin plugin) {
      
//		/**
//		 * 获取登录后服务端返回的实体
//		 */
//		LoginInfo loginInfo=(LoginInfo) SessionContext.get("login_info");
//		/**
//		 * 获取所有菜单
//		 */
//		List<ResourceEntity> resources= loginInfo.getResources();
//		/**
//		 * 获取该用户的所有菜单
//		 */
//		List<ResourceEntity> userResources= loginInfo.getUserResources();
//		/**
//		 * 新增链式hash map
//		 */
//		Map<String,  MenuNodeInfo> menuNodeInfos=new LinkedHashMap<String, MenuNodeInfo>();
//		/**
//		 * 什么父菜单
//		 */
//		ResourceEntity parentRes=null;
//		/**
//		 * 如果用户菜单不为空
//		 */
//       if(userResources!=null){
//		for(ResourceEntity userResource:userResources)
//		{
//			/**
//			 * 如果父类菜单为空
//			 */
//			if(userResource.getParentRes()!=null){
//				/**
//				 * 设置菜单的父类节点的code 为1
//				 */
//				userResource.getParentRes().setCode("1");
//				/**
//				 * 获取父类节点
//				 */
//				parentRes=userResource.getParentRes();
//				/**
//				 * 设置菜单的父类节点
//				 */
//				userResource.setParentRes(parentRes);
//			}
//			/**
//			 * 设置用户菜单的编码
//			 */
//			userResource.setCode(userResource.getCode()+"user");//由于ID不能重复，需要+user进行区分
//		}
//       }
//		
//
//       
//		/**
//		 *对远程的 菜单遍历转换		
//		 */
//		
//		if(resources!=null)
//		{
//		    	/**
//			 *判断userResources是否为空，如果不为空，则将userResources里面的所有元素都添加到resources里面
//			 */
//			if(userResources!=null){
//				resources.addAll(userResources);
//			}
//			/**
//			 * 使用菜单转换
//			 */
//			menucovertor(menuNodeInfos,resources,plugin);//转换菜单类型
//		}
				
	//	return menuNodeInfos;
		Map<String,  MenuNodeInfo> menuNodeInfos=new LinkedHashMap<String, MenuNodeInfo>();
		for (int i = 0; i < 5; i++) {
			MenuNodeInfo menu = new MenuNodeInfo();
			menu.setText("测试菜单"+i);
			menu.setCode("test"+i);
			ImageIcon mainImage = ImageUtil.getImageIcon(MenuService.class,"main.png");
			menu.setIcon(mainImage);
			menu.setBgClass("ye1-node-lvl4");
			List<MenuNodeInfo> childMenus = new ArrayList<MenuNodeInfo>();
			for (int j = 0; j < 3; j++) {
				MenuNodeInfo menu1 = new MenuNodeInfo();
				menu1.setText("c测试菜单"+j);
				menu1.setCode(i+"ctest"+j);
				ImageIcon mainImage1 = ImageUtil.getImageIcon(MenuService.class,"main.png");
				menu1.setIcon(mainImage1);
				menu1.setBgClass("ye1-node-lvl4");
				OpenWaybillEditUIAction openMenuAction=JpfUtils.createInstance(plugin, OpenWaybillEditUIAction.class.getName());
				menu1.setAction(openMenuAction);
				openMenuAction.setOpentime(3);
				//openMenuAction.setUriName("c测试菜单"+j);
				menu1.setOpentime(3);
				childMenus.add(menu1);
			}
			menu.setChildMenus(childMenus);
			menuNodeInfos.put("test"+i, menu);
		}
		return menuNodeInfos;
	}
	
	
	//根据sonar(方法名应该以小写字母开头)修改
	/**
	 * 
	 * 加载菜单
	 */
	private  void menucovertor( Map<String,  MenuNodeInfo> menuNodeInfos   ,List<ResourceEntity> resources,Plugin plugin)
	{
//	    	//定义一个ImageIcon对象
//		ImageIcon mainImage=null;
//		String parentCode=null;
//		
//		for (ResourceEntity resourceEntity : resources)
//		{
//
//			if(resourceEntity.getParentRes()==null){
//				parentCode=resourceEntity.getCode();
//				break;
//			}
//		}
//		
//		
//		
//		/**
//		 * 循环遍历参数resources集合
//		 */
//		for (ResourceEntity resourceEntity : resources) {
//		    	/**
//			 * 判断resourceEntity是否为空，如果为空，则记录日志，继续循环下一条记录
//			 */
//			if(resourceEntity==null)
//			{
//				LOG.info("the resourceEntity has one null,mmaybe cache is this value!");
//				continue;
//			}
//			
//			/**
//			 * 创建MenuNodeInfo对象
//			 */
//			MenuNodeInfo menuNodeInfo = new MenuNodeInfo();
//
//			// 由于需要使用outlook样式来进行显示，第一级需要进行过滤
//
//				if (resourceEntity.getCode().equals(parentCode)) {
//					continue;// 继续循环
//				}
//				if (resourceEntity.getParentRes().getCode().equals(parentCode)) {
//				    	//设置resourceEntity的父菜单为空
//					resourceEntity.setParentRes(null);
//					//设置resourceEntity的图标的CSS样式
//					resourceEntity.setIconCls("main.png");
//				}
//			
//
//			menuNodeInfo.setId(resourceEntity.getCode());// 菜单Id
//			menuNodeInfo.setCode(resourceEntity.getCode());// 菜单code
//			menuNodeInfo.setBgClass(resourceEntity.getCls());//背景样式
////			menuNodeInfo.setResLevel(resourceEntity.getResLevel());//功能层次
//			
//			// 父菜单
//			/**
//			 * 判断resourceEntity的父菜单是否为空
//			 */
//			if (resourceEntity.getParentRes() != null) {
//			    	/**
//				 * 菜单节点信息的父节点设置为resourceEntity的父菜单的code
//				 */
//				menuNodeInfo.setParentId(resourceEntity.getParentRes().getCode());
//			}
//			Integer index = 1;	//声明Integer类型变量，默认值为1
//			try {	
//			    	/**
//				 * 判断显示顺序是否为空，如果不为空，则获取转化为Integer类型并赋给index变量
//				 */
//				if (resourceEntity.getDisplayOrder() != null) {
//					index = Integer.parseInt(resourceEntity
//							.getDisplayOrder());
//				}
//
//			} catch (NumberFormatException e) {
//				//LOG.warn(e);
//				//根据sonar(使用正确的异常日志 )修改
//				LOG.error(e.getMessage(), e);
//			}
//
//			/**
//			 * 内部顺序:不是必须值
//			 */
//			menuNodeInfo.setIndex(index);
//			/**
//			 * 复制菜单文字
//			 */
//			if (resourceEntity.getName() != null) {
//			    	//设置菜单节点信息的文本为resourceEntity的name
//				menuNodeInfo.setText(resourceEntity.getName());
//			} else {
//			    	//设置菜单节点信息的文本为空字符串
//				menuNodeInfo.setText("");
//			}
//			/**
//			 * 判断resourceEntity.getIconCls()是否为空
//			 */
//			if (!StringUtils.isEmpty(resourceEntity.getIconCls())) {
//			    	/**
//				 * 根据MenuService和Iconcls获取mainImage
//				 */
//				mainImage = ImageUtil.getImageIcon(MenuService.class,
//						resourceEntity.getIconCls());
//				/**
//				 * 设置菜单节点信息的图标为mainImage
//				 */
//				menuNodeInfo.setIcon(mainImage);
//
//			}
//			menuNodeInfo.setOpentime(3);//暂时设置为3次
//			//如果是GUI解析uri
//			if (resourceEntity.getUri() != null) {
//			    	//创建一个一个数组uri并获取
//				String[] uri = resourceEntity.getUri().split("/");
//				//创建一个int型变量并获取
//				int   uriIndex=resourceEntity.getUri().indexOf("#!");
//				//定义一个Object对象
//				Object obj = null;
//				//定义一个Action对象
//				Action action = null;
//				/**
//				 * 判断uri的length是否等于2
//				 */
//				if (uri.length == 2) {
//					
//					try {
//					    	/**
//						 * 根据插件信息获取obj对象
//						 */
//						obj = JpfUtils.createInstance(plugin.getManager()
//								.getPlugin(uri[0]), uri[1]);
//
//						// action need open time
//						/**
//						 * 判断在运行时obj是否是Action的一个实例
//						 */
//						if (!(obj instanceof Action)) {
//						    	/**
//							 * 记录错误日志
//							 */
//							LOG.error(new RuntimeException(obj.getClass()
//									.getName() + "must be a javax.swing.Action"));
//
//						} else {
//						    	/**
//							 * obj赋给action
//							 */
//							action = (Action) obj;
//
//						}
//						//加载Action
//						initAction(obj,menuNodeInfo);
//					} catch (PluginLifecycleException e) {
//					LOG.error("菜单：" + resourceEntity.getName()
//								+ "所在插件循环依赖问题");
//					} catch (IllegalArgumentException e) {
//
//						LOG.error("菜单：" + resourceEntity.getName()
//								+ "找不到pluginID 或 action 类");
//					} catch (Exception e) {
//						LOG.error("菜单：" + resourceEntity.getName() + "异常");
//					}
//					
//				}else if(uriIndex!=-1)
//				{
//					//定义一个String型的变量并获取
//					String s=resourceEntity.getUri();
//					//创建一个int型变量并获取
//					int   outSysIndex=resourceEntity.getUri().indexOf("##");
//					if(outSysIndex!=-1){
//					
//						String sysName	=s.substring(0,outSysIndex);
//						//截取字符串并追加然后赋给变量s
//						String url=s.substring(outSysIndex+3, uriIndex)+s.substring(uriIndex+2);
//						
//						OpenOutSysMenuAction openMenuAction=JpfUtils.createInstance(plugin, "com.deppon.foss.module.mainframe.client.action.OpenOutSysMenuAction");
//						//设置统一资源标识符
//						openMenuAction.setUri(url);
//						//系统名称
//						openMenuAction.setSysName(sysName);
//						
//						//设置统一资源标识符名称
//						openMenuAction.setUriName(resourceEntity.getName());
//						//给action赋值
//						action = (Action) openMenuAction;
//						//加载Action
//						initAction(action,menuNodeInfo);
//						
//					}else{
//					
//						//截取字符串并追加然后赋给变量s
//						s=s.substring(0, uriIndex)+s.substring(uriIndex+2);
//						//根据插件获取菜单时间触发器
//						OpenMenuAction openMenuAction=JpfUtils.createInstance(plugin, "com.deppon.foss.module.mainframe.client.action.OpenMenuAction");
//						//设置统一资源标识符
//						openMenuAction.setUri(s);
//						//设置统一资源标识符名称
//						openMenuAction.setUriName(resourceEntity.getName());
//						//给action赋值
//						action = (Action) openMenuAction;
//						//加载Action
//						initAction(action,menuNodeInfo);
//					}
//				}
//				//菜单节点信息设置Action
//				menuNodeInfo.setAction(action);
//				
//				
//			}
//			
//			//记录日志
//			LOG.info("menuNodeInfo:"+menuNodeInfo.getCode()+";"+menuNodeInfo.getText()+";prantID:"+menuNodeInfo.getParentId());
//			//向menuNodeInfos集合中添加元素
//			menuNodeInfos.put(menuNodeInfo.getCode(), menuNodeInfo);
//			
//			
//		}
		
	}
	
	
	
	/**
	 * 加载Action所需要的参数
	 * @param obj
	 * @param menuNodeInfo
	 * @author 026113-foss-linwensheng
	 */
	private void initAction(Object obj,MenuNodeInfo menuNodeInfo)
	
	{
		// action need open time 
	    	/**
	    	 * 在运行时obj对象是否是AbstractOpenUIAction的一个实例
	    	 */
		if (obj instanceof AbstractOpenUIAction) {
			((AbstractOpenUIAction<?>) obj)
					.setOpentime(menuNodeInfo.getOpentime());
		}
		// main frame need open time
		/**
	    	 * 在运行时obj对象是否是IMainFrame的一个实例
	    	 */		
		if (obj instanceof IMainFrame) {
			((IMainFrame) obj).setOpentime(menuNodeInfo
					.getOpentime());
		}
	}
	
	
	
	

}