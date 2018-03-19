package org.hbhk.hms.gateway.mq;

import java.util.List;
import java.util.Map;

import org.hbhk.hms.mq.rabbit.IEventMessageStorage;
import org.hbhk.hms.mq.rabbit.impl.EventMessage;
import org.jboss.netty.util.internal.ConcurrentHashMap;

public class DefaultEventMessageStorage  implements IEventMessageStorage {
	
	private static Map<String, EventMessage> eventMessageCache = new ConcurrentHashMap<String, EventMessage>();

	@Override
	public void saveEventMessage(EventMessage message) {
		eventMessageCache.put(message.getRequestId(), message);
	}

	@Override
	public void removeEventMessage(String requestId) {
		eventMessageCache.remove(requestId);
		RepeatSendTask.repeatSendMap.remove(requestId);
	}

	@Override
	public EventMessage getEventMessag(String requestId) {
		return eventMessageCache.get(requestId);
	}

	@Override
	public List<EventMessage> getEventMessages(List<String> requestIds) {
		return null;
	}

	@Override
	public List<EventMessage> getAllEventMessages() {
		return null;
	}

}
