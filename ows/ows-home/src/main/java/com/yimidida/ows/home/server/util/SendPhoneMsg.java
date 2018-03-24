package com.yimidida.ows.home.server.util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ows.home.server.constant.Constant;

public class SendPhoneMsg {

	public static final MediaType SOAP_XML = MediaType.parse("application/soap+xml; charset=UTF-8");
	private static final OkHttpClient mOkHttpClient = new OkHttpClient();
	private static final String SUCCESS = "\"result\":\"0\",\"desc\":\"提交成功\"";
	public static void main(String[] args) throws IOException {
		System.out.println(testsendPhoneCode("15821999948","测试通信通道官网222"));
//		String content = "注册验证码:" + 111111
//				+ ",有效时间为30分钟,如不是您本人操作请忽略此短信,谢谢!";
//		newSendPhoneCode("13020205713", content);

	}
	
	
	public static boolean testsendPhoneCode(String phone,String content) {
		//接口地址
		String url ="http://wt.3tong.net/json/sms/Submit";
		String uname="ymdadaowl";
		String pwd ="cfedcefdf136ce6d62a3f24055c44a34";
		
		
		String body ="{\"account\":\""+uname+"\",\"password\":\""+pwd
				+ "\",\"msgid\":\"\""
				+ ",\"phones\":\""+phone+"\",\"content\":\""+content+"\",\"sign\":\"【大道物流】\",\"sendtime\":\"\"}";
		System.out.println(body);
		RequestBody rbody = RequestBody.create(SOAP_XML,body);
		Request request = new Request.Builder().url(url).post(rbody).build();
		Response response = null;
		try {
			response = mOkHttpClient.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.isSuccessful()) {
			String res="";
			try {
				res = response.body().string();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(res!=null&&res.contains(SUCCESS)){
				return true;
			}
			
		}
		return false;
	}

	public static boolean sendPhoneCode(String phone,String content) {
		//接口地址
		String url = SystemParameterUtil.getValue("message.url");
		String uname=SystemParameterUtil.getValue("message.username");
		String pwd =SystemParameterUtil.getValue("message.password");
		
		
		String body ="{\"account\":\""+uname+"\",\"password\":\""+pwd
				+ "\",\"msgid\":\"\""
				+ ",\"phones\":\""+phone+"\",\"content\":\""+content+"\",\"sign\":\"【大道物流】\",\"sendtime\":\"\"}";
		System.out.println(body);
		RequestBody rbody = RequestBody.create(SOAP_XML,body);
		Request request = new Request.Builder().url(url).post(rbody).build();
		Response response = null;
		try {
			response = mOkHttpClient.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.isSuccessful()) {
			String res="";
			try {
				res = response.body().string();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(res!=null&&res.contains(SUCCESS)){
				return true;
			}
			
		}
		return false;
	}
	public static boolean newSendPhoneCode(String phoneNo,String content){
		Properties prop =  new  Properties();    
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(new File("src/config/phoneMsg.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        String enterpriseID = prop.getProperty( "enterpriseID" ).trim();    
        String loginName = prop.getProperty( "loginName" ).trim();    
        String password = prop.getProperty( "password" ).trim();  
        String url= prop.getProperty( "url" ).trim();  
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("enterpriseID", enterpriseID);
        param.add("loginName", loginName);
        param.add("password", password);
        param.add("smsId", null);
        param.add("subPort", null);
        param.add("content", "【壹米滴答】"+content);
        param.add("mobiles", phoneNo);
        param.add("sendTime", null);
        String resposeXml = HttpUtil.sendPost(url, param, String.class);
        Document document=null;
		try {
			document = DocumentHelper.parseText(resposeXml);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        Element root = document.getRootElement();
        String sendResult= listNodes(root);
        if("0".equals(sendResult)){
        	return true;
        }
        else{
        	return false;
        }
}  
	//获取发送结果
	public  static String listNodes(Element node){  
	    String Result = "";
	    Iterator<Element> iterator = node.elementIterator();  
	    while(iterator.hasNext()){  
	        Element e = iterator.next();  
	        if("Result".equals(e.getName())){ 
	        	Result= e.getText();   
	       }  
	    }
	    return Result;
	}  
	
}
