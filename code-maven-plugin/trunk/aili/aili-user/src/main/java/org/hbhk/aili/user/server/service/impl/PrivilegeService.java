package org.hbhk.aili.user.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.hbhk.aili.user.server.cache.InvalidateAuthCache;
import org.hbhk.aili.user.server.dao.IFunctionDao;
import org.hbhk.aili.user.server.dao.IRoleFunctionDao;
import org.hbhk.aili.user.server.dao.IUserRoleDao;
import org.hbhk.aili.user.server.service.IPrivilegeService;
import org.hbhk.aili.user.share.entity.PrivilegeEntity;
import org.hbhk.aili.user.share.entity.RolePrivilegeEntity;
import org.hbhk.aili.user.share.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class PrivilegeService implements IPrivilegeService {

	@Autowired
	private IFunctionDao functionDao;

	@Autowired
	private IRoleFunctionDao roleFunctionDao;

	@Autowired
	private IUserRoleDao userRoleDao;

	/*
	 * 查询权限菜单信息
	 */
	@Transactional(readOnly = true)
	@Override
	public List<PrivilegeEntity> getMenuService(Map<String, Object> map) {
		return functionDao.get(map);
	}

	/*
	 * 新增菜单信息
	 */
	@Override
	public int addMenuService(PrivilegeEntity privilegeEntity) {
		int num = functionDao.insert(privilegeEntity);
		if (num < 0) {
			throw new BusinessException("新增失败");
		}
		return 1;
	}

	/*
	 * 更新菜单信息
	 */
	@Override
	public int updateMenuService(PrivilegeEntity privilegeEntity) {
		int result = functionDao.update(privilegeEntity);
		if (result == 1) {
			invalidateUserCache(privilegeEntity.getPrivilegeCode());
		}
		if (result < 0) {
			throw new BusinessException("修改失败");
		}
		return result;
	}

	public void invalidateUserCache(String functionCode) {
		RolePrivilegeEntity rf = new RolePrivilegeEntity();
		rf.setFunctionCode(functionCode);
		List<RolePrivilegeEntity> rfList = roleFunctionDao.get(BeanToMapUtil
				.convert(rf));
		if (rfList != null && rfList.size() > 0) {
			List<String> roleCodeList = new ArrayList<>();
			for (RolePrivilegeEntity rf1 : rfList) {
				roleCodeList.add(rf1.getRoleCode());
			}
			if (roleCodeList.size() > 0) {
				for (String rc : roleCodeList) {
					UserRoleEntity ur = new UserRoleEntity();
					ur.setRoleCode(rc);
					// 查询出用户刷新缓存
					List<UserRoleEntity> urList = userRoleDao.get(BeanToMapUtil
							.convert(ur));
					if (urList != null && urList.size() > 0) {
						for (UserRoleEntity userRole : urList) {
							InvalidateAuthCache.invalidateUserCache(userRole
									.getUserCode());
						}
					}
				}
			}

		}
	}

	/*
	 * 删除菜单信息
	 */
	@Override
	public int deleteMenuService(String id) {
		PrivilegeEntity privilegeEntity = functionDao.getById(id);
		if (privilegeEntity == null) {
			throw new BusinessException("删除的权限已经不存在了");
		}
		int result = functionDao.deleteById(id);
		if (result == 1) {
			invalidateUserCache(privilegeEntity.getPrivilegeCode());
		}
		return result;
	}

	/*
	 * 根据Id查询权限菜单信息
	 */
	@Transactional(readOnly = true)
	@Override
	public PrivilegeEntity getMenu(Map<String, Object> map) {
		List<PrivilegeEntity> list = functionDao.get(map);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/*
	 * 根据编码查询该编码下的角色
	 */
	@Transactional(readOnly = true)
	@Override
	public int getCountByFunctionCodeService(String functionCode) {
		// TODO Auto-generated method stub
		return functionDao.getCountByFunctionCode(functionCode);
	}

}
