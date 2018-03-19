package org.hbhk.hms.gateway.mq;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.hbhk.hms.mq.rabbit.CodecFactory;
import org.hbhk.hms.mq.rabbit.EventController;
import org.hbhk.hms.mq.rabbit.EventTemplate;
import org.hbhk.hms.mq.rabbit.IMessageAdapterHandler;
import org.hbhk.hms.mq.rabbit.MqControlConfig;
import org.hbhk.hms.mq.rabbit.impl.DefaultEventTemplate;
import org.hbhk.hms.mq.rabbit.impl.HessionCodecFactory;
import org.hbhk.hms.mq.rabbit.impl.MessageErrorHandler;
import org.hbhk.hms.mq.util.StringUtils;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;

/**
 *
 * 和rabbitmq通信的控制器，主要负责：
 * <p>1、和rabbitmq建立连接</p>
 * <p>2、声明exChange和queue以及它们的绑定关系</p>
 * <p>3、启动消息监听容器，并将不同消息的处理者绑定到对应的exchange和queue上</p>
 * <p>4、持有消息发送模版以及所有exchange、queue和绑定关系的本地缓存</p>
 */
public class ServiceDiscoveryController implements EventController {
	
	private CachingConnectionFactory rabbitConnectionFactory;
	
	private MqControlConfig config;
	
	private RabbitAdmin rabbitAdmin;
	
	private CodecFactory defaultCodecFactory = new HessionCodecFactory();
	
	
	
	public static  Map<String, SimpleMessageListenerContainer>  listenerContainerMap = new ConcurrentHashMap<String, SimpleMessageListenerContainer>(); 

	public static  Map<String, IMessageAdapterHandler>  msgAdapterHandlerMap = new ConcurrentHashMap<String, IMessageAdapterHandler>(); 

	private IMessageAdapterHandler msgAdapterHandler;
	
	private MessageConverter serializerMessageConverter = new SerializerMessageConverter(); // 直接指定
	//queue cache, key is exchangeName
	private Map<String, DirectExchange> exchanges = new HashMap<String,DirectExchange>();
	//queue cache, key is queueName
	private Map<String, Queue> queues = new HashMap<String, Queue>();
	//bind relation of queue to exchange cache, value is exchangeName | queueName
	private Set<String> binded = new HashSet<String>();
	
	private EventTemplate eventTemplate; // 给App使用的Event发送客户端
	
	private AtomicBoolean isStarted = new AtomicBoolean(false);
	
	private static ServiceDiscoveryController defaultEventController;
	
	public synchronized static ServiceDiscoveryController getInstance(MqControlConfig config,IMessageAdapterHandler msgAdapterHandler){
		if(defaultEventController==null){
			defaultEventController = new ServiceDiscoveryController(config,msgAdapterHandler);
		}
		return defaultEventController;
	}
	
	private ServiceDiscoveryController(MqControlConfig config,IMessageAdapterHandler msgAdapterHandler){
		if (config == null) {
			throw new IllegalArgumentException("Config can not be null.");
		}
		this.config = config;
		this.msgAdapterHandler = msgAdapterHandler;
		initRabbitConnectionFactory();
		// 初始化AmqpAdmin
		rabbitAdmin = new RabbitAdmin(rabbitConnectionFactory);
		// 初始化RabbitTemplate
		RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
		rabbitTemplate.setMessageConverter(serializerMessageConverter);
		eventTemplate = new DefaultEventTemplate(rabbitTemplate,defaultCodecFactory, this);
	}
	
	/**
	 * 初始化rabbitmq连接
	 */
	private void initRabbitConnectionFactory() {
		rabbitConnectionFactory = new CachingConnectionFactory();
		rabbitConnectionFactory.setHost(config.getServerHost());
		rabbitConnectionFactory.setChannelCacheSize(config.getEventMsgProcessNum());
		rabbitConnectionFactory.setPort(config.getPort());
		rabbitConnectionFactory.setUsername(config.getUsername());
		rabbitConnectionFactory.setPassword(config.getPassword());
		if (!StringUtils.isEmpty(config.getVirtualHost())) {
			rabbitConnectionFactory.setVirtualHost(config.getVirtualHost());
		}
	}
	
	/**
	 * 注销程序
	 */
	public synchronized void destroy() throws Exception {
		if (!isStarted.get()) {
			return;
		}
		Collection<SimpleMessageListenerContainer> list =  listenerContainerMap.values();
		for (SimpleMessageListenerContainer smslc : list) {
			smslc.stop();
		}
		eventTemplate = null;
		rabbitAdmin = null;
		rabbitConnectionFactory.destroy();
	}
	
