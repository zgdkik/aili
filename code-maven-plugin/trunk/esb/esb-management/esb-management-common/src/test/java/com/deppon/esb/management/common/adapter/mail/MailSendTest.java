package com.deppon.esb.management.common.adapter.mail;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @Description 邮件发送测试，默认是忽略测试（免得发送地址成为垃圾邮件地址，同时免除我删除这些垃圾测试邮件的苦恼）
 *              我也不知道操蛋的断言应该怎么写，所以要验证测试是否成功，请自行前往邮箱查看是否收到邮件。
 * @author HuangHua
 * @date 2012-4-25上午11:41:10
 */
@ContextConfiguration(locations = {
		"classpath*:com/deppon/esb/management/common/META-INF/adapter/mail/spring-mail.xml",
		"classpath*:com/deppon/esb/management/common/META-INF/adapter/mail/spring-mail-template.xml" })
public class MailSendTest extends AbstractJUnit4SpringContextTests {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailSendTest.class);

	private static final String TO_USER_NAME = "HuangHua";
	private static final String TO_MAIL_ADDR = "esb@deppon.com";

	/**
	 * 发送队列告警邮件
	 * 
	 * @author HuangHua
	 * @date 2012-12-29 上午10:36:26
	 */
	@Test
	public void testSendQueueMail() {
		VelocityMailSupport quWrnMailSupport = (VelocityMailSupport) applicationContext
				.getBean("quWrnMailSupport");
		Map<String, Object> quWrnmodel = new HashMap<String, Object>();
		quWrnmodel.put("qmgr", "QM_TEST");
		quWrnmodel.put("queue", "QU_ORDER_IN_TEST");
		quWrnmodel.put("time", new Timestamp(System.currentTimeMillis()));
		quWrnmodel.put("currentDepth", "5010");
		quWrnmodel.put("threshold", "5000");
		try {
			quWrnMailSupport.sendMime(quWrnmodel, TO_USER_NAME, TO_MAIL_ADDR);
		} catch (MailException e) {
			Assert.fail("发送失败，报错！");
		}
		LOGGER.info("发送队列告警邮件成功。");
	}

	/**
	 * 发送异常告警邮件
	 * 
	 * @author HuangHua
	 * @date 2012-12-29 上午10:33:29
	 */
	@Test
	public void testSendExceptionMail() {
		VelocityMailSupport excptnWrnMailSupport = (VelocityMailSupport) applicationContext
				.getBean("excptnWrnMailSupport");
		Map<String, Object> excptnmodel = new HashMap<String, Object>();
		excptnmodel.put("time", 5);
		excptnmodel.put("errorCount", 10);
		try {
			excptnWrnMailSupport.sendMime(excptnmodel, TO_USER_NAME,
					TO_MAIL_ADDR);
		} catch (MailException e) {
			Assert.fail("发送失败，报错！");
		}
		LOGGER.info("发送异常告警邮件成功。");
	}

	/**
	 * 发送性能告警邮件
	 * 
	 * @author HuangHua
	 * @date 2012-12-29 上午10:35:48
	 */
	@Test
	public void testSendPerformanceMail() {
		VelocityMailSupport pfmncWrnMailSupport = (VelocityMailSupport) applicationContext
				.getBean("pfmncWrnMailSupport");
		Map<String, Object> pfmncmodel = new HashMap<String, Object>();
		pfmncmodel.put("svcName", "测试新增订单");
		pfmncmodel.put("time", new Timestamp(System.currentTimeMillis()));
		pfmncmodel.put("avgTime", "110");
		pfmncmodel.put("threshold", "100");
		try {
			pfmncWrnMailSupport
					.sendMime(pfmncmodel, TO_USER_NAME, TO_MAIL_ADDR);
		} catch (MailException e) {
			Assert.fail("发送失败，报错！");
		}
		LOGGER.info("发送性能告警邮件成功。");
	}
	
	@Test
	public void testSendStatusNoComplete(){
		VelocityMailSupport pfmncWrnMailSupport = (VelocityMailSupport) applicationContext
				.getBean("statusNoCompleteMailSupport");
		Map<String, Object> statusNoCompleteModel = new HashMap<String, Object>();
		statusNoCompleteModel.put("esbServiceCode", "esbServiceCode");
		statusNoCompleteModel.put("backServiceCode", "backServiceCode");
		statusNoCompleteModel.put("requestId", "requestId");
		statusNoCompleteModel.put("responseId", "responseId");
		statusNoCompleteModel.put("statusId", "statusId");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		statusNoCompleteModel.put("statusTimeStamp", dateFormat.format(new Date()));
		try {
			pfmncWrnMailSupport
					.sendMime(statusNoCompleteModel, TO_USER_NAME, TO_MAIL_ADDR);
		} catch (MailException e) {
			Assert.fail("发送失败，报错！");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOGGER.debug(e.getMessage());
		}
		LOGGER.info("发送状态不完整告警邮件成功。");
	
	}
	@Test
	public void testSendExcptn2(){
		VelocityMailSupport excptn2MailSupport = (VelocityMailSupport) applicationContext
				.getBean("excptn2MailSupport");
		Map<String, Object> statusNoCompleteModel = new HashMap<String, Object>();
		statusNoCompleteModel.put("bizId", "bizId");
		statusNoCompleteModel.put("esbServiceCode", "esbServiceCode");
		statusNoCompleteModel.put("backServiceCode", "backServiceCode");
		statusNoCompleteModel.put("excptnCode", "excptnCode");
		statusNoCompleteModel.put("excptnType", "excptnType");
		statusNoCompleteModel.put("excptnMsg", "excptnMsg");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		statusNoCompleteModel.put("excptnTime", dateFormat.format(new Date()));
		try {
			excptn2MailSupport
			.sendMime(statusNoCompleteModel, TO_USER_NAME, TO_MAIL_ADDR);
		} catch (MailException e) {
			Assert.fail("发送失败，报错！");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOGGER.debug(e.getMessage());
		}
		LOGGER.info("发送ESB二期异常告警告警邮件成功。");
		
	}
}
