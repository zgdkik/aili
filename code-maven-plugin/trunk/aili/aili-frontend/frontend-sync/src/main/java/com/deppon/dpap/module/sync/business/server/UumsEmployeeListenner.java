package com.deppon.dpap.module.sync.business.server;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.dpap.module.context.DateUtils;
import com.deppon.dpap.module.organization.server.service.IEmployeeService;
import com.deppon.dpap.module.organization.shared.domain.DepartmentEntity;
import com.deppon.dpap.module.organization.shared.domain.EmployeeEntity;
import com.deppon.dpap.module.sync.business.exception.EmployeeJMSException;
import com.deppon.dpap.module.sync.business.jms.EmployeeInfo;
import com.deppon.dpap.module.sync.business.jms.SendEmployeeProcessReult;
import com.deppon.dpap.module.sync.business.jms.SendEmployeeResponse;
import com.deppon.dpap.module.sync.business.jms.SyncEmployeeRequest;
import com.deppon.dpap.module.sync.esb.process.IProcess;
import com.deppon.dpap.module.sync.esb.util.Constant;
import com.deppon.foss.module.frameworkimpl.shared.define.DpapConstants;
import com.deppon.foss.module.frameworkimpl.shared.util.UUIDUtils;

/**
 * 
 * 职员监听类
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:ztjie,date:2013-8-29 上午10:14:43,content:TODO
 * </p>
 * 
 * @author ztjie
 * @date 2013-8-29 上午10:14:43
 * @since
 * @version
 */
