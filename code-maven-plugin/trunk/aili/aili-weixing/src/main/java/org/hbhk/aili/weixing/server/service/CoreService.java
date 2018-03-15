package org.hbhk.aili.weixing.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hbhk.aili.weixing.share.message.resp.Article;
import org.hbhk.aili.weixing.share.message.resp.NewsMessage;
import org.hbhk.aili.weixing.share.message.resp.TextMessage;
import org.hbhk.aili.weixing.share.util.MessageUtil;

/** 
 * 锟斤拷锟侥凤拷锟斤拷锟斤拷 
 *  
 */  
public class CoreService {  
    /** 
     * 锟斤拷锟斤拷微锟脚凤拷锟斤拷锟斤拷锟斤拷锟斤拷 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
      
        try {  
            // 默锟较凤拷锟截碉拷锟侥憋拷锟斤拷息锟斤拷锟斤拷  
            String respContent = "锟斤拷锟斤拷锟斤拷锟届常锟斤拷锟斤拷锟皆猴拷锟皆ｏ拷";  
  
            // xml锟斤拷锟斤拷锟斤拷锟� 
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 锟斤拷锟酵凤拷锟绞号ｏ拷open_id锟斤拷  
            String fromUserName = requestMap.get("FromUserName");  
            // 锟斤拷锟斤拷锟绞猴拷  
            String toUserName = requestMap.get("ToUserName");  
            // 锟斤拷息锟斤拷锟斤拷  
            String msgType = requestMap.get("MsgType"); 
           
  
            // 锟截革拷锟侥憋拷锟斤拷息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            
            //锟截革拷图片锟斤拷息           
            NewsMessage newsMessage=new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0); 
            List<Article> arlist=new ArrayList<Article>();

            // 锟侥憋拷锟斤拷息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { 
            	 //锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷息
                String content=requestMap.get("Content"); 
                if (content.trim().equals("锟斤拷")||content.trim().equals("?")) {
                //respContent=Test1.getMainMenu();
                	textMessage.setContent(respContent);                
                    respMessage = MessageUtil.textMessageToXml(textMessage);
				}else if (content.trim().equals("1")) {
					
					if (respContent=="1") {
						respContent="锟斤拷锟斤拷锟阶帮拷锟斤拷小锟斤拷锟斤拷锟斤拷锟斤拷~";
					}
					
					//respContent=TalkService.GiveTalk(content);
					
					textMessage.setContent(respContent);                
		               respMessage = MessageUtil.textMessageToXml(textMessage);
				}else if (content.trim().equals("2")) {
					respContent="锟斤拷锟斤拷锟斤拷什么态锟饺ｏ拷锟斤拷锟酵革拷锟斤拷什么锟斤拷色锟斤拷";	
					textMessage.setContent(respContent);                
		               respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				else if (content.trim().equals("3")) {
					Article ar1=new Article();
		            ar1.setDescription("锟斤拷使锟斤拷魔锟斤拷");
		            ar1.setPicUrl("http://www.xj-wlpf.com/Web_test/Image/11.jpg");
		            ar1.setTitle("锟秸斤拷锟斤拷去锟斤拷锟洁春");
		            ar1.setUrl("http://www.xj-wlpf.com/Web_test/");
		            arlist.add(ar1);
		            newsMessage.setArticleCount(1);
		            newsMessage.setArticles(arlist);
					respMessage=MessageUtil.newsMessageToXml(newsMessage);			
				}
				else if (content.trim().equals("4")) {
					  Article ar2=new Article();
			            ar2.setDescription("锟届长锟截撅拷");
			            ar2.setPicUrl("http://www.xj-wlpf.com/Web_test/Image/CS.jpg");
			            ar2.setTitle("锟届长锟截撅拷锟叫撅拷时锟斤拷锟剿帮拷锟斤拷锟斤拷锟睫撅拷锟节ｏ拷");
			            ar2.setUrl("http://www.xj-wlpf.com/Web_test/");
			            arlist.add(ar2);
					 newsMessage.setArticleCount(1);
			           newsMessage.setArticles(arlist);
					respMessage=MessageUtil.newsMessageToXml(newsMessage);			
				}
                else{ 
                respContent="锟斤拷锟斤拷"+content;
                textMessage.setContent(respContent);                
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
                  
            }
            // 图片锟斤拷息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "锟斤拷锟酵碉拷锟斤拷图片锟斤拷息锟斤拷";  
                
            }  
            // 锟斤拷锟斤拷位锟斤拷锟斤拷息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "锟斤拷锟酵碉拷锟角碉拷锟斤拷位锟斤拷锟斤拷息锟斤拷";  
            }  
            // 锟斤拷锟斤拷锟斤拷息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "锟斤拷锟酵碉拷锟斤拷锟斤拷锟斤拷锟斤拷息锟斤拷";  
            }  
            // 锟斤拷频锟斤拷息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "锟斤拷锟酵碉拷锟斤拷锟斤拷频锟斤拷息锟斤拷";  
            }  
            // 锟铰硷拷锟斤拷锟斤拷  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 锟铰硷拷锟斤拷锟斤拷  
                String eventType = requestMap.get("Event");  
                // 锟斤拷锟斤拷  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                   // respContent = Test1.getMainMenu();  
                }  
                // 取锟斤拷锟斤拷  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取锟斤拷锟侥猴拷锟矫伙拷锟斤拷锟秸诧拷锟斤拷锟斤拷锟节号凤拷锟酵碉拷锟斤拷息锟斤拷锟斤拷瞬锟斤拷锟揭拷馗锟斤拷锟较� 
                }  
                // 锟皆讹拷锟斤拷说锟斤拷锟斤拷锟铰硷拷  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO 锟皆讹拷锟斤拷说锟饺伙拷锌锟斤拷牛锟斤拷莶锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷息  
                }  
            }  
  
            
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  
}  
