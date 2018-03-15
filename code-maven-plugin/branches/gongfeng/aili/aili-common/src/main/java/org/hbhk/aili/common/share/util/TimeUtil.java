package org.hbhk.aili.common.share.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");

	private static SimpleDateFormat imgsdf = new SimpleDateFormat("yyyyMMddHHmmss");

	public static String getImgCurrentTime() {
		Date date = new Date();
		return imgsdf.format(date);
	}
	
	/**
	 * 
	 * Description: 获取任意时间的前一个小时
	 * @param date
	 * @return
	 * Created by zhangjianfeng 2016年2月26日
	 */
	public static String beforeOneHourToNowDate(String date){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		return sdf.format(calendar.getTime());
	} 
	
	/**
	 * 
	 * Description: 获取任意时间的后一个小时
	 * @param date
	 * @return
	 * Created by zhangjianfeng 2016年2月26日
	 */
	public static String afterOneHourToNowDate(String date){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
		return sdf.format(calendar.getTime());
	} 
}
