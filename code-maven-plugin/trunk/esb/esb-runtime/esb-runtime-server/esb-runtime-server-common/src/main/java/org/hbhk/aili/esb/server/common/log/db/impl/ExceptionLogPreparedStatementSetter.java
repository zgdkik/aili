package org.hbhk.aili.esb.server.common.log.db.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.log.db.IPreparedStatementSetter;
import org.hbhk.aili.esb.server.common.transformer.CommonExceptionInfoTrans;
import org.hbhk.aili.esb.server.common.utils.IPUtils;
import org.hbhk.aili.esb.server.common.utils.jms.EsbLogMessage;

public class ExceptionLogPreparedStatementSetter implements IPreparedStatementSetter {
	private static final Log LOG = LogFactory.getLog(ExceptionLogPreparedStatementSetter.class);
	private final String insert_exception_table="insert into t_esb2_exception (FID, VERSION, BIZID, BIZDESC1, BIZDESC2, BIZDESC3, REQID, RESID, SOURCESYS, TARGETSYS, ESBSVCCODE, BACKSVCCODE, MSGFMT, EXGPTN, SENTSEQ, RESULTCODE, USERNAME, PASSWORD, EXCPTNCODE, EXCPTNTYPE, MSG, EXCPTNCREATETIME, DETAIL, HOSTIP, CREATETIME)values (sys_guid(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	@Override
	public String getSql() {
		return insert_exception_table;
	}

	@Override
	public PreparedStatement setValues(PreparedStatement ps,
			EsbLogMessage esbLogMessage) throws SQLException {
		if (ps == null || esbLogMessage == null || esbLogMessage.getHeader()==null) {
			LOG.warn(String.format("object %s invoker method setValues errror - %s", this,"args ps or esbLogMessage or esbLogMessage.header is null"));
		}
		else{
			ps.setObject(1, esbLogMessage.getHeader().get(ESBServiceConstant.VERSION));
			ps.setObject(2, esbLogMessage.getHeader().get(ESBServiceConstant.BUSINESSID));
			ps.setObject(3, esbLogMessage.getHeader().get(ESBServiceConstant.BUSINESSDESC1));
			ps.setObject(4, esbLogMessage.getHeader().get(ESBServiceConstant.BUSINESSDESC2));
			ps.setObject(5, esbLogMessage.getHeader().get(ESBServiceConstant.BUSINESSDESC3));
			ps.setObject(6, esbLogMessage.getHeader().get(ESBServiceConstant.REQUEST_ID));
			ps.setObject(7, esbLogMessage.getHeader().get(ESBServiceConstant.RESPONSE_ID));
			ps.setObject(8, esbLogMessage.getHeader().get(ESBServiceConstant.SOURCE_SYSTEM));
			ps.setObject(9, esbLogMessage.getHeader().get(ESBServiceConstant.TARGET_SYSTEM));
			ps.setObject(10, esbLogMessage.getHeader().get(ESBServiceConstant.ESB_SERVICE_CODE));
			ps.setObject(11, esbLogMessage.getHeader().get(ESBServiceConstant.BACK_SERVICE_CODE));
			ps.setObject(12, esbLogMessage.getHeader().get(ESBServiceConstant.MESSAGE_FORMATE));
			ps.setObject(13, esbLogMessage.getHeader().get(ESBServiceConstant.EXCHANGE_PATTERN));
			ps.setObject(14, esbLogMessage.getHeader().get(ESBServiceConstant.SENTSEQUENCE));
			ps.setObject(15, esbLogMessage.getHeader().get(ESBServiceConstant.RESULT_CODE));
			ps.setObject(16, esbLogMessage.getHeader().get(ESBServiceConstant.USER_NAME));
			ps.setObject(17, esbLogMessage.getHeader().get(ESBServiceConstant.PASSWORD));
			CommonExceptionInfoTrans transfer = new CommonExceptionInfoTrans();
			CommonExceptionInfo ex = (CommonExceptionInfo)transfer.toMessage(esbLogMessage.getBody());
			//EXCPTNCODE, EXCPTNTYPE, MSG, EXCPTNCREATETIME, DETAIL, HOSTIP, CREATETIME
			ps.setObject(18, ex.getExceptioncode());
			ps.setObject(19,ex.getExceptiontype());
			ps.setObject(20, ex.getMessage());
//			ps.setTimestamp(21, new Timestamp(ex.getCreatedTime().getTime()));
			if (esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME)!=null) {
//				Date d = new Date(((java.util.Date)esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME)).getTime());
//				Date d = (Date)esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME);
				ps.setTimestamp(21, new Timestamp(((java.util.Date)esbLogMessage.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME)).getTime()));
			}else{
				ps.setTimestamp(21, new Timestamp(new java.util.Date().getTime()));
			}
			ps.setObject(22, ex.getDetailedInfo());
			ps.setObject(23, IPUtils.getSystemIP());
			ps.setTimestamp(24, new Timestamp((new java.util.Date()).getTime()));
			ps.addBatch();
		}
		return ps;
	}

}
