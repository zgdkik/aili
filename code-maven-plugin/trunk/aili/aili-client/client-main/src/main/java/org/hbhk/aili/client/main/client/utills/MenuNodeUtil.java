package org.hbhk.aili.client.main.client.utills;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.main.client.MenuNodeInfo;
import org.java.plugin.Plugin;
public class MenuNodeUtil {
	/**
	 * 国际化对象
	 */
	private II18n i18n = I18nManager.getI18n(MenuNodeUtil.class); 

	
	/**
    	/**
	 * 定义MenuNodeInfo类型的List集合menus
	 */
	List<MenuNodeInfo> menus = null;
	/**
	 * 定义key为String型，Value为MenuNodeInfo型的Map集合
	 */
	Map<String, MenuNodeInfo> toDoMsgMenuNodeMap = null;
	
	public static Plugin plugin;
	
	/**
	 * PKP_PDA_WAYBILL
	 * PKP_WB_LAB_PT
	 * PKP_WB_AUDIT
	 * PKP_WB_ACCECPT
	 */
	Map<String, String> localBizActionMap = new HashMap<String, String> ();
	Map<String, Object> classIdMap = new HashMap<String, Object>();
	
	
 	/**
	 * 定义静态的MenuNodeUtil的实例instance
	 */
	private static MenuNodeUtil instance = null;
	
	private MenuNodeUtil(){
	    	/**
		 * 创建一个List集合menus
		 */
		menus = new ArrayList<MenuNodeInfo>();
		/**
		 * 创建一个Map集合toDoMsgMenuNodeMap
		 */
		toDoMsgMenuNodeMap = new HashMap<String,MenuNodeInfo>();
		
//		localBizActionMap.put(DictionaryValueConstants.TODOMSG__BUSINESS_TYPE__PDA_WAYBILL, 
//				"com.deppon.foss.module.pickup.creating.client.action.OpenWaybillEditUIAction");
//		
//		localBizActionMap.put(DictionaryValueConstants.TODOMSG__BUSINESS_TYPE__WAYBILL_CHANGE_LABELED_PRINT, 
//				"com.deppon.foss.module.mainframe.client.action.OpenMenuAction");
//		
//		
//		localBizActionMap.put(DictionaryValueConstants.TODOMSG__BUSINESS_TYPE__WAYBILL_CHANGE_ACCECPT, 
//				"com.deppon.foss.module.pickup.changing.client.action.OpenWaybillRFCDealUIAction");
//		localBizActionMap.put(DictionaryValueConstants.TODOMSG__BUSINESS_TYPE__WAYBILL_CHANGE_AUDIT, 
//				"com.deppon.foss.module.pickup.changing.client.action.OpenWaybillRFCCheckUIAction");
//		localBizActionMap.put(ToDoMsgConstants.KEY_TODO_MSG_BIZTYPE_PKP_ACTIVE_OFFLINE_WAYBILL, 
//				"com.deppon.foss.module.pickup.creating.client.action.OpenWaybillEditUIAction");
//		localBizActionMap.put(ToDoMsgConstants.KEY_TODO_MSG_BIZTYPE_PKP_MODIFY_LABEL, 
//				"com.deppon.foss.module.pickup.creating.client.action.OpenPrintSignUIAction");
//		localBizActionMap.put(ToDoMsgConstants.KEY_TODO_MSG_BIZTYPE_PKP_CHANGING_HANDLE, 
//				"com.deppon.foss.module.pickup.changing.client.action.OpenWaybillRFCDealUIAction");
//		localBizActionMap.put(ToDoMsgConstants.KEY_TODO_MSG_BIZTYPE_PKP_CHANGING_CHEKC, 
//				"com.deppon.foss.module.pickup.changing.client.action.OpenWaybillRFCDealUIAction");
//		
//		localBizActionMap.put(DictionaryValueConstants.TODOMSG__BUSINESS_TYPE__WAYBILL_CHANGE_APPLY, "com.deppon.foss.module.pickup.common.client.action.OpenWabillRfcAccountUIAction");
//		localBizActionMap.put(ToDoMsgConstants.KEY_TODO_MSG_BIZTYPE_PKP_NOT_SIGNED_WAYBILL, 
//				"com.deppon.foss.module.mainframe.client.action.OpenMenuAction1");
		WebMenuDto dto = new WebMenuDto();
		dto.setClassName("com.deppon.foss.module.mainframe");
		dto.setName(i18n.get("mainFrame.subOpenMenuTdo"));
		dto.setUri("/pkp-deliver-web/waybill/todoActionIndex_include.action");
		classIdMap.put("com.deppon.foss.module.mainframe.client.action.OpenMenuAction", 
				dto);
		
		WebMenuDto dto1 = new WebMenuDto();
		dto1.setClassName("com.deppon.foss.module.mainframe");
		dto1.setName(i18n.get("mainFrame.subOpenMenuSign"));
		dto1.setUri("/bse-querying-web/querying/integrativeQueryIndex_include.action");
		classIdMap.put("com.deppon.foss.module.mainframe.client.action.OpenMenuAction1", 
				dto1);

		classIdMap.put("com.deppon.foss.module.pickup.common.client.action.OpenWabillRfcAccountUIAction", 
				"com.deppon.foss.module.pkp-common");
		
		classIdMap.put("com.deppon.foss.module.pickup.creating.client.action.OpenWaybillEditUIAction", 
				"com.deppon.foss.module.pkp-creating");
		
		classIdMap.put("com.deppon.foss.module.pickup.changing.client.action.OpenWaybillRFCDealUIAction", 
				"com.deppon.foss.module.pkp-changing");
		classIdMap.put("com.deppon.foss.module.pickup.changing.client.action.OpenWaybillRFCCheckUIAction", 
				"com.deppon.foss.module.pkp-changing");
	}
	
	//根据sonar问题(多线程错误 - 错误的对static属性进行了延迟初始化 )修改
	public synchronized static MenuNodeUtil getInstance(){
	    	/**
		 * 判断instance实例是否为空，如果为空，就创建一个新的实例
		 */
		if(instance==null){
			instance = new MenuNodeUtil();
		}
		/**
		 * 返回instance实例
		 */
		return instance;
	}
	
	public void resetMenus(List<MenuNodeInfo> plist){
	    	/**
		 * 将参数plist赋给当前属性menus
		 */
		this.menus = plist;
	}
	
	public void registerToDoMsgMenuNodeMap(String pBiztype,MenuNodeInfo pNode){
	    	/**
		 * 判断toDoMsgMenuNodeMap是否为空，如果为空，则创建一个新的Map集合
		 */
		if(toDoMsgMenuNodeMap==null){
			toDoMsgMenuNodeMap = new HashMap<String,MenuNodeInfo>();
		}
		/**
		 * 向toDoMsgMenuNodeMap添加元素
		 */
		toDoMsgMenuNodeMap.put(pBiztype, pNode);
	}
	
	public MenuNodeInfo findNodeOneByToDoMsgBizType(String pBizType){
	    	/**
		 * 判断toDoMsgMenuNodeMap是否为空并且pBizType是否为空，如果都不为空，则返回根据参数pBizType获取元素
		 */
		if(toDoMsgMenuNodeMap!=null && pBizType!=null){
			return toDoMsgMenuNodeMap.get(pBizType);
		}
		/**
		 * 返回null
		 */
		return null;
	}
	
	public String findTodoActionNameByBizType(String bizType){
		return localBizActionMap.get(bizType);
	}
	
	public Object findClassPluginIdByActionName(String className){
		return classIdMap.get(className);
	}
	
}