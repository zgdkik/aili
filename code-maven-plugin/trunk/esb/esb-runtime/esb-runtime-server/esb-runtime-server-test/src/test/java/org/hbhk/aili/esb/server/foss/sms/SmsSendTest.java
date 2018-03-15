package org.hbhk.aili.esb.server.foss.sms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.cxf.helpers.FileUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.hbhk.aili.esb.common.exception.ESBSmsSendException;
import org.hbhk.aili.esb.server.foss4sms.MD5Encrypt;
import org.hbhk.aili.esb.server.foss4sms.SmsRequestJSONConverter;
import org.hbhk.aili.esb.server.foss4sms.SmsSendRequest;
import org.hbhk.aili.esb.server.foss4sms.SmsSendService;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 调用测试网关，如果调用电脑ip没有经过验证返回失败；测试时请把“@Ignore”注解去掉。
 * 
 * 
 * 业务信息传输格式是JSON, 业务实体属性见SmsInfo类 调用短信网关需要经过1、ip验证；2、密文验证;
 * 密文由“用户名+密码”经过MD5加密后获取，用户名、密码需要短信网关分配，ip验证需要向短信网关负责人提交自己的ip，让短信网关添加ip验证信息。.
 * 
 * @author qiancheng
 */
@Ignore
public class SmsSendTest {
	
	/** The log. */
	private Logger log = Logger.getLogger(getClass());
	//测试网关请求地址
	/** The sms url. */
	private String smsURL="http://192.168.17.152:8085/dpVoiceSms/ws/sms/batch";
	
	/** The http client. */
	private HttpClient httpClient= new HttpClient();
	
	/** The user. */
	private String user="esbsms";
	
	/** The passwd. */
	private String passwd="20121019";
	
	/**
	 * Test.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test()throws Exception{
		PostMethod postMethod = new PostMethod(smsURL);
		//
		String data = createPasswd(user,passwd);
		//
		String jsonMsg = createJsonMsg();
		
		postMethod.setRequestBody(new NameValuePair[]{new NameValuePair("data_digest", data), new NameValuePair("smsInfo", jsonMsg)});
		postMethod.getParams().setContentCharset("UTF-8");
		try {
			int status =  httpClient.executeMethod(postMethod);
			 String str = postMethod.getResponseBodyAsString();
			 log.debug("status:"+status);
			 System.out.println("response body:"+str);
			 Assert.assertEquals(200, status);
			 Assert.assertNotNull(str);
		} catch (HttpException e) {
			throw new ESBSmsSendException(e.getMessage()); 
		} catch (IOException e) {
			throw new ESBSmsSendException(e.getMessage()); 
		}
	}
	
	/**
	 * 生成密文.
	 * 
	 * @param user
	 *            the user
	 * @param passwd
	 *            the passwd
	 * @return the string
	 */
	public String createPasswd(String user,String passwd){
		return MD5Encrypt.encrypt(user+passwd);
	}
	
	/**
	 * 生成JSON数据.
	 * 
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String createJsonMsg()throws Exception{
		List<SmsInfo> list = new ArrayList<SmsInfo>();
		for(int i =0;i<10;i++){
			SmsInfo info = new SmsInfo();
			info.setLatestSendTime(new Date());
			info.setMobile("12345");
			//为避免误发给别人短信，测试时尽量填写自己的手机号
			info.setMobile("13917097293");
			info.setMsgContent("test msg");
			info.setMsgSource("CRM");
			info.setSendDept("ESB");
			info.setSender("054839");
			info.setSendTime(new Date());
			info.setServiceType("短信");
			info.setUnionId(UUID.randomUUID().toString());
			info.setWaybillNo(null);
			list.add(info);
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(list);
	}
	
	private static final String URL = "http://192.168.17.152:8085/dpVoiceSms/ws/sms/batch";
	@Test
	public void test2()throws Exception{
	
		SmsSendService service = null;
		service = new SmsSendService();
		
		SmsSendRequest rq = getSmsSendRequestFromFile();
		String jsonSource = SmsRequestJSONConverter.pojo2Json(rq
				.getSmsInfos());
		rq.getSmsAuthority().setUsername(user);
		rq.getSmsAuthority().setPassword(passwd);
		String user = rq.getSmsAuthority().getUsername();
		String passwd = rq.getSmsAuthority().getPassword();
		send(user, passwd, jsonSource, URL);
	}
	
	public SmsSendRequest  getSmsSendRequestFromFile()throws Exception{
		File file = new File("src/test/resources/com/deppon/esb/server/foss/sms/data/sa.txt");
		String jsonstr= FileUtils.getStringFromFile(file);
		SmsSendRequest rq = SmsRequestJSONConverter.json2Java(jsonstr);
		return rq;
	}
	
	private static Logger LOG = Logger.getLogger(SmsSendTest.class);
	private String send(String user,String passwd,String jsonMsg,String url)throws ESBSmsSendException {
		PostMethod postMethod = new PostMethod(url);
		String encryption = MD5Encrypt.encrypt(user+passwd);
		postMethod.setRequestBody(new NameValuePair[]{new NameValuePair("data_digest", encryption), new NameValuePair("smsInfo", jsonMsg)});
		postMethod.getParams().setContentCharset("UTF-8");
		try {
			 LOG.debug(" start to send Info to SMS"+":"+jsonMsg);
			 LOG.debug("msg:"+jsonMsg);
			 int resultcode = httpClient.executeMethod(postMethod);
			// System.out.println(resultcode);
			 if(resultcode !=200){
				 String str = postMethod.getResponseBodyAsString();
				 
				 throw new  ESBSmsSendException("send sms fail ， resultstatus ："+resultcode+" detail:"+str);
			 }
			 LOG.debug("send Info to SMS successfully ");
			 return  postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			LOG.error(e.getMessage(), e);
			throw new ESBSmsSendException(e.getMessage(),e); 
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new ESBSmsSendException(e.getMessage(),e); 
		}
	}
}
