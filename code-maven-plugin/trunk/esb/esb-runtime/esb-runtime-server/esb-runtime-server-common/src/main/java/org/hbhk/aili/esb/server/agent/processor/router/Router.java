package org.hbhk.aili.esb.server.agent.processor.router;

import java.util.Map;

public interface Router {
	
	String getDestination(String previous,Map<String,Object> headers);
	
}