public class UumsEmployeeListenner implements IProcess {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UumsEmployeeListenner.class);

	private IEmployeeService employeeService;

	@Override
	public Object process(Object req) {
		LOGGER.info("接收员工信息 开始");
		int successCount = 0, failedCount = 0;
		SendEmployeeResponse response = new SendEmployeeResponse();
		SyncEmployeeRequest request = (SyncEmployeeRequest) req;
		try {
			if (request == null || request.getEmployeeInfo() == null  || request.getEmployeeInfo().size() == 0 ) {
				LOGGER.info("接收到的SyncEmployeeRequest对象为空");
				throw new EmployeeJMSException(
						"接收到的SyncEmployeeRequest对象为空");
			}
			for (EmployeeInfo employeeInfo : request.getEmployeeInfo()) {
				try {
					EmployeeEntity employeeEntity = null;
					employeeEntity = this
							.vaidationUUMSDataObjectIntoNetWork(employeeInfo);
					// 新增类型或者返聘类型
					if (Constant.NUMERAL_S_ONE.equalsIgnoreCase(employeeInfo
							.getChangeType())
							|| Constant.NUMERAL_S_FOUR
									.equalsIgnoreCase(employeeInfo
											.getChangeType())) {
						if (null == employeeEntity) {
							employeeEntity = new EmployeeEntity();
							employeeEntity.setId(UUIDUtils.getUUID());
						} else {
							throw new EmployeeJMSException(
									EmployeeJMSException.DATA_RULE_REASON_OBJECTISNOTNULL_ERROR);
						}
					} else {
						if (null == employeeEntity) {
							throw new EmployeeJMSException(
									EmployeeJMSException.DATA_RULE_REASON_OBJECTISNULL_ERROR);
						} else {
							// 这里不做处理
						}
					}

					// 职员编码
					employeeEntity.setEmpCode(employeeInfo.getEmployeeCode());
					// 职员姓名
					employeeEntity.setEmpName(employeeInfo.getEmployeeName());
					// 性别
					employeeEntity.setGender(employeeInfo.getEmployeeName()
							.equals("女") ? false : true);// employee中的gender为String类型，employeeEntity为Integer
					// 职位
					employeeEntity.setPosition(employeeInfo.getPosition());
					// 出生日期
					employeeEntity.setBirthDate(DateUtils
							.convertToDate(employeeInfo.getBirthday()));
					// 状态（是否离职 ）
					employeeEntity.setStatus(employeeInfo.getStatus().equals(
							"0") ? false : true);
					// 入职日期
					employeeEntity.setInDate(DateUtils
							.convertToDate(employeeInfo.getEntryDate()));
					// 离职日期
					employeeEntity.setOutDate(DateUtils
							.convertToDate(employeeInfo.getDepartureDate()));
					// 办公电话
					employeeEntity.setOfferTel(employeeInfo.getOfficeTel());
					// 办公地址
					employeeEntity.setOfferAddress(employeeInfo
							.getOfficeAddress());
					// 办公邮编
					employeeEntity.setOfferZipCode(employeeInfo
							.getOfficeZipCode());
					// 办公邮件
					employeeEntity.setOfferEmail(employeeInfo.getOfficeEmail());
					// 手机号码
					employeeEntity.setMobileNumber(employeeInfo.getMobile());
					// 家庭电话
					employeeEntity.setHomeTel(employeeInfo.getHomeTel());
					// 家庭地址
					employeeEntity
							.setHomeAddress(employeeInfo.getHomeAddress());
					// 家庭邮编
					employeeEntity
							.setHomeZipCode(employeeInfo.getHomeZipCode());
					// 私人邮编
					employeeEntity.setPersonEmail(employeeInfo
							.getPersonalEmail());
					// 工作描述
					employeeEntity.setWorkExp(employeeInfo.getWorkexp());
					// 备注
					employeeEntity.setRemark(employeeInfo.getRemark());
					// 创建人
					employeeEntity.setCreateUser(Constant.SYSTEM_UUMS);
					// 创建时间
					employeeEntity.setCreateDate(new Date());
					// // 最后修改人
					employeeEntity.setModifyUser(Constant.SYSTEM_UUMS);
					// // 最后修改时间
					employeeEntity.setModifyDate(new Date());
					// 雇员名拼音
					// employeeEntity.setPinyin(employeeInfo.get);
					// 组织标杆编码
					// employeeEntity.setUnifieldCode(employeeInfo.getDeptBenchmarkCode());
					// 身份证号码
					// employeeEntity.setIdentityCard(employeeInfo.getPersonId());
					// 部门编码
					DepartmentEntity dept = new DepartmentEntity();
					dept.setDeptCode(employeeInfo.getDeptCode());
					employeeEntity.setDeptEntity(dept);

					// 是否启用
					employeeEntity.setActive(employeeInfo.getStatus().equals(
							"0") ? DpapConstants.INACTIVE
							: DpapConstants.ACTIVE);

					if (Constant.NUMERAL_S_ONE.equalsIgnoreCase(employeeInfo
							.getChangeType())
							|| Constant.NUMERAL_S_FOUR
									.equalsIgnoreCase(employeeInfo
											.getChangeType())) {
						// 根据同步的动作执行对应的"新增"或"返聘"

						employeeService.add(employeeEntity);

					} else if (Constant.NUMERAL_S_THREE
							.equalsIgnoreCase(employeeInfo.getChangeType())) {
						// 根据同步的动作执行对应的"删除"
						employeeService.delete(employeeEntity);

					} else if (Constant.NUMERAL_S_TWO
							.equalsIgnoreCase(employeeInfo.getChangeType())) {
						// 根据同步的动作执行对应的"修改"
						employeeService.update(employeeEntity);
					} else {
						throw new EmployeeJMSException(
								Constant.DATA_RULE_OPERATE_ERROR);
					}
					successCount++;
					response.setSuccessCount(successCount);
				} catch (EmployeeJMSException e) {
					LOGGER.error(e.getMessage(), e);
					failedCount++;
					SendEmployeeProcessReult detal = new SendEmployeeProcessReult();// SendEmployeeProcessReult
					detal.setEmployeeChangeId(employeeInfo.getEmployeeChangeId());
					detal.setResult(false);
					detal.setReason(e.getErrorCode());
					response.getProcessResult().add(detal);
					response.setFailedCount(failedCount);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			SendEmployeeProcessReult detal = new SendEmployeeProcessReult();// SendEmployeeProcessReult
			detal.setResult(false);
			detal.setReason(e.getMessage());
			response.getProcessResult().add(detal);
			response.setSuccessCount(successCount);
			response.setFailedCount(failedCount);
		}
		LOGGER.info("接收员工信息 结束");
		return response;
	}

	private EmployeeEntity vaidationUUMSDataObjectIntoNetWork(
			EmployeeInfo employeeInfo) {

		return employeeService.queryEmployeeByEmpCode(employeeInfo
				.getEmployeeCode());

	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
