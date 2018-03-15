package com.deppon.dpap.module.sync.business.server;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.dpap.module.authorization.server.service.IRoleService;
import com.deppon.dpap.module.authorization.shared.domain.RoleEntity;
import com.deppon.dpap.module.context.NumberConstants;
import com.deppon.dpap.module.sync.business.exception.RoleJMSException;
import com.deppon.dpap.module.sync.business.jms.SyncRoleDetail;
import com.deppon.dpap.module.sync.business.jms.SyncRoleRequest;
import com.deppon.dpap.module.sync.business.jms.SyncRoleResponse;
import com.deppon.dpap.module.sync.esb.process.IProcess;
import com.deppon.dpap.module.sync.esb.util.Constant;
import com.deppon.foss.module.frameworkimpl.shared.define.DpapConstants;

/**
 * 
 * 用户角色监听
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:ztjie,date:2013-8-29 上午10:14:36,content:TODO
 * </p>
 * 
 * @author ztjie
 * @date 2013-8-29 上午10:14:36
 * @since
 * @version
 */
public class UumsRoleListener implements IProcess {
	// 日志加载信息service
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UumsRoleListener.class);

	// 直接调用底层角色service
	private IRoleService roleService;

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 
	 * 接收用户信息
	 * 
	 * @author YangBin
	 * @date 2013-8-14 下午6:04:27
	 */
	@Override
	public Object process(Object req) {
		LOGGER.info("接收角色信息开始");
		int successCount = 0, failedCount = 0;
		SyncRoleResponse response = new SyncRoleResponse();
		SyncRoleRequest request = (SyncRoleRequest) req;
		try {
			// 判断转换用户角色是否为空
			if (null == null) {
				SyncRoleDetail detail = new SyncRoleDetail();
				detail.setReason("角色信息为空");
				detail.setResult(0);
				detail.setRoleid(request.getRoleid());
				response.setFailCount(failedCount);
				response.setDetails(detail);
				return response;
			}
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setId(request.getRoleid());
			roleEntity.setRoleCode(request.getRoleStandardNumber().trim());
			roleEntity.setRoleName(request.getRoleName().trim());
			roleEntity
					.setCreateUser(Constant.EXTERNAL_SYSTEM_UUMS_USER_ACCOUNT);
			roleEntity.setCreateDate(new Date());
			// 新增
			if (NumberConstants.NUMERAL_S_ONE.equalsIgnoreCase(request
					.getOperateType().trim())) {
				roleService.save(roleEntity, null);
			}
			// 删除
			else if (NumberConstants.NUMERAL_S_THREE.equalsIgnoreCase(request
					.getOperateType().trim())) {
				roleEntity
						.setModifyUser(Constant.EXTERNAL_SYSTEM_UUMS_USER_ACCOUNT);
				roleEntity.setActive(DpapConstants.INACTIVE);
				roleService.removeByCode(roleEntity);
			}
			// 更新
			else if (NumberConstants.NUMERAL_S_TWO.equalsIgnoreCase(request
					.getOperateType().trim())) {
				roleEntity
						.setModifyUser(Constant.EXTERNAL_SYSTEM_UUMS_USER_ACCOUNT);
				roleService.updateRoleFunction(roleEntity);
			}
			successCount++;
			response.setSucessCount(successCount);
		} catch (RoleJMSException e) {
			LOGGER.error(e.getErrorCode());
			failedCount++;
			SyncRoleDetail detail = new SyncRoleDetail();
			detail.setReason(e.getErrorCode());
			detail.setResult(0);
			detail.setRoleid(request.getRoleid());
			response.setFailCount(failedCount);
			response.setDetails(detail);
		}
		LOGGER.info("接收角色信息结束");
		return response;
	}

}
