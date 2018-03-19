package org.hbhk.hms.gateway.mq;


import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.hbhk.hms.mq.rabbit.CodecFactory;
import org.hbhk.hms.mq.rabbit.EventTemplate;
import org.hbhk.hms.mq.rabbit.IEventMessageStorage;
import org.hbhk.hms.mq.rabbit.IMessageAdapterHandler;
import org.hbhk.hms.mq.rabbit.MqConstant;
import org.hbhk.hms.mq.rabbit.impl.EventMessage;
import org.hbhk.hms.mq.rabbit.impl.EventProcessorWrap;
import org.hbhk.hms.mq.rabbit.impl.MqBootstrap;
import org.hbhk.hms.mq.util.StringUtils;

/**
 * MessageListenerAdapter的Pojo
 * <p>消息路由处理适配器，主要功能：</p>
 * <p>1、将不同的消息类型绑定到对应的处理器并缓存，如将queue+exchange的消息统一交由A处理器来出来</p>
 * <p>2、执行消息的消费分发，调用相应的处理器来消费属于它的消息</p>
 *
 */
public class MessageRouteAdapterHandler  implements IMessageAdapterHandler {

	private static final Logger logger = Logger.getLogger(MessageRouteAdapterHandler.class);

	private ConcurrentMap<String, EventProcessorWrap> epwMap;

	
	private IEventMessageStorage  eventMessageStorage ;
	
	public MessageRouteAdapterHandler() {
		this.epwMap = new ConcurrentHashMap<String, EventProcessorWrap>();
        eventMessageStorage = new DefaultEventMessageStorage();
	}

	public void handleMessage(EventMessage eem) {
		// ,RpcRequest request
		logger.debug("Receive an EventMessage: [" + eem + "]");
		// 先要判断接收到的message是否是空的，在某些异常情况下，会产生空值
		if (eem == null) {
			logger.warn("Receive an null EventMessage, it may product some errors, and processing message is canceled.");
			return;
		}
		if (StringUtils.isEmpty(eem.getQueueName()) || StringUtils.isEmpty(eem.getExchangeName())) {
			logger.warn("The EventMessage's queueName and exchangeName is empty, this is not allowed, and processing message is canceled.");
			return;
		}
		//判断是否是发送请求队列
		String queueName = eem.getQueueName();
		try {
			eventMessageStorage.saveEventMessage(eem);
			EventTemplate amqpTemplate = MqBootstrap.eventTemplate;
			String toQueueName = queueName+MqConstant._r_request_queue;
			String toExchangeName =eem.getExchangeName()+MqConstant._r_request_exchange;
			logger.info("route msg  queue : requestId = "+eem.getRequestId()+" =>"+queueName +"to"+toQueueName+" exchange "+eem.getExchangeName() +" to " +toExchangeName);
			amqpTemplate.send(eem.getRequestId(),toQueueName,toExchangeName, eem.getEventData());
		} catch (Exception e) {
			//路由转发错误 TODO
			StringBuilder msg = new StringBuilder();
			msg.append("requestId:"+ eem.getRequestId());
			msg.append("queueName:"+ eem.getQueueName());
			msg.append("exchangeName:"+ eem.getExchangeName());
			logger.error("route error : " +msg,e);
		}
	}

	public void add(String queueName, String exchangeName,CodecFactory codecFactory) {
		if (StringUtils.isEmpty(queueName) || StringUtils.isEmpty(exchangeName) || codecFactory == null) {
			throw new RuntimeException("queueName and exchangeName can not be empty,and processor or codecFactory can not be null. ");
		}
		EventProcessorWrap epw = new EventProcessorWrap();
		EventProcessorWrap oldProcessorWrap = epwMap.putIfAbsent(queueName + "|" + exchangeName, epw);
		if (oldProcessorWrap != null) {
			logger.warn("The processor of this queue and exchange exists, and the new one can't be add");
		}
	}
	public Set<String> getAllBinding() {
		Set<String> keySet = epwMap.keySet();
		return keySet;
	}

}
