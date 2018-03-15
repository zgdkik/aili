package org.hbhk.aili.route.jms.server.common.log.db;

import java.util.List;

import org.hbhk.aili.route.jms.server.common.utils.jms.EsbLogMessage;

public interface IEsbLogPersistence {
	
	public void save(List<EsbLogMessage> messages);
}
