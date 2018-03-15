package org.hbhk.aili.client.core.commons.util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 与服务器时间同步,在X200上测试,设置一次时间的开销是50ms左右
 *
 */
public class DateTimeSynchronizeService {

	public static final long DATETIME_DIFF = 60 * 60 * 1000; // 60分钟
	public static final String TIME_ZONE_STANDARD_ID = "Asia/Shanghai";
	public static final int HINT_VALUE = 6; // 随机数命中的值
	public static final int MAX_VALUE = 100; // 随机数取值的范围(0 ~ MAX_VALUE - 1)
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_WINDOWS = "yyyy-MM-dd";
	public static final String DATE_FORMAT_LINUX = "yyyyMMdd";
	public static final String TIME_FORMAT = "HH:mm:ss";

	public static TimeZone timeZoneStandard = TimeZone
			.getTimeZone(TIME_ZONE_STANDARD_ID);
	private static Log log = LogFactory
			.getLog(DateTimeSynchronizeService.class);

	final Object lock = new Object();
	private Timer timer;
	private InnerTimerWorker innerTimerWorker;

	private boolean started = false;
	
	private DateTimeSynchronizeService() {
		timer = new Timer();
		innerTimerWorker = new InnerTimerWorker();
	}

	static class DateTimeSynchronizeServiceHolder {
		static DateTimeSynchronizeService dateTimeSynchronizeService = new DateTimeSynchronizeService();
	}

	public static DateTimeSynchronizeService getInstance() {
		return DateTimeSynchronizeServiceHolder.dateTimeSynchronizeService;
	}

	/**
	 * 产生一个随机数,不需要每次都执行,提供1/100的几率执行
	 * 
	 * @return
	 */
	private boolean canSynchronizeByRandom() {
		int random = ((int) (Math.random() * MAX_VALUE) % MAX_VALUE);
		if (HINT_VALUE != random)
			return false;
		else
			return true;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 * @throws Exception 
	 */
	public Date getCurrentDateTime() throws Exception {
		Date localTime = new Date();
		// localTime.before(getInnerDateTime())
		if (getInnerDateTime().getTime() - localTime.getTime() > DATETIME_DIFF) {
			// 本机系统时间早于内部时钟,大于容差
			throw new Exception("请校准本机时间");
		} else {
			return localTime;
		}
	}

	/**
	 * 比较时间差异,大于阀值执行
	 * 
	 * @param serverTime
	 * @return
	 */
	public boolean canSynchronizeByDiff(Date serverTime) {
		Date localTime = new Date();

		// 检查是否在同一时区
		TimeZone tzClient = TimeZone.getDefault();

		// 检查本地时间是否标准时区
		if (!tzClient.getID().equals(TIME_ZONE_STANDARD_ID)) {
			log.warn("本机非标准GMT+8:00时区,不更新本地时间; TimeZoneStandard="
					+ TIME_ZONE_STANDARD_ID + ", TimeZoneClient="
					+ tzClient.getID());
			return false;
		}

		if (Math.abs(localTime.getTime() - serverTime.getTime()) > DATETIME_DIFF) {
			return true;
		} else
			return false;
	}

	/**
	 * 操作过程中随机执行
	 * 
	 * @param serverTime
	 */
	public void dateTimeRandomSynchronize(Date serverTime) {
		if (canSynchronizeByRandom() && canSynchronizeByDiff(serverTime)) {
			synchronized (lock) {
				synchronizeSystemDateTime(serverTime);

				// 内部时钟设新值
				innerTimerWorker.setDatumTime(serverTime);
			}

		}
	}

	/**
	 * 首次执行.登录成功
	 * 
	 * @param serverTime
	 */
	public void dateTimeInitSynchronize(Date serverTime) {
		if (canSynchronizeByDiff(serverTime)) {
			synchronized (lock) {
				log.info("客户机与服务器时钟不一致,serverTime:"
						+ this.convert(serverTime,
								this.DATE_TIME_FORMAT, null)
						+ ",localTime:"
						+ this.convert(new Date(),
								this.DATE_TIME_FORMAT, null));

				synchronizeSystemDateTime(serverTime);

			}
			
			if(!started){
				// 启动内部时钟
				started = true;
				innerTimerWorker.setDatumTime(serverTime);
				timer.schedule(innerTimerWorker, 59 * 1000, 60 * 1000); 
				
				// 调快1秒,算作网络传输的损耗,之后每秒加1秒
			}
		}
	}
	
	public String convert(Date date, String format, TimeZone timeZone) {
		if (null != date) {

			if (StringUtils.isEmpty(format)) {
				format = DATE_TIME_FORMAT;
				log.info("format 未赋值或非法");
			}

			if (null == timeZone) {
				timeZone = timeZoneStandard;
				log.info("timeZone 未赋值,取中国标准时区");
			}

			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setTimeZone(timeZone);
			return sdf.format(date);

		}
		return null;
}

	private void synchronizeSystemDateTime(Date serverTime) {
		// Operating system name
		String osName = System.getProperty("os.name");
		String cmd = "";

		try {

			if (osName.matches("^(?i)Windows.*$")) {
				// Windows 系统. 域策略会有权限控制的问题
				// 格式：yyyy-MM-dd
				String strServerDate = this.convert(serverTime,
						this.DATE_FORMAT_WINDOWS, null);
				String strServerTime = this.convert(serverTime,
						this.TIME_FORMAT, null);

				cmd = " cmd /c date " + strServerDate;
				Runtime.getRuntime().exec(cmd);

				// 格式 HH:mm:ss
				cmd = " cmd /c time " + strServerTime;
				Runtime.getRuntime().exec(cmd);

				log.info("设置本机时间:" + strServerDate + "," + strServerTime);
			}

			// else {
			// // Linux 系统 .有权限控制的问题
			// // 格式：yyyyMMdd
			// String strServerDate = DateUtils.convert(
			// serverTime, DATE_FORMAT_LINUX);
			// cmd = " date -s " + strServerDate;
			// Runtime.getRuntime().exec(cmd);
			// // 格式 HH:mm:ss
			// cmd = " date -s " + strServerTime;
			// Runtime.getRuntime().exec(cmd);
			// }

		} catch (Throwable e) {
			log.error("DateTimeSynchronize", e);
		}

	}

	public Date getInnerDateTime() {
		return innerTimerWorker.getDatumTime().getTime();
	}

	public static void main(String[] args) {

	}
	
	
	public class InnerTimerWorker extends TimerTask {

		private Calendar datumTime; // 基准时间

		public InnerTimerWorker() {
			this.datumTime = Calendar.getInstance();
		}

		public InnerTimerWorker(Date datumTime) {
			this.datumTime = Calendar.getInstance();
			this.datumTime.setTime(datumTime);
		}

		public Calendar getDatumTime() {
			return datumTime;
		}

		public void setDatumTime(Calendar datumTime) {
			this.datumTime = datumTime;
		}

		public void setDatumTime(Date datumTime) {
			this.datumTime.setTime(datumTime);
		}

		public void run() {
			// 加1
			datumTime.add(Calendar.MINUTE, 1);

		}


	}

}
