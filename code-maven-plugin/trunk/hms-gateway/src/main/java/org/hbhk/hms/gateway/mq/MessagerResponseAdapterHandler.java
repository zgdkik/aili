package org.hbhk.hms.gateway.mq;


import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.hbhk.hms.mq.rabbit.CodecFactory;
import org.hbhk.hms.mq.rabbit.IEventMessageStorage;
import org.hbhk.hms.mq.rabbit.IMessageAdapterHandler;
import org.hbhk.hms.mq.rabbit.impl.EventMessage;
import org.hbhk.hms.mq.rabbit.impl.EventProcessorWrap;
import org.hbhk.hms.mq.rabbit.impl.MqResponse;
import org.hbhk.hms.mq.util.StringUtils;
import org.hbhk.hms.rpc.protocol.SerializationUtil;

/**
 * MessageListenerAdapter的Pojo
 * <p>消息路由处理适配器，主要功能：</p>
 * <p>1、将不同的消息类型绑定到对应的处理器并缓存，如将queue+exchange的消息统一交由A处理器来出来</p>
 * <p>2、执行消息的消费分发，调用相应的处理器来消费属于它的消息</p>
 *
 */
public class MessagerResponseAdapterHandler  implements IMessageAdapterHandler {

	private static final Logger logger = Logger.getLogger(MessagerResponseAdapterHandler.class);

	private ConcurrentMap<String, EventProcessorWrap> epwMap;
	
	private IEventMessageStorage eventMessageStorage;
	private RepeatSendTask repeatSendTask;

	public MessagerResponseAdapterHandler(IEventMessageStorage eventMessageStorage) {
		this.epwMap = new ConcurrentHashMap<String, EventProcessorWrap>();
		this.eventMessageStorage = eventMessageStorage;
		this.repeatSendTask = new RepeatSendTask();
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
		try {
			String requestId = eem.getRequestId();
			MqResponse response =  SerializationUtil.deserialize(eem.getEventData(), MqResponse.class);
			Boolean success = response.getSuccess();
			logger.info("receive response msg requestId: "+requestId);
			if(success!=null && success == true){
				eventMessageStorage.removeEventMessage(requestId);
			}else{
				//TODO 业务系统 处理失败 ，需要重复发送，发送机制是什么
				logger.error("response error : "+response.getErrorMsg());
				EventMessage eventMessage =  eventMessageStorage.getEventMessag(requestId);
				repeatSend(eventMessage);
			}
		} catch (Exception e) {
			//处理响应消息错误 TODO
			StringBuilder msg = new StringBuilder();
			msg.append("requestId:"+ eem.getRequestId());
			msg.append("queueName:"+ eem.getQueueName());
			msg.append("exchangeName:"+ eem.getExchangeName());
			logger.error("deal  response error : " +msg,e);
		}
	}
	private  void  repeatSend(EventMessage eventMessage){
		if(eventMessage!=null){
			repeatSendTask.repeatSend(eventMessage);
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
