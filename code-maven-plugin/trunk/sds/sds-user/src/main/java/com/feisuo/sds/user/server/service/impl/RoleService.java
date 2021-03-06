package com.feisuo.sds.user.server.service.impl;







import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.mybatis.spring.support.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.vo.QueryPageVo;
import com.feisuo.sds.common.util.UuidUtil;
import com.feisuo.sds.user.server.cache.InvalidateAuthCache;
import com.feisuo.sds.user.server.dao.IRoleDao;
import com.feisuo.sds.user.server.dao.IRoleFunctionDao;
import com.feisuo.sds.user.server.dao.IUserRoleDao;
import com.feisuo.sds.user.server.service.IRoleService;
import com.feisuo.sds.user.share.entity.RoleEntity;
import com.feisuo.sds.user.share.entity.RolePrivilegeEntity;
import com.feisuo.sds.user.share.entity.UserRoleEntity;

@Service
@Transactional
public class RoleService implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IRoleFunctionDao roleFunctionDao ;
	
	@Autowired
	private  IUserRoleDao userRoleDao;
	
	/*
	 * 查询角色信息
	 */

	@Override
	@Transactional(readOnly=true)
	public Pagination<RoleEntity> findRoleList(QueryPageVo queryPageVo) {
		if( queryPageVo==null){
			throw new BusinessException("不允许为空");
		}
		Pagination<RoleEntity> pageInfo = roleDao.getPagination(queryPageVo.getParaMap(),queryPageVo.getPage(),queryPageVo.getSorts());
		return pageInfo;
	}
    
	/*
	 * 新增角色
	 */
	@Override
	public int addRole(RoleEntity roleEntity,String funCodes) {
		String id = roleEntity.getId();
		String roleCode = roleEntity.getRoleCode();
		if(StringUtils.isNotEmpty(id)){
			UserRoleEntity  ur = new UserRoleEntity();
			ur.setRoleCode(roleCode);
			List<UserRoleEntity>  list =  userRoleDao.get(BeanToMapUtil.convert(ur));
			if(list!=null && list.size()>0){
				for (UserRoleEntity userRole : list) {
					InvalidateAuthCache.invalidateUserCache(userRole.getUserCode());
				}
			}
			//先删除 已有角色与权限关系
			roleFunctionDao.deleteByRoleCode(roleCode);
		}
		int num = 0;
		if(StringUtils.isNotEmpty(id)){
			roleEntity.setUpdateTime(new Date());
			roleEntity.setUpdateUser(UserContext.getCurrentUser().getUserName());
			num  = roleDao.update(roleEntity);
			
		}else{
			roleEntity.setCreateTime(new Date());
			roleEntity.setCreateUser(UserContext.getCurrentUser().getUserName());
			roleEntity.setId(UuidUtil.getUuid());
			num  = roleDao.insert(roleEntity);
		}
		if(StringUtils.isNotEmpty(funCodes)){
			String[] codes = funCodes.split(",");
			RolePrivilegeEntity rf = null;
			for (String code : codes) {
				rf = new RolePrivilegeEntity();
				rf.setId(UuidUtil.getUuid());
				rf.setCreateTime(new Date());
				rf.setRoleCode(roleCode);
				rf.setFunctionCode(code);
				roleFunctionDao.insert(rf);
			}
		}
		return num;
	}
	/*
	 * 修改角色信息
	 */
	@Override
	public int updateRole(RoleEntity roleEntity) {
		return roleDao.update(roleEntity);
	}

	/*
	 * 删除角色
	 */
	@Override
	public int deleteRoleById(List<String> id) {
		if(id==null || id.isEmpty()){
		 throw  new BusinessException("删除的id为空");
		}
		UserRoleEntity  ur = new UserRoleEntity();
		RoleEntity role = getRoleById(id.get(0));
		ur.setRoleCode(role.getRoleCode());
		List<UserRoleEntity> list = userRoleDao.get(BeanToMapUtil.convert(ur));
		if(list!=null && !list.isEmpty()){
			 throw  new BusinessException("角色已经存在用户关联不能删除");
		}
		if(list!=null && list.size()>0){
			for (UserRoleEntity userRole : list) {
				InvalidateAuthCache.invalidateUserCache(userRole.getUserCode());
			}
		}
		return roleDao.deleteRoleById(id);
	}
	/*
	 * 根据Id查询角色信息
	 */
	@Transactional(readOnly = true)
	@Override
	public RoleEntity getRoleById(String id) {
		
		return roleDao.getById(id);
	}
	
	public List<RoleEntity> getRoleList(Map<String, Object> paraMap ){
		paraMap.put("status",1);
		List<RoleEntity> list = roleDao.get(paraMap);
		return list;
	}

	@Override
	public List<RolePrivilegeEntity> getRoleFunctionList(String roleCode) {
		if(StringUtils.isEmpty(roleCode)){
			return null;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("roleCode", roleCode);
		return roleFunctionDao.get(params);
	}

	@Override
	public boolean checkRoleCode(String roleCode) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("roleCode", roleCode);
		List<RoleEntity> list = roleDao.get(map);
		return list.size()<1;
	}

	
	  
}
