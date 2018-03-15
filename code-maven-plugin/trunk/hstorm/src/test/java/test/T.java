package test;

import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.time.DateUtils;

public class T {

	public static void main(String[] args) {
		String ss = "d6af97e615744100a61db8fa32661e12";
		System.out.println(SerializationUtils.serialize(ss));
		Date d1 = new Date();
		Date d2 = DateUtils.addDays(d1, 1);
		System.out.println(d1.after(d2));
		
	}

}
