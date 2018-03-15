package org.hbhk.aili.client.main.client.ui;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Calculator {
	
	/**
	 * log 从日志工厂获取日志
	 */
	private static final Log LOG = LogFactory.getLog(Calculator.class);
	
	/**
	 * time task 
	 *
	 */
	public static class MyTimerTask extends TimerTask {

		/**
		 * 构造方法
		 * @param tm
		 */
		public MyTimerTask() {
		}

		/**
		 * run method for task
		 */
		public void run() {
			try {
				//exec windows calculator
			    	/**
				 * 启动
				 */
				Runtime.getRuntime().exec("calc.exe");
			} catch (Exception ex) {
				LOG.error("exception", ex);
			}

		}

	}

}