package org.hbhk.aili.esb.server.common.log.db.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.log.db.IPreparedStatementSetter;
import org.hbhk.aili.esb.server.common.utils.jms.EsbLogMessage;

/**
 * 状态日志保存
 * TODO（描述类的职责）
 * @author davidcun
 * @date 2013-4-26 下午03:06:58
 */
public class StatusLogPreparedStatementSetter implements
		IPreparedStatementSetter {

	private static final Log LOG = LogFactory.getLog(AuditPreparedStatementSetter.class);
	
	private final String INSERT_STATUS_LOG = "INSERT INTO   T_ESB2_STATUS(FID,ESBSVCCODE,TARGETSVCCODE,BIZID,REQID,RESID,SOURCESYS,TARGETSYS,STATUSID,TIMESTAMP,CREATETIME) VALUES(sys_guid(),?,?,?,?,?,?,?,?,?,?)";

	@Override
	public String getSql() {
		return INSERT_STATUS_LOG;
	}

	@Override
	public PreparedStatement setValues(PreparedStatement ps,
			EsbLogMessage esbLogMessage) throws SQLException {
		// 空检查
		if (ps == null || esbLogMessage == null || esbLogMessage.getHeader()==null) {
			LOG.warn(String.format("object %s invoker method setValues errror - %s", this,"args ps or esbLogMessage or esbLogMessage.header is null"));
		} else {
			String[] statusArray = esbLogMessage.getBody().split(":");
			for (String st : statusArray) {// 根据状态消息格式转换
				ps.setObject(
						1,
						esbLogMessage.getHeader().get(
								ESBServiceConstant.ESB_SERVICE_CODE));
				ps.setObject(
						2,
						esbLogMessage.getHeader().get(
								ESBServiceConstant.BACK_SERVICE_CODE));
				ps.setObject(
						3,
						esbLogMessage.getHeader().get(
								ESBServiceConstant.BUSINESSID));
				ps.setObject(
						4,
						esbLogMessage.getHeader().get(
								ESBServiceConstant.REQUEST_ID));
				ps.setObject(
						5,
						esbLogMessage.getHeader().get(
								ESBServiceConstant.RESPONSE_ID));
				ps.setObject(
						6,
						esbLogMessage.getHeader().get(
								ESBServiceConstant.SOURCE_SYSTEM));
				ps.setObject(
						7,
						esbLogMessage.getHeader().get(
								ESBServiceConstant.TARGET_SYSTEM));
				String[] sts = st.split("@");
				ps.setObject(8, sts[0]);
				ps.setObject(9, Long.parseLong(sts[1]));
				//ps.setTimestamp(10, new Timestamp((new java.util.Date()).getTime()));
				
				if (esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME)!=null) {
//					Date d = new Date(((java.util.Date)esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME)).getTime());
//					Date d = (Date)esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME);
					ps.setTimestamp(10, new Timestamp(((java.util.Date)esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME)).getTime()));
				}else{
					ps.setTimestamp(10, new Timestamp(new java.util.Date().getTime()));
				}
				
				ps.addBatch();
			}
		}
		return ps;
	}

}
