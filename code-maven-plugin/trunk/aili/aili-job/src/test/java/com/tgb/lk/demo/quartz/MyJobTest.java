package com.tgb.lk.demo.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJobTest {

	public static void main(String[] args) {
		try {
			Date d = new Date(1457753548077l);
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(d));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
