package org.hbhk.aili.esb.server.common.utils;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;


/**
 * 日期转换工具类，用于在Calender、Date以及相应子类间的转换；.
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:59:18
 */
public class DateConverterUtil {
	
	/** The log. */
	private static Logger log = Logger.getLogger(DateConverterUtil.class);
	
	/**
	 * Convert to xml gregorian calendar.
	 * 
	 * @param date
	 *            the date
	 * @return the xML gregorian calendar
	 * @fuanction Date对象转换为Calender
	 * @author qiancheng
	 * @date 2012-12-20 下午2:15:46
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
        	log.error("", e);
        }
        return gc;
    }
    
    /**
	 * fuanction：Calender转换为Date.
	 * 
	 * @param cal
	 *            the cal
	 * @return the date
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午2:16:30
	 */
    public static Date convertToUtilDate(XMLGregorianCalendar cal) throws Exception{
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }
    
    /**
	 * fuanction calender转换为java.sql.Date
	 * 
	 * @param cal
	 *            the cal
	 * @return the java.sql. date
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午2:57:39
	 */
    public static  java.sql.Date convertToSqlDate(XMLGregorianCalendar cal) throws Exception{
        GregorianCalendar ca = cal.toGregorianCalendar();
        return new java.sql.Date(ca.getTime().getTime());
    }
    /**
     * 
     * fuanction XMLGregorianCalendar 转换为java.sql.Timestamp
     * @author qiancheng
     * @param 
     * @date 2013-3-21 上午9:09:23
     * @return
     */
    public static java.sql.Timestamp  convertToTimeStamp(XMLGregorianCalendar cal)throws Exception{
    	if(cal == null){
    		throw new IllegalArgumentException("arg could not be null");
    	}
    	  GregorianCalendar ca = cal.toGregorianCalendar();
    	return new java.sql.Timestamp(ca.getTime().getTime());
    }
}
