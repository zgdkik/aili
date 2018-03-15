package com.deppon.dpap.module.sync.server;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.dpap.module.organization.server.service.IDepartmentService;
import com.deppon.dpap.module.organization.shared.domain.DepartmentEntity;
import com.deppon.dpap.module.sync.business.jms.AdminOrgInfo;
import com.deppon.dpap.module.sync.business.jms.SyncAdminOrgRequest;
import com.deppon.dpap.module.sync.business.transfer.SyncOrganizationRequestTrans;
import com.deppon.dpap.module.sync.esb.header.ESBHeader;
import com.deppon.dpap.module.sync.esb.sender.DefaultSender;
import com.deppon.dpap.module.sync.esb.transfer.IMessageTransform;

public class TestUumsAdminOrgListener {
	ApplicationContext context = null;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("spring-jms.xml");
	}

	AdminOrgInfo changEntity(DepartmentEntity entry, AdminOrgInfo entity) {
		entity.setOrgId(entry.getId());
		if (StringUtils.isNotBlank(entry.getDeptCode())) {
			// 组织编码
			entity.setOrgCode(entry.getDeptCode().trim());
		} else {
			// 组织编码
			entity.setOrgCode(entry.getDeptCode());
		}

		// 组织名称
		entity.setOrgName(entry.getDeptName());
		if (StringUtils.isNotBlank(entry.getUnifiedCode())) {
			// 组织标杆编码
			entity.setOrgBenchmarkCode(entry.getUnifiedCode().trim());
		} else {
			// 组织标杆编码
			entity.setOrgBenchmarkCode(entry.getUnifiedCode());
		}
		// 组织状态, 0-正常, 1-待撤销, 2-已撤销
		entity.setOrgStatus(entry.getStatus());
		if (StringUtils.isNotBlank(entry.getParentEntity().getDeptCode())) {
			// 上级组织编码
			entity.setParentOrgCode(entry.getParentEntity().getDeptCode());
		} else {
			// 上级组织编码
			entity.setParentOrgCode(entry.getParentEntity().getDeptCode());
		}
		// 是否为叶子节点
		entity.setIsLeaf(entry.getIsLeaf().equals("1") ? true : false);
		// 已封存系统
		entity.setCanceledSystems(entry.getCanceledSystems());
		if (StringUtils.isNotBlank(entry.getEhrDeptCode())) {
			// EHR部门编码
			entity.setEhrDeptCode(entry.getEhrDeptCode().trim());
		} else {
			// EHR部门编码
			entity.setEhrDeptCode(entry.getEhrDeptCode());
		}
		return entity;
	}

	@Test
	public void testUpdate() throws Exception {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		// 初始化消息头
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessId("bus00001");
		esbHeader.setEsbServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_DEPARTMENT");
		esbHeader.setBackServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_DEPARTMENT");
		esbHeader.setExchangePattern(2);
		esbHeader.setMessageFormat("XML");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setSourceSystem("UUMS");
		esbHeader.setVersion("1.0");
		IMessageTransform<SyncAdminOrgRequest> messageTransform = (SyncOrganizationRequestTrans) context
				.getBean("syncOrganizationRequestTrans");

		SyncAdminOrgRequest deptRequest = new SyncAdminOrgRequest();

		List<AdminOrgInfo> adminOrgInfos = new ArrayList<AdminOrgInfo>();
		IDepartmentService departmentService = (IDepartmentService) context
				.getBean("departmentService");
		DepartmentEntity dept = departmentService.queryByCode("W01");
		// IUserDeptAuthorityService userDeptAuthorityService =
		// (IUserDeptAuthorityService)
		// context.getBean("userDeptAuthorityService");
		// List<String> oldDeptAuthList =
		// userDeptAuthorityService.queryUserDeptDataByCode("000000");
		// List<String> oldDeptAuthList1 =
		// userDeptAuthorityService.queryUserDeptDataByCode("089115");
		AdminOrgInfo adminOrgInfo = new AdminOrgInfo();
		adminOrgInfo = changEntity(dept, adminOrgInfo);
		// W101 W01130306
		adminOrgInfo.setParentOrgCode("W01");
		adminOrgInfo.setOrgCode("W0111");
		adminOrgInfos.add(adminOrgInfo);
		deptRequest.setAdminOrgInfo(adminOrgInfos);
		String textMessage = messageTransform.fromMessage(deptRequest);
		jmsSender.sendJms(esbHeader, textMessage);
		System.out.println(textMessage);
	}

	@Test
	public void testAdd() throws Exception {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessId("bus00001");
		esbHeader.setEsbServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_DEPARTMENT");
		esbHeader.setBackServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_DEPARTMENT");
		esbHeader.setExchangePattern(2);
		esbHeader.setMessageFormat("XML");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setSourceSystem("UUMS");
		esbHeader.setVersion("1.0");

		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		SyncAdminOrgRequest req = new SyncAdminOrgRequest();
		List<AdminOrgInfo> adminOrgInfos = new ArrayList<AdminOrgInfo>();
		AdminOrgInfo adminOrgInfo = new AdminOrgInfo();
		IDepartmentService departmentService = (IDepartmentService) context
				.getBean("departmentService");
		DepartmentEntity dept = departmentService.queryByCode("W1011122");
		adminOrgInfo = changEntity(dept, adminOrgInfo);

		adminOrgInfo.setParentOrgCode("W1011122");
		adminOrgInfo.setOrgCode("W10111221");
		adminOrgInfo.setOrgName("测试部门1");
		adminOrgInfo.setOrgBenchmarkCode("deppon123456");
		adminOrgInfo.setOrgId("G-135246");
		adminOrgInfos.add(adminOrgInfo);
		req.setAdminOrgInfo(adminOrgInfos);
		IMessageTransform<SyncAdminOrgRequest> messageTransform = (SyncOrganizationRequestTrans) context
				.getBean("syncOrganizationRequestTrans");
		String textMessage = messageTransform.fromMessage(req);
		jmsSender.sendJms(esbHeader, textMessage);
		System.out.println(textMessage);
	}

}
