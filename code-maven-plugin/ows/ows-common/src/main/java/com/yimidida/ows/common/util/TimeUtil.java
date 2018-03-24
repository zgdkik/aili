package com.yimidida.ows.common.util;

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
	
	public static String formatTimeEight(String time) throws Exception {
      Date d = null;
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      d = sd.parse(time);
      long rightTime = (long) (d.getTime() + 8 * 60 * 60 * 1000); //把当前得到的时间用date.getTime()的方法写成时间戳的形式，再加上8小时对应的毫秒数
      String newtime = sd.format(rightTime);//把得到的新的时间戳再次格式化成时间的格式
      return newtime;
   }
}
