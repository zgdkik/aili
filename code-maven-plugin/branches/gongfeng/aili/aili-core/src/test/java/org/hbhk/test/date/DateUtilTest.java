package org.hbhk.test.date;

import java.util.Date;

import org.hbhk.aili.core.share.util.DateUtil;

public class DateUtilTest {

	
	public static void main(String[] args) {
		String str = DateUtil.convertToXMLGregorianCalendar(new Date()).toXMLFormat();
		String str1 = DateUtil.convertToXMLGregorianCalendar(new Date()).toXMLFormat();
		System.out.println(str);
	}
}
