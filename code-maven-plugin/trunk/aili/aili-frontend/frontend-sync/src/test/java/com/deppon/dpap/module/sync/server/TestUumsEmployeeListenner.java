package com.deppon.dpap.module.sync.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.dpap.module.context.DateUtils;
import com.deppon.dpap.module.sync.business.jms.EmployeeInfo;
import com.deppon.dpap.module.sync.business.jms.SyncEmployeeRequest;
import com.deppon.dpap.module.sync.business.transfer.SyncEmployeeRequestTrans;
import com.deppon.dpap.module.sync.esb.exception.ConvertException;
import com.deppon.dpap.module.sync.esb.header.ESBHeader;
import com.deppon.dpap.module.sync.esb.sender.DefaultSender;

public class TestUumsEmployeeListenner {

	ApplicationContext context = null;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("spring-jms.xml");
	}

	@Test
	public void testAdd() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncEmployeeRequestTrans empReqTr = new SyncEmployeeRequestTrans();
		SyncEmployeeRequest req = new SyncEmployeeRequest();
		List<EmployeeInfo> emps = new ArrayList<EmployeeInfo>();

		EmployeeInfo emp = new EmployeeInfo();
		emp.setEmployeeCode("089115");
		emp.setDeptCode("W01");
		emp.setChangeType("1");
		emp.setEmployeeName("何波");
		emp.setGender("男");
		emp.setPosition("员工");
		emp.setBirthday(DateUtils.convertToXMLGregorianCalendar(new Date()));
		emp.setStatus("1");
		emp.setEntryDate(DateUtils.convertToXMLGregorianCalendar(new Date()));
		emp.setDepartureDate(DateUtils
				.convertToXMLGregorianCalendar(new Date()));
		emps.add(emp);
		req.setEmployeeInfo(emps);
		String body = empReqTr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	@Test
	public void testUP() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncEmployeeRequestTrans empReqTr = new SyncEmployeeRequestTrans();
		SyncEmployeeRequest req = new SyncEmployeeRequest();
		List<EmployeeInfo> emps = new ArrayList<EmployeeInfo>();

		EmployeeInfo emp = new EmployeeInfo();
		emp.setEmployeeCode("089115");
		emp.setDeptCode("W01");
		emp.setChangeType("2");
		emp.setEmployeeName("何波");
		emp.setGender("男");
		emp.setPosition("员工");
		emp.setBirthday(DateUtils.convertToXMLGregorianCalendar(new Date()));
		emp.setStatus("1");
		emp.setEntryDate(DateUtils.convertToXMLGregorianCalendar(new Date()));
		emp.setDepartureDate(DateUtils
				.convertToXMLGregorianCalendar(new Date()));
		emps.add(emp);
		req.setEmployeeInfo(emps);
		String body = empReqTr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	@Test
	public void testDel() throws ConvertException {
		DefaultSender jmsSender = (DefaultSender) context.getBean("jmsSender");
		ESBHeader esbHeader = getESBHeader();
		SyncEmployeeRequestTrans empReqTr = new SyncEmployeeRequestTrans();
		SyncEmployeeRequest req = new SyncEmployeeRequest();
		List<EmployeeInfo> emps = new ArrayList<EmployeeInfo>();

		EmployeeInfo emp = new EmployeeInfo();
		emp.setEmployeeCode("089115");
		emp.setDeptCode("W01");
		emp.setChangeType("3");
		emp.setEmployeeName("何波");
		emp.setGender("男");
		emp.setPosition("员工");
		emp.setBirthday(DateUtils.convertToXMLGregorianCalendar(new Date()));
		emp.setStatus("1");
		emp.setEntryDate(DateUtils.convertToXMLGregorianCalendar(new Date()));
		emp.setDepartureDate(DateUtils
				.convertToXMLGregorianCalendar(new Date()));
		emps.add(emp);
		req.setEmployeeInfo(emps);
		String body = empReqTr.fromMessage(req);
		System.out.println(body);
		jmsSender.sendJms(esbHeader, body);

	}

	public ESBHeader getESBHeader() {
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setBusinessId("bus00001");
		esbHeader.setEsbServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_EMPLOYEE");
		esbHeader.setBackServiceCode("WDGH_ESB2WDGH_WDGH_SYNC_EMPLOYEE");
		esbHeader.setExchangePattern(2);
		esbHeader.setMessageFormat("XML");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setSourceSystem("UUMS");
		esbHeader.setVersion("1.0");
		return esbHeader;
	}

}
