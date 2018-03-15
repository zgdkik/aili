package org.hbhk.aili.user.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.user.share.entity.PrivilegeEntity;
import org.hbhk.aili.user.share.vo.MenuVo;

public interface IFunctionDao extends IBaseDao<PrivilegeEntity, String> {
	
	
	/**
	 * 
	* @Title: getFunctionListByCodes 
	* @Description: 根据权限编码查询
	* @param @param functionCodes
	* @param @return 
	* @return List<FunctionEntity>    返回类型 
	* @author hbhk  
	* @throws
	 */
	List<PrivilegeEntity> getFunctionListByUris(@Param("uris") String... uris);
	
	/**
	 * 
	* @Title: getFunctionListByCodes 
	* @Description: 根据权限编码查询该编码下的角色数量
	* @param @param functionCodes
	* @param @return 
	* @return int    返回类型 
	* @author hbhk  
	* @throws
	 */
	int getCountByFunctionCode(String functionCode);
	
	//List<MenuVo> getMenuTree(@Param("code")String code,@Param("uris") String... uris );

	List<MenuVo> getMenuTree(Map<String, Object> params);
	
	List<MenuVo> getUserMenuTree(Map<String, Object> params);
}
