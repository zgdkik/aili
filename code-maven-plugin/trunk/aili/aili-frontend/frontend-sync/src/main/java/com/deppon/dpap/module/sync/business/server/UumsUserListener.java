package com.deppon.dpap.module.sync.business.server;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.dpap.module.authorization.server.service.IUserService;
import com.deppon.dpap.module.authorization.shared.domain.UserEntity;
import com.deppon.dpap.module.organization.shared.domain.EmployeeEntity;
import com.deppon.dpap.module.sync.business.exception.EmployeeJMSException;
import com.deppon.dpap.module.sync.business.exception.UserJMSException;
import com.deppon.dpap.module.sync.business.jms.SendUserInfoDeptAllProcessReult;
import com.deppon.dpap.module.sync.business.jms.SyncUserInfoRequest;
import com.deppon.dpap.module.sync.business.jms.SyncUserInfoResponse;
import com.deppon.dpap.module.sync.business.jms.UserInfo;
import com.deppon.dpap.module.sync.esb.process.IProcess;
import com.deppon.dpap.module.sync.esb.util.Constant;
import com.deppon.foss.module.frameworkimpl.shared.define.DpapConstants;
import com.deppon.foss.module.frameworkimpl.shared.util.CryptoUtil;
import com.deppon.foss.module.frameworkimpl.shared.util.UUIDUtils;

/**
 * 
 * 用户监听类
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:ztjie,date:2013-8-29 上午10:15:03,content:TODO
 * </p>
 * 
 * @author ztjie
 * @date 2013-8-29 上午10:15:03
 * @since
 * @version
 */
public class UumsUserListener implements IProcess {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UumsUserListener.class);

	private IUserService userService;

	@Override
	public Object process(Object req) {
		LOGGER.info("接收用户信息");
		int successCount = 0, failedCount = 0;
		SyncUserInfoResponse response = new SyncUserInfoResponse();
		SyncUserInfoRequest request = (SyncUserInfoRequest) req;
		try {
			if (request == null || request.getUserList() == null) {
				LOGGER.info("接收到的SyncUserInfoRequest对象为空");
				throw new EmployeeJMSException(
						"接收到的SyncUserInfoRequest对象为空");
			}
			for (UserInfo userInfo : request.getUserList()) {
				try {
					UserEntity userEntity = null;
					userEntity = this
							.vaidationUUMSDataObjectIntoNetWork(userInfo);
					// 判断是否是新增 还是反聘
					if (Constant.NUMERAL_S_ONE.equalsIgnoreCase(userInfo
							.getChangeType())
							|| Constant.NUMERAL_S_FOUR
									.equalsIgnoreCase(userInfo.getChangeType())) {
						if (null == userEntity) {
							userEntity = new UserEntity();
							userEntity.setId(UUIDUtils.getUUID());
							// 只有做新增或返聘动作时才同步用户密码
							userEntity.setPassword(CryptoUtil
									.digestByMD5(userInfo.getDesPassword()));
						} else {
							throw new UserJMSException(UserJMSException.USER_ADD_EXIT);
						}
					} else {
						if (null == userEntity) {
							throw new UserJMSException(
									EmployeeJMSException.DATA_RULE_REASON_OBJECTISNULL_ERROR);
						} else {
							// 这里不做处理
						}
					}
					// 职员编码
					if (StringUtils.isBlank(userInfo.getEmpCode())) {
						throw new UserJMSException(UserJMSException.USER_EMPCODE_NOT_EXIT);
					}
					userEntity.setUserName(userInfo.getEmpCode());
					EmployeeEntity emp = new EmployeeEntity();
					emp.setEmpCode(userInfo.getEmpCode());
					userEntity.setEmpEntity(emp);
					userEntity.setPassword(userInfo.getDesPassword());
					// 最近 登陆时间
					userEntity.setLastLogin(new Date());
					// // 创建人
					userEntity.setCreateUser(userInfo.getUserName());
					// // 创建时间
					userEntity.setCreateDate(new Date());
					// 最后修改人
					userEntity.setModifyUser(userInfo.getUserName());
					userEntity.setModifyDate(new Date());
					userEntity.setBeginTime(new Date());
					userEntity.setEndTime(new Date());
					// 用户状态
					userEntity
							.setActive((userInfo.isIsActive() == true ? DpapConstants.ACTIVE
									: DpapConstants.INACTIVE));

					if (Constant.NUMERAL_S_ONE.equalsIgnoreCase(userInfo
							.getChangeType())
							|| Constant.NUMERAL_S_FOUR
									.equalsIgnoreCase(userInfo.getChangeType())) {
						// 根据同步的动作执行对应的"新增"或"返聘"
						userService.save(userEntity, userInfo.getRoleInfo());

					} else if (Constant.NUMERAL_S_THREE
							.equalsIgnoreCase(userInfo.getChangeType())) {
						// 根据同步的动作执行对应的"删除"
						userEntity.setActive(DpapConstants.INACTIVE);
						userService.update(userEntity, null);

					} else if (Constant.NUMERAL_S_TWO.equalsIgnoreCase(userInfo
							.getChangeType())) {
						// 根据同步的动作执行对应的"修改"
						userService.update(userEntity, userInfo.getRoleInfo());
					} else {
						throw new UserJMSException(
								Constant.DATA_RULE_OPERATE_ERROR);
					}
					successCount++;
					response.setSuccessCount(successCount);
				} catch (UserJMSException e) {
					LOGGER.error(e.getErrorCode(), e);
					failedCount++;
					SendUserInfoDeptAllProcessReult detal = new SendUserInfoDeptAllProcessReult();
					detal.setAccountChangeId(userInfo.getAccountChangeId());
					detal.setResult(false);
					detal.setReason(e.getErrorCode());
					response.getProcessResult().add(detal);
					response.setFailedCount(failedCount);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			SendUserInfoDeptAllProcessReult detal = new SendUserInfoDeptAllProcessReult();
			detal.setAccountChangeId(null);
			detal.setResult(false);
			detal.setReason(e.getMessage());
			response.setSuccessCount(successCount);
			response.getProcessResult().add(detal);
			response.setFailedCount(failedCount);
		}
		LOGGER.info("接收用户结束");
		return response;
	}

	private UserEntity vaidationUUMSDataObjectIntoNetWork(UserInfo userInfo) {
		return userService.findByLoginName(userInfo.getEmpCode());
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
