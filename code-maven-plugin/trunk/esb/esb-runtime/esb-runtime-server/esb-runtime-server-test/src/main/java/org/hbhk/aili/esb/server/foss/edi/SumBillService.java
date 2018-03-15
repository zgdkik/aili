/*
 * jtds驱动包的ResultSet注意事项：
 * 
 * I get java.sql.SQLException: "ResultSet may only be accessed in a forward direction" or "ResultSet is read only" when using a scrollable/updateable ResultSet.
 * 
 * There are three possible causes to this (if we exclude not creating the ResultSet with the appropriate type and concurrency in the first place):
 * 
 * The executed query must be a single SELECT statement or a call to a procedure that consists of a single SELECT statement (even a SET or PRINT will cause the resulting ResultSet to be forward only read only). This is a SQL Server limitation and there's not much jTDS can do about it.
 * 
 * The scroll insensitive/updateable combination is not supported by SQL Server, so such a ResultSet is automatically downgraded to scroll insensitive/read-only by the server. Use the scroll sensitive/updateable combination and it should work.
 * 
 * The other possible cause is that the cursor is keyset-based and either the table you are selecting from does not have a unique primary key or that primary key is not included in your SELECT. See the SQL Server Documentation on cursor types for more information.
 * 
 * In both cases if you call Statement.getWarnings() right after calling executeQuery() you'll get a warning about the ResultSet being downgraded. Also, please take a look at our ResultSet support page for some additional information and tips.
 */
package org.hbhk.aili.esb.server.foss.edi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hbhk.aili.esb.server.common.utils.DateConverterUtil;

import com.deppon.esb.inteface.domain.air.SumBillRequest;

/**
 * 调用EDI上传合票清单存储过程.
 * 
 * @author qiancheng
 */

public class SumBillService {
	private final static Logger LOG = Logger.getLogger(SumBillService.class);

	/** The Constant INDEX1. */
	private static final int INDEX1 = 1;

	/** The Constant INDEX2. */
	private static final int INDEX2 = 2;

	/** The Constant INDEX3. */
	private static final int INDEX3 = 3;

	/** The Constant INDEX4. */
	private static final int INDEX4 = 4;

	/** The Constant INDEX5. */
	private static final int INDEX5 = 5;

	/** The Constant INDEX6. */
	private static final int INDEX6 = 6;

	/** The Constant INDEX7. */
	private static final int INDEX7 = 7;

	/** The Constant INDEX8. */
	private static final int INDEX8 = 8;

	/** The Constant INDEX9. */
	private static final int INDEX9 = 9;

	/** The Constant INDEX10. */
	private static final int INDEX10 = 10;

	/** The Constant INDEX11. */
	private static final int INDEX11 = 11;

	/** The Constant INDEX12. */
	private static final int INDEX12 = 12;
	/**
	 * 存储过程返回值s
	 */
	private static final String STORE_PROCEDURE_RESULT_SUMBILL_EXIST ="-1";
	
	private static final String STORE_PROCEDURE_RESULT_PROXY_NOTEXIST="-2";

	private DataSource dataSource ;
	
	/**
	 * 
	 * 调用存储过程代码
	 * @author qiancheng
	 * @param 
	 * @date 2013-3-25 下午2:24:06
	 * @return
	 */
	public void addSumbill(SumBillRequest info)throws Exception{
		LOG.info("call EDI db procedure start,info:"+this.sumBilltoString(info));
		Connection conn= null;
		CallableStatement callStat = null;
		ResultSet resultSet = null;
		try {
		conn = dataSource.getConnection();
		//conn.setAutoCommit(false);
		java.sql.Timestamp timestamp = DateConverterUtil.convertToTimeStamp(info
				.getSendDate());
			callStat = conn
					.prepareCall("{call add_edi_mail(?,?,?,?,?,?,?,?,?,?,?,?)}");
			// 员工编号
			callStat.setString(INDEX1, info.getCreatorNumber());
			// 寄件人姓名
			callStat.setString(INDEX2, info.getSenderName());
			// 发件日期
			callStat.setTimestamp(INDEX3,timestamp);
			// 主题
			callStat.setString(INDEX4, info.getSubject());
			// 邮件夹名称
			callStat.setString(INDEX5, info.getMailFolderName());
			// 提醒标志
			callStat.setString(INDEX6, info.getNoticeFlag());
			// 已读标志
			callStat.setString(INDEX7, info.getReadFlag());
			// 邮件标志
			callStat.setString(INDEX8, info.getMailFlag());
			// 优先级别
			callStat.setString(INDEX9, info.getPriorityLevel());
			// 邮件大小
			callStat.setInt(INDEX10, info.getMailSize());
			// 附件名称
			callStat.setString(INDEX11, info.getAttachmentName());
			callStat.setString(INDEX12, info.getAttachmentLink());
			resultSet = callStat.executeQuery();
			if(resultSet.next()){
				String mailId= resultSet.getString(1);
				String attactId = resultSet.getString(2);
				if(STORE_PROCEDURE_RESULT_SUMBILL_EXIST.equals(mailId)&& STORE_PROCEDURE_RESULT_SUMBILL_EXIST.equals(attactId)){
					LOG.info("SumBillInfo[creatorNumber:"+info.getCreatorNumber()+
							"; senderName:"+info.getSenderName()+
							"; subject:"+info.getSubject()+"; "+"]has exists in EDI DB");
				}
				LOG.info("call EDI procedure result[ maiId:"+mailId+";attactId:"+attactId+"]");
			}
		//	conn.commit();
		} 
		catch(Exception e){
		//	conn.rollback();
			LOG.error("call EDI procedure fail,SumBillInfo detail:[creatorNumber:"+info.getCreatorNumber()+
					"; senderName:"+info.getSenderName()+
					"; subject:"+info.getSubject()+"; ",e);
		}
		finally{
			closeDataSource(resultSet,callStat,conn);
		}
	}
	/**
	 * 释放数据库资源
	 * @author qiancheng
	 * @param 
	 * @date 2013-5-13 上午11:42:39
	 * @return
	 */
	public void closeDataSource(ResultSet rs ,Statement st,Connection con){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				LOG.error("close resultset fail!", e);
			}
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				LOG.error("close resultset fail!", e);
			}
		}
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				LOG.error("close resultset fail!", e);
			}
		}
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	private String sumBilltoString(SumBillRequest info){
		if(info == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("SumBill[CreatorNumber:").append(info.getCreatorNumber()).append(
				";AttachmentLink:").append(info.getAttachmentLink())
				.append(";AttachmentName:").append(info.getAttachmentName())
				.append(";MailFlag:").append(info.getMailFlag())
				.append(";MailFolderName:").append(info.getMailFolderName())
				.append(";MailSize:").append(info.getMailSize())
				.append(";NoticeFlag:").append(info.getNoticeFlag())
				.append(";PriorityLevel:").append(info.getPriorityLevel())
				.append(";ReadFlag:").append(info.getReadFlag())
				.append(";SenderName:").append(info.getSenderName())
				.append(";Subject:").append(info.getSubject())
				.append(";SendDate:").append(info.getSendDate());
		return buffer.toString();
	}
}
