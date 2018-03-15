package org.hbhk.aili.esb.server.common.log.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hbhk.aili.esb.server.common.utils.jms.EsbLogMessage;


public interface IPreparedStatementSetter {
	
	public String getSql();
	
	public PreparedStatement setValues(PreparedStatement ps,EsbLogMessage esbLogMessage) throws SQLException;
	
	
}