	@Override
	public void start() {
		if (isStarted.get()) {
			return;
		}
		Set<String> mapping = msgAdapterHandler.getAllBinding();
		for (String relation : mapping) {
			String[] relaArr = relation.split("\\|");
			declareBinding(relaArr[1], relaArr[0]);
		}
		initMsgListenerAdapter();
		isStarted.set(true);
	}
	
	/**
	 * 初始化消息监听器容器
	 */
	private void initMsgListenerAdapter(){
		
		if(!queues.isEmpty()){
			Set<String> queueNames = queues.keySet();
			if(queueNames==null|| queueNames.isEmpty()){
				return;
			}
			SimpleMessageListenerContainer msgListenerContainer = null;
			for (String queueName : queueNames) {
				if(listenerContainerMap.containsKey(queueName)){
					continue;
				}
				MessageListener listener = new MessageListenerAdapter(msgAdapterHandlerMap.get(queueName),serializerMessageConverter);
				msgListenerContainer = new SimpleMessageListenerContainer();
				msgListenerContainer.setConnectionFactory(rabbitConnectionFactory);
				msgListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
				msgListenerContainer.setMessageListener(listener);
				msgListenerContainer.setErrorHandler(new MessageErrorHandler());
				msgListenerContainer.setPrefetchCount(config.getPrefetchSize()); // 设置每个消费者消息的预取值
				msgListenerContainer.setConcurrentConsumers(config.getEventMsgProcessNum());
				msgListenerContainer.setTxSize(config.getPrefetchSize());//设置有事务时处理的消息数
				msgListenerContainer.setQueueNames(queueName);
				msgListenerContainer.start();
				listenerContainerMap.put(queueName, msgListenerContainer);
			}
			Set<String> queueNameMap = listenerContainerMap.keySet();
			for (String key : queueNameMap) {
				if(!queueNames.contains(key)){
					listenerContainerMap.get(key).stop();
				}
				
			}
		}
		
	}

	@Override
	public EventTemplate getEopEventTemplate() {
		return eventTemplate;
	}

	@Override
	public EventController add(String queueName, String exchangeName) {
		return add(queueName, exchangeName, defaultCodecFactory);
	}
	
	public EventController add(String queueName, String exchangeName,CodecFactory codecFactory) {
		msgAdapterHandler.add(queueName, exchangeName, defaultCodecFactory);
		msgAdapterHandlerMap.put(queueName, msgAdapterHandler);
		if(isStarted.get()){
			initMsgListenerAdapter();
		}
		return this;
	}
	
	@Override
	public EventController add(String queueName, String exchangeName,
			IMessageAdapterHandler messageAdapterHandler) {
		msgAdapterHandler.add(queueName, exchangeName, defaultCodecFactory);
		msgAdapterHandlerMap.put(queueName, messageAdapterHandler);
		if(isStarted.get()){
			initMsgListenerAdapter();
		}
		return this;
	}

	@Override
	public EventController add(Map<String, String> bindings) {
		return add(bindings,defaultCodecFactory);
	}

	public EventController add(Map<String, String> bindings,
			  CodecFactory codecFactory) {
		for(Map.Entry<String, String> item: bindings.entrySet()) 
			msgAdapterHandler.add(item.getKey(),item.getValue(),codecFactory);
		return this;
	}
	
	/**
	 * exchange和queue是否已经绑定
	 */
	public boolean beBinded(String exchangeName, String queueName) {
		return binded.contains(exchangeName+"|"+queueName);
	}
	
	/**
	 * 声明exchange和queue已经它们的绑定关系
	 */
	public synchronized void declareBinding(String exchangeName, String queueName) {
		String bindRelation = exchangeName+"|"+queueName;
		if (binded.contains(bindRelation)) return;
		
		boolean needBinding = false;
		DirectExchange directExchange = exchanges.get(exchangeName);
		if(directExchange == null) {
			directExchange = new DirectExchange(exchangeName, true, false, null);
			exchanges.put(exchangeName, directExchange);
			rabbitAdmin.declareExchange(directExchange);//声明exchange
			needBinding = true;
		}
		
		Queue queue = queues.get(queueName);
		if(queue == null) {
			queue = new Queue(queueName, true, false, false);
			queues.put(queueName, queue);
			rabbitAdmin.declareQueue(queue);	//声明queue
			needBinding = true;
		}
		
		if(needBinding) {
			Binding binding = BindingBuilder.bind(queue).to(directExchange).with(queueName);//将queue绑定到exchange
			rabbitAdmin.declareBinding(binding);//声明绑定关系
			binded.add(bindRelation);
		}
	}

	@Override
	public void setStarted(Boolean start) {
		isStarted.set(start);
	}

	

}
