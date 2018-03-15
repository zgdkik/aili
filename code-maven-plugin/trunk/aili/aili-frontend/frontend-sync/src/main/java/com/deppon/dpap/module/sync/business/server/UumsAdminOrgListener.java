package com.deppon.dpap.module.sync.business.server;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.dpap.module.authorization.server.service.IUserDeptAuthorityService;
import com.deppon.dpap.module.authorization.shared.dto.UserDeptDataDto;
import com.deppon.dpap.module.context.DateUtils;
import com.deppon.dpap.module.organization.server.service.IDepartmentService;
import com.deppon.dpap.module.organization.shared.domain.DepartmentEntity;
import com.deppon.dpap.module.sync.business.exception.DeptJMSException;
import com.deppon.dpap.module.sync.business.jms.AdminOrgInfo;
import com.deppon.dpap.module.sync.business.jms.SyncAdminOrgProcessReult;
import com.deppon.dpap.module.sync.business.jms.SyncAdminOrgRequest;
import com.deppon.dpap.module.sync.business.jms.SyncAdminOrgResponse;
import com.deppon.dpap.module.sync.esb.process.IProcess;
import com.deppon.foss.module.frameworkimpl.shared.define.DpapConstants;
import com.deppon.foss.module.frameworkimpl.shared.util.UUIDUtils;

/**
 * 
 * 部门组织信息同步监听
 * 
 * @author YangBin
 * @date 2013-8-9 下午12:14:03
 */
