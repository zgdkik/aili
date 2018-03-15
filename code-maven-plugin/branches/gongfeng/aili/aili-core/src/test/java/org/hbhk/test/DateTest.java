package org.hbhk.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.hbhk.aili.core.share.util.EncryptUtil;

public class DateTest {

	
	public static void main(String[] args) throws InterruptedException {
		Date d1 = new Date();
		TimeUnit.SECONDS.sleep(1);
		Date d2 = new Date();
		TimeUnit.SECONDS.sleep(1);
		Date d3 = new Date();
		if(d2.after(d1) &&  d2.before(d3)){
			System.out.println("aaaaaaa");
		}
		
		//343b1c4a3ea721b2d640fc8700db0f36
		System.out.println(EncryptUtil.encodeMD5("qqqqqq"));
	}
}
