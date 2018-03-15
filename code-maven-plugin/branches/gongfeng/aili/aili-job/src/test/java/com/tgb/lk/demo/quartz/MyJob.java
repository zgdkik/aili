package com.tgb.lk.demo.quartz;

import java.util.Date;

public class MyJob {

	public void work() {
		System.out.println("date:" + new Date().toString());
	}
}
