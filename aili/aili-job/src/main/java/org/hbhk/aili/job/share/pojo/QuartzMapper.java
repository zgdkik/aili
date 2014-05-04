package org.hbhk.aili.job.share.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class QuartzMapper implements  ResultSetExtractor<List<QuartzEntity>> {

	@Override
	public List<QuartzEntity> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		QuartzEntity q = null;
		List<QuartzEntity> qs = new ArrayList<QuartzEntity>();
		while (rs.next()) {
			q = new QuartzEntity();
			String  jobName = rs.getString("JOB_NAME");
			q.setJob_name(jobName);
			String  TRIGGER_NAME = rs.getString("TRIGGER_NAME");
			q.setTRIGGER_NAME(TRIGGER_NAME);
			q.setDESCRIPTION(rs.getString("DESCRIPTION"));
			q.setEND_TIME(rs.getLong("END_TIME"));
			q.setNEXT_FIRE_TIME(rs.getLong("NEXT_FIRE_TIME"));
			q.setPREV_FIRE_TIME(rs.getLong("PREV_FIRE_TIME"));
			q.setSTART_TIME(rs.getLong("START_TIME"));
			q.setTRIGGER_STATE(rs.getString("TRIGGER_STATE"));
			q.setTRIGGER_TYPE(rs.getString("TRIGGER_TYPE"));
			
			qs.add(q);
			
		}
		return qs;
	}


}
