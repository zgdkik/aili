package com.feisuo.sds.org.test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AppTest {

	
	public static void main(String[] args) {
		
		String sql = "SELECT QRTZ_JOB_DETAILS.JOB_NAME JOB_NAME ,QRTZ_TRIGGERS.TRIGGER_NAME TRIGGER_NAME"
				+ ",NEXT_FIRE_TIME,PREV_FIRE_TIME,TRIGGER_STATE,TRIGGER_TYPE,START_TIME,END_TIME"
				+ ",QRTZ_JOB_DETAILS.DESCRIPTION  DESCRIPTION from QRTZ_TRIGGERS inner join QRTZ_JOB_DETAILS "
				+ " on QRTZ_TRIGGERS.JOB_NAME = QRTZ_JOB_DETAILS.JOB_NAME";
			sql = sql + " WHERE QRTZ_JOB_DETAILS.JOB_NAME = ?"
					+ " order by start_time";
			System.out.println(sql);
			Date date = new Date(1456480800000l);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println(sdf.format(date));
			System.out.println();
	}
}
