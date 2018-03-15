package org.hbhk.aili.esb.server.common.log.db.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.server.common.log.db.IEsbLogPersistence;
import org.hbhk.aili.esb.server.common.log.db.IPreparedStatementSetter;
import org.hbhk.aili.esb.server.common.utils.jms.EsbLogMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EsbLogPersistenceImpl implements IEsbLogPersistence {

	private final Log LOG = LogFactory.getLog(EsbLogPersistenceImpl.class);
	
	private JdbcTemplate jdbcTemplate;

	private int batchSize = 1000;

	private IPreparedStatementSetter preparedStatementSetter;

	public EsbLogPersistenceImpl() {
	}

	public EsbLogPersistenceImpl(JdbcTemplate jdbcTemplate, int batchSize,
			IPreparedStatementSetter preparedStatementSetter) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.batchSize = batchSize;
		this.preparedStatementSetter = preparedStatementSetter;
	}

	@Override
	public void save(List<EsbLogMessage> messages) {
		if (messages == null || messages.size() < 1) {
			LOG.warn(String.format("%s method %s invoke args is null", EsbLogPersistenceImpl.class.getName(),"save"));
			return;
		}
		//TODO 此处应该考虑批量存储失败之后再进行单条存储
		jdbcTemplate.execute(preparedStatementSetter.getSql(),
				new BatchPreparedStatementSetter(messages, batchSize,
						preparedStatementSetter));
	}
	
	class BatchPreparedStatementSetter implements
			PreparedStatementCallback<int[]> {

		private int size = 1000;
		private List<EsbLogMessage> messages;

		private IPreparedStatementSetter psSetter;

		public BatchPreparedStatementSetter(final List<EsbLogMessage> messages,
				int batchSize, IPreparedStatementSetter preparedStatementSetter) {
			this.messages = messages;
			if (messages.size() < batchSize) {
				this.size = messages.size();

			} else {
				this.size = batchSize;
			}
			this.psSetter = preparedStatementSetter;
		}

		@Override
		public int[] doInPreparedStatement(PreparedStatement ps)
				throws SQLException, DataAccessException {
			try {
				if (JdbcUtils.supportsBatchUpdates(ps.getConnection())) {
					for (int i = 0; i < messages.size(); i++) {
						// EsbLogMessage logMessage = messages.get(i);
						//TODO 收集每次ps.executeBatch()的结果
						psSetter.setValues(ps, messages.get(i));
						if (i!=0 && i % size == 0) {
							ps.executeBatch();
						}
					}
					return ps.executeBatch();
				} else {
					List<Integer> rowsAffected = new ArrayList<Integer>();
					for (int i = 0; i < batchSize; i++) {
						rowsAffected.add(ps.executeUpdate());
					}
					int[] rowsAffectedArray = new int[rowsAffected.size()];
					for (int i = 0; i < rowsAffectedArray.length; i++) {
						rowsAffectedArray[i] = rowsAffected.get(i);
					}
					return rowsAffectedArray;
				}
			} catch (Exception e) {
				throw new SQLException(e.getMessage());
			}
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public IPreparedStatementSetter getPreparedStatementSetter() {
		return preparedStatementSetter;
	}

	public void setPreparedStatementSetter(
			IPreparedStatementSetter preparedStatementSetter) {
		this.preparedStatementSetter = preparedStatementSetter;
	}

}