public class UumsAdminOrgListener implements IProcess {
	// 日志记录
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UumsAdminOrgListener.class);

	// 直接调用底层角色service
	private IDepartmentService departmentService;

	private IUserDeptAuthorityService userDeptAuthorityService;

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IUserDeptAuthorityService getUserDeptAuthorityService() {
		return userDeptAuthorityService;
	}

	public void setUserDeptAuthorityService(
			IUserDeptAuthorityService userDeptAuthorityService) {
		this.userDeptAuthorityService = userDeptAuthorityService;
	}

	/**
	 * @部门组织信息接受信息
	 * @author YangBin
	 * @date 2013-8-14 下午5:00:04
	 * @see com.deppon.network.esb.process.IProcess#process(java.lang.Object)
	 */
	@Override
	public Object process(Object req) {
		LOGGER.info("接收部门信息开始");
		SyncAdminOrgResponse response = new SyncAdminOrgResponse();
		int successCount = 0, failedCount = 0;

		SyncAdminOrgRequest request = (SyncAdminOrgRequest) req;
		for (AdminOrgInfo entry : request.getAdminOrgInfo()) {
			{
				try {
					// entry对象非空判断
					if (entry == null) {
						// 返回为空
						return null;
					}
					// 验证参数的合法性
					if (StringUtils.equals(entry.getOrgCode(),
							entry.getParentOrgCode())) {
						// 抛出异常信息
						throw new DeptJMSException(
								"组织编码OrgCode与组织的上组织编码ParentOrgCode相同");
					}
					// 三目运算符判断参数是否为空，为空则声明新对象
					DepartmentEntity addNewEntity = new DepartmentEntity();
					addNewEntity.setId(UUIDUtils.getUUID());
					addNewEntity = changEntity(entry, addNewEntity);

					// 判断标杆编码是否为空，如果为空，不做任何操作
					if (StringUtils.isNotEmpty(entry.getOrgId())
							&& StringUtils.isNotEmpty(entry
									.getOrgBenchmarkCode())) {
						DepartmentEntity info = new DepartmentEntity();
						info.setUnifiedCode(entry.getOrgBenchmarkCode());
						// 根据标杆编码查询
						List<DepartmentEntity> list = departmentService
								.queryAll(info);
						// 判断是否已经存在，如果存在，则更新，否则新增
						if (CollectionUtils.isNotEmpty(list)) {
							for (DepartmentEntity updateEntity : list) {
								String parentCode = updateEntity
										.getParentEntity().getDeptCode()
										.toString();
								updateEntity = changEntity(entry, updateEntity);
								updateEntity.setModifyDate(new Date());
								departmentService.updateById(updateEntity);
								if (!updateEntity.getParentEntity()
										.getDeptCode().equals(parentCode)) {
									userDeptAuthorityService
											.refreshUserDeptDataByOrgCode(updateEntity
													.getDeptCode());
									departmentService
											.refreshDepartmentByOrgCode(updateEntity
													.getDeptCode());
									List<UserDeptDataDto> userDeptDatas = userDeptAuthorityService
											.queryAllUserDeptData();
									String[] userCodes = new String[userDeptDatas
											.size()];
									for (int i = 0; i < userDeptDatas.size(); i++) {
										userCodes[i] = userDeptDatas.get(i)
												.getUserCode();
									}
									// 失效数据权限缓存
									userDeptAuthorityService
											.invalidMulUserDeptAuth(userCodes);
								}
							}
						} else {
							addNewEntity.setCreateDate(new Date());
							departmentService.add(addNewEntity);
							// 添加部门时 刷部门权限
							userDeptAuthorityService
									.refreshUserDeptDataByOrgCode(addNewEntity
											.getDeptCode());
							departmentService
									.refreshDepartmentByOrgCode(addNewEntity
											.getDeptCode());
							List<UserDeptDataDto> userDeptDatas = userDeptAuthorityService
									.queryAllUserDeptData();
							String[] userCodes = new String[userDeptDatas
									.size()];
							for (int i = 0; i < userDeptDatas.size(); i++) {
								userCodes[i] = userDeptDatas.get(i)
										.getUserCode();
							}
							// 失效数据权限缓存
							userDeptAuthorityService
									.invalidMulUserDeptAuth(userCodes);
						}
					}
					successCount++;
					response.setSuccessCount(successCount);
				} catch (DeptJMSException e) {
					LOGGER.error(e.getErrorCode(), e);
					failedCount++;
					SyncAdminOrgProcessReult detal = new SyncAdminOrgProcessReult();
					detal.setOrgChangeId(entry.getOrgChangeId());
					detal.setOrgName(entry.getOrgName());
					detal.setOrgBenchmarkCode(entry.getOrgBenchmarkCode());
					detal.setChangeType(entry.getChangeType());
					detal.setResult(false);
					detal.setReason(e.getErrorCode());
					response.getProcessResult().add(detal);
					response.setFailedCount(failedCount);
				}

			}
		}
		LOGGER.info("接收部门信息结束");
		return response;
	}

	private DepartmentEntity changEntity(AdminOrgInfo entry,
			DepartmentEntity entity) {
		if (StringUtils.isNotBlank(entry.getOrgCode())) {
			// 组织编码
			entity.setDeptCode(entry.getOrgCode().trim());
		} else {
			// 组织编码
			entity.setDeptCode(entry.getOrgCode());
		}

		// 组织名称
		entity.setDeptName(entry.getOrgName());
		if (StringUtils.isNotBlank(entry.getOrgBenchmarkCode())) {
			// 组织标杆编码
			entity.setUnifiedCode(entry.getOrgBenchmarkCode().trim());
		} else {
			// 组织标杆编码
			entity.setUnifiedCode(entry.getOrgBenchmarkCode());
		}

		// 组织负责人（工号）
		entity.setPrincipalNo(entry.getOrgManager());
		// 组织联系电话
		entity.setDeptTelephone(entry.getOrgPhone());
		// 组织传真号码
		entity.setDeptFax(entry.getOrgFax());
		if (StringUtils.isNotBlank(entry.getParentOrgBenchmarkCode())) {
			// 上级组织标杆编码
			entity.setParentOrgUnifiedCode(entry.getParentOrgBenchmarkCode()
					.trim());
		} else {
			// 上级组织标杆编码
			entity.setParentOrgUnifiedCode(entry.getParentOrgBenchmarkCode());
		}
		// 所属子公司名称
		entity.setSubCompName(entry.getSubCompName());
		if (StringUtils.isNotBlank(entry.getSubCompCode())) {
			// 所属子公司编码
			entity.setSubsidiaryCode(entry.getSubCompCode().trim());
		} else {
			// 所属子公司编码
			entity.setSubsidiaryCode(entry.getSubCompCode());
		}
		// 组织地址
		entity.setAddress(entry.getOrgAddress());
		// 组织状态, 0-正常, 1-待撤销, 2-已撤销
		entity.setStatus(entry.getOrgStatus());
		// 是否启用
		entity.setActive(entry.getOrgStatus().equals("0") ? DpapConstants.ACTIVE
				: DpapConstants.INACTIVE);
		// 启用日期
		entity.setBeginTime(DateUtils.convertToDate(entry.getValidDate()));
		// 作废日期
		entity.setEndTime(DateUtils.convertToDate(entry.getInvalidDate()));
		if (StringUtils.isNotBlank(entry.getCostCenterCode())) {
			// 所属成本中心编码
			entity.setCostCenterCode(entry.getCostCenterCode().trim());
		} else {
			// 所属成本中心编码
			entity.setCostCenterCode(entry.getCostCenterCode());
		}
		// 所属成本中心名字
		entity.setCostCenterName(entry.getCostCenterName());

		// 是否事业部
		entity.setIsDivision(entry.isIsCareerDept() ? DpapConstants.YES
				: DpapConstants.NO);
		// 是否大区
		entity.setIsBigRegion(entry.isIsBigArea() ? DpapConstants.YES
				: DpapConstants.NO);
		// 是否小区
		entity.setIsSmallRegion(entry.isIsSmallArea() ? DpapConstants.YES
				: DpapConstants.NO);

		// 部门简称
		entity.setOrgSimpleName(entry.getDeptShortName());
		// 部门备注描述信息
		entity.setDeptDesc(entry.getOrgDesc());
		DepartmentEntity parentDept = new DepartmentEntity();
		if (StringUtils.isNotBlank(entry.getParentOrgCode())) {
			// 上级组织编码
			parentDept.setDeptCode(entry.getParentOrgCode().trim());
		} else {
			// 上级组织编码
			parentDept.setDeptCode("");
		}
		entity.setParentEntity(parentDept);

		// UUMS主键ID
		entity.setUumsId(entry.getOrgId());
		// UUMS上级主键ID
		entity.setUumsParentId(entry.getParentOrgId());
		// UUMS主键ID序列
		entity.setUumsIds(entry.getOrgSeq());
		// 是否为叶子节点
		entity.setIsLeaf(entry.isIsLeaf() ? "1" : "0");
		// 显示顺序
		entity.setDisplayOrder(entry.getDisplayOrder() == null ? 0
				: new Integer(entry.getDisplayOrder()));
		// 部门层级
		entity.setDeptLevel(entry.getOrgLevel() == null ? 0 : new Integer(entry
				.getOrgLevel()));
		// 地区编码默认拼音
		entity.setAreaCode(entry.getAreaCode());
		// 组织邮箱
		entity.setDeptEmail(entry.getOrgEmail());
		// 邮编号码
		entity.setZipCode(entry.getOrgZipCode());
		// 组织性质
		entity.setDeptAttribute(entry.getOrgAttribute());
		// 已封存系统
		entity.setCanceledSystems(entry.getCanceledSystems());
		if (StringUtils.isNotBlank(entry.getEhrDeptCode())) {
			// EHR部门编码
			entity.setEhrDeptCode(entry.getEhrDeptCode().trim());
		} else {
			// EHR部门编码
			entity.setEhrDeptCode(entry.getEhrDeptCode());
		}
		// 省份
		entity.setProvCode(entry.getOrgProvince());
		// 城市
		entity.setCityCode(entry.getOrgCity());
		// 区县
		entity.setCountyCode(entry.getOrgCountry());
		// 变动类型, 1-新增, 2-修改, 3-异动, 4-待撤销, 5-已撤销
		entity.setChangeType(entry.getChangeType());
		// 变动时间
		entity.setChangeDate(DateUtils.convertToDate(entry.getChangeDate()));
		return entity;
	}

	/**
	 * 下面是工具方法
	 */
	public static String boolToYesNo(boolean yes) {
		// 三目运算符判断
		return yes ? DpapConstants.YES : DpapConstants.NO;
	}

}
