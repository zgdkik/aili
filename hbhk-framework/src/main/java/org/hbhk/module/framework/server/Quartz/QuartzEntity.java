package org.hbhk.module.framework.server.Quartz;

import java.util.Date;

public class QuartzEntity {

	private String job_name;
	private String TRIGGER_NAME;

	private long NEXT_FIRE_TIME;
	private long PREV_FIRE_TIME;
	private String TRIGGER_STATE;
	private String TRIGGER_TYPE;

	private long START_TIME;
	private long END_TIME;
	private String DESCRIPTION;

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getTRIGGER_NAME() {
		return TRIGGER_NAME;
	}

	public void setTRIGGER_NAME(String tRIGGER_NAME) {
		TRIGGER_NAME = tRIGGER_NAME;
	}

	public long getNEXT_FIRE_TIME() {
		return NEXT_FIRE_TIME;
	}

	public void setNEXT_FIRE_TIME(long nEXT_FIRE_TIME) {
		NEXT_FIRE_TIME = nEXT_FIRE_TIME;
	}

	public long getPREV_FIRE_TIME() {
		return PREV_FIRE_TIME;
	}

	public void setPREV_FIRE_TIME(long pREV_FIRE_TIME) {
		PREV_FIRE_TIME = pREV_FIRE_TIME;
	}

	public String getTRIGGER_STATE() {
		return TRIGGER_STATE;
	}

	public void setTRIGGER_STATE(String tRIGGER_STATE) {
		TRIGGER_STATE = tRIGGER_STATE;
	}

	public String getTRIGGER_TYPE() {
		return TRIGGER_TYPE;
	}

	public void setTRIGGER_TYPE(String tRIGGER_TYPE) {
		TRIGGER_TYPE = tRIGGER_TYPE;
	}

	
	public long getSTART_TIME() {
		return START_TIME;
	}

	public void setSTART_TIME(long sTART_TIME) {
		START_TIME = sTART_TIME;
	}

	public long getEND_TIME() {
		return END_TIME;
	}

	public void setEND_TIME(long eND_TIME) {
		END_TIME = eND_TIME;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

}
