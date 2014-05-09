package org.hbhk.aili.job.share.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class QuartzMapper implements ResultSetExtractor<List<QuartzEntity>> {

	@Override
	public List<QuartzEntity> extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		QuartzEntity q = null;
		List<QuartzEntity> qs = new ArrayList<QuartzEntity>();
		while (rs.next()) {
			q = new QuartzEntity();
			String jobName = rs.getString("JOB_NAME");
			q.setJobNname(jobName);
			String trggerName = rs.getString("TRIGGER_NAME");
			q.setTrggerName(trggerName);
			q.setDesc(rs.getString("DESCRIPTION"));
			q.setEndTime(rs.getLong("END_TIME"));
			q.setNextFireTime(rs.getLong("NEXT_FIRE_TIME"));
			q.setPrevFireTime(rs.getLong("PREV_FIRE_TIME"));
			q.setStartTime(rs.getLong("START_TIME"));
			q.setTriggerState(rs.getString("TRIGGER_STATE"));
			q.setTriggerType(rs.getString("TRIGGER_TYPE"));
			qs.add(q);

		}
		return qs;
	}

}
