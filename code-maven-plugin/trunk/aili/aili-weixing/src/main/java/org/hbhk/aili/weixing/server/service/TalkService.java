package org.hbhk.aili.weixing.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hbhk.aili.weixing.share.entity.YellowTalk;
import org.hbhk.aili.weixing.share.message.resp.TextMessage;
import org.hbhk.aili.weixing.share.util.MessageUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
public class TalkService {
	 public static String processRequest(HttpServletRequest request) throws IOException {  
	
		 String respMessage="";
	        try {
				
	         
	                // Ĭ�Ϸ��ص��ı���Ϣ����  
	                String respContent = "�������쳣�����Ժ��ԣ�";  
	      
	                // xml�������  
	                Map<String, String> requestMap = MessageUtil.parseXml(request);  
	      
	                // ���ͷ��ʺţ�open_id��  
	                String fromUserName = requestMap.get("FromUserName");  
	                // �����ʺ�  
	                String toUserName = requestMap.get("ToUserName");  
	                // ��Ϣ����  
	                String msgType = requestMap.get("MsgType"); 
	                String content=requestMap.get("Content"); 
	      
	                // �ظ��ı���Ϣ  
	                TextMessage textMessage = new TextMessage();  
	                textMessage.setToUserName(fromUserName);  
	                textMessage.setFromUserName(toUserName);  
	                textMessage.setCreateTime(new Date().getTime());  
	                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
	                textMessage.setFuncFlag(0); 
	                //9431cba8-a8f6-4788-a223-e75a8381f3f2
	                //eb00e952-2636-4bfa-bbbd-0a671b28adeb
	                String key="9431cba8-a8f6-4788-a223-e75a8381f3f2";
	                String urlStr="http://sandbox.api.simsimi.com/request.p";
	                String endUrl=urlStr+"?key="+key+"&lc=ch&ft=1.0"+"&text="+URLEncoder.encode(content,"UTF-8");
	                URL url = new URL(endUrl);//Զ��url
	                StringBuffer document = new StringBuffer();
	    			URLConnection conn = url.openConnection();
	    		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	    		    String line = null;
	    		    while ((line = reader.readLine()) != null){
	    		    	document.append(line + " ");
	    		    }
	    		    reader.close();	    		
	    		    String xml = document.toString();//����ֵ
	    		    YellowTalk yt=getPerson(xml,YellowTalk.class);
	    		    respContent=yt.getResponse();  
	    		    if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
	                    respContent = "���͵���������Ϣ��";  
	                }  
	                // ��Ƶ��Ϣ  
	                else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
	                    respContent = "���͵�����Ƶ��Ϣ��";  
	                }  
	                // �¼�����  
	                else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
	                    // �¼�����  
	                    String eventType = requestMap.get("Event");  
	                    // ����  
	                    if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
	                        respContent = "С���������~";  
	                    }  
	                    // ȡ����  
	                    else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
	                        // TODO ȡ���ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ  
	                    }  
	                    // �Զ���˵�����¼�  
	                    else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
	                        // TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ  
	                    }  
	                }  
	      
	                textMessage.setContent(respContent);   
                    respMessage = MessageUtil.textMessageToXml(textMessage);
	        	
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	        return respMessage;
	 }
	 
	 public static YellowTalk getPerson(String jsonString, Class cls) {
		 YellowTalk t = null;
	        try {
	            t = JSON.parseObject(jsonString, cls);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return t;
	    }
	 
	 public static List getPersons(String jsonString, Class cls) {
	        List list = new ArrayList();
	        try {
	            list = JSON.parseArray(jsonString, cls);
	        } catch (Exception e) {
	        }
	        return list;
	    }
	 
	public static List listKeyMaps(String jsonString) {
	        List list = new ArrayList();
	        try {
	            list = JSON.parseObject(jsonString,
	                    new TypeReference() {
	            });
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return list;
	    }
}
