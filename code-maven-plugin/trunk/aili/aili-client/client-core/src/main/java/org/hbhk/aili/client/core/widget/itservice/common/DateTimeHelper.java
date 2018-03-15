package org.hbhk.aili.client.core.widget.itservice.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class DateTimeHelper {
	private static final Log LOG = LogFactory.getLog(DateTimeHelper.class);
    private DateTimeHelper(){
    }
    
    /**
     * <p>Description:[根据转化表达式进行转换日期]</p>
     * @param formatPattern 表达式
     * @return String .
     */
    public static String formatDate(Date date, String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        return sdf.format(date);
    }
    
    /**
     * 
     * <p>Description:[字符日期转换Date 对象日期]</p>
     */
    public static Date getDateFromString(String value) {
    	return getDateFromString(value, Locale.getDefault());
    }
    
    /**
     * 
     * <p>Description:[字符日期转换Date 对象日期]</p>
     */
    public static Date getDateFromString(String value, Locale locale) {
		Date result = null;
		DateFormat[] dfs = getDateFormats(locale);

		DateFormat df = null;
		for (DateFormat df1 : dfs) {
			try {
				result = df1.parse(value);
				df = df1;
				if (result != null) {
					break;
				}
			} catch (ParseException ignore) {
				LOG.error(ignore.getMessage(),ignore);
			}
		}
		
		if (df == null) {
			df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		}
		try {
			df.setLenient(false);
			result = df.parse(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return result;
    }
    
    /**
     * 
     * <p>Description:[日期转换表达式集合]</p>
     */
    private static DateFormat[] getDateFormats(Locale locale) {
//		DateFormat dt1 = 
//				DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, locale);
		DateFormat dt2 = 
				DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale);
		DateFormat dt3 = 
				DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);

		DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		DateFormat d2 = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		DateFormat d3 = DateFormat.getDateInstance(DateFormat.LONG, locale);
		DateFormat d4 = new SimpleDateFormat("yyyy-MM-dd");

		DateFormat rfc3399 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		DateFormat dt4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		

		// 添加yyyy-MM-dd HH:mm:ss格式的日期转换
		DateFormat[] dfs = { dt2, dt3, rfc3399, dt4, d1, d2, d3, d4};
		return dfs;
	}
}
