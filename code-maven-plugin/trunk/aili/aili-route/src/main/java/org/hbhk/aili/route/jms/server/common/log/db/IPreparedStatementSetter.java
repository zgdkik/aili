package org.hbhk.aili.route.jms.server.common.log.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hbhk.aili.route.jms.server.common.utils.jms.EsbLogMessage;


public interface IPreparedStatementSetter {
	
	public String getSql();
	
	public PreparedStatement setValues(PreparedStatement ps,EsbLogMessage esbLogMessage) throws SQLException;
	
	
}
