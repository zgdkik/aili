package org.hbhk.aili.jms.server.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.ConnectionFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

public class MessageListenerContainer {

	public static ExecutorService service = Executors.newFixedThreadPool(10);
	private static Log log = LogFactory.getLog(MessageListenerContainer.class);
	public static  ApplicationContext  context = new  ClassPathXmlApplicationContext("classpath:spring-jms.xml");
	
	public static Map<String, DefaultMessageListenerContainer> listMap = new ConcurrentHashMap<String, DefaultMessageListenerContainer>();
	
	private BusinessListener  listener;
	
	public void startMessageListener(final ConnectionFactory cf,final BusinessListener listener,
			final String messageSelector, final String destinationName) {
		service.execute(new Runnable() {
			@Override
			public void run() {
				log.info("消息过滤器:"+messageSelector +" 接收队列:"+destinationName);
				DefaultMessageListenerContainer mlc = (DefaultMessageListenerContainer) context.getBean("businessListener");
				mlc.setConnectionFactory(cf);
				mlc.setMessageListener(listener);
				if(StringUtils.isNotEmpty(messageSelector)){
					//serviceCode in ('hbhk_code2')
					String str[] = messageSelector.split("=");
					if(str.length==2){
						mlc.setMessageSelector(str[0]+" in ('"+str[1]+"')");
						log.info("消息选择器:"+str[0]+" in ('"+str[1]+"')");
					}
				}
				mlc.setDestinationName(destinationName);
				listMap.put(listener.toString(), mlc);
				mlc.start();
				log.info("启动完成...");
			}
		});
	}

	public BusinessListener getListener() {
		return listener;
	}

	public void setListener(BusinessListener listener) {
		this.listener = listener;
	}
	
	
}
