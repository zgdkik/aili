package com.deppon.esb.management.web.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
public class DateUtil {
	/**
	 * 
	 * 比较日期大小
	 * eg：
	 *  start :2013-2-21 11:20:25  end :2013-2-28 10:00:00 limit :7  true
	 *  start :2013-2-21 11:20:25  end :2013-2-29 10:00:00 limit :7   false
	 *   
	 * @author qiancheng
	 * @param 
	 * @date 2013-2-28 上午11:20:25
	 * @return
	 */
	public static boolean compareDate(Date start,Date end,int limit){
		if(start == null || end ==null){
			throw new IllegalArgumentException("The date must not be null");
		}
		Date d1 = DateUtils.round(start, Calendar.DATE);
		Date d2 = DateUtils.round(end, Calendar.DATE);
		d1 = DateUtils.addDays(d1, limit);
		if(d2.getTime()>=d1.getTime()){
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 02, 21, 10, 31,20);
		Date d1 =cal.getTime();
		cal.set(2013, 02, 28, 9, 31,20);
		Date d2 = cal.getTime();
		System.out.println(compareDate(d1,d2,7));
		System.out.println(compareDate(d2,d1,7));
		
		System.out.println(Integer.MAX_VALUE);
	}
}
