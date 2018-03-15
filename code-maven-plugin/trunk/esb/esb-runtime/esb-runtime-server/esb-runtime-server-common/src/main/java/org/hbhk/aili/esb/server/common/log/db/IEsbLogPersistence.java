package org.hbhk.aili.esb.server.common.log.db;

import java.util.List;

import org.hbhk.aili.esb.server.common.utils.jms.EsbLogMessage;

public interface IEsbLogPersistence {
	
	public void save(List<EsbLogMessage> messages);
}
