package org.hbhk.aili.route.common.router;

import java.util.Map;

public interface IHttpRouter {
	
	String getDestination(String previous,Map<String,Object> headers);
	
}
