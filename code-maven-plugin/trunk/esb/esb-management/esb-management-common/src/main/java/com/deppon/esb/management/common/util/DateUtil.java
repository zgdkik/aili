package com.deppon.esb.management.common.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 一期移植
 * 
 * 日期转换工具类
 * @author 钱城
 *
 */
public class DateUtil {
	/**
	 * Date 转换为XMLGregorianCalendar
	 * @param date
	 * @return
	 * @throws DatatypeConfigurationException 
	 */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        return gc;
    }
	 /**
	  * XMLGregorianCalendar 转换为Date
	  * @param cal
	  * @return
	  * @throws Exception
	  */
     public static Date convertToDate(XMLGregorianCalendar cal) throws Exception{
         GregorianCalendar ca = cal.toGregorianCalendar();
         return ca.getTime();
     }
}
