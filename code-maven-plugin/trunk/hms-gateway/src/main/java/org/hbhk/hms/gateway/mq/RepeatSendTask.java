package org.hbhk.hms.gateway.mq;

import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hbhk.hms.mq.rabbit.EventTemplate;
import org.hbhk.hms.mq.rabbit.MqConstant;
import org.hbhk.hms.mq.rabbit.impl.EventMessage;
import org.hbhk.hms.mq.rabbit.impl.MqBootstrap;

public class RepeatSendTask {
	
	private static final Logger logger = Logger.getLogger(RepeatSendTask.class);
	private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(100);
	public  static ConcurrentMap<String, Integer> repeatSendMap = new ConcurrentHashMap<String, Integer>();

	public RepeatSendTask() {

	}
	public void repeatSend(EventMessage eventMessage){
		TimerTask task = new TTlTimerTask(eventMessage);
		if(!repeatSendMap.containsKey(eventMessage.getRequestId())){
			repeatSendMap.put(eventMessage.getRequestId(), new Integer(1));
		}
		int times = repeatSendMap.get(eventMessage.getRequestId()).intValue();
		long delay = new Double(Math.pow(2,times)).longValue();
		scheduledExecutor.schedule(task,delay, TimeUnit.SECONDS);
		times=times+1;
		repeatSendMap.put(eventMessage.getRequestId(), times);
	}
	class TTlTimerTask extends TimerTask {

		private EventMessage eventMessage;

		public TTlTimerTask(EventMessage eventMessage) {
			super();
			this.eventMessage = eventMessage;
		}

		@Override
		public void run() {
			EventTemplate amqpTemplate = MqBootstrap.eventTemplate;
			String queueName = eventMessage.getQueueName();
			String toQueueName = queueName+MqConstant._r_request_queue;
			String toExchangeName =eventMessage.getExchangeName()+MqConstant._r_request_exchange;
			logger.info("route info  queue "+queueName +"to"+toQueueName+" exchange "+eventMessage.getExchangeName() +" to " +toExchangeName);
			amqpTemplate.send(eventMessage.getRequestId(),toQueueName,toExchangeName, eventMessage.getEventData());
		}

	}
}
