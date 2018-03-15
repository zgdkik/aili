package org.hbhk.aili.esb.server.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * IP工具类.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午9:40:00
 */
public class IPUtils {
	
	/** The log. */
	private static Log log = LogFactory.getLog(IPUtils.class);
	
	/**
	 * 得到当前系统的IP.
	 * 
	 * @return the system ip
	 * @author HuangHua
	 * @date 2012-12-21 上午9:40:08
	 */
	public static String getSystemIP(){
		
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("获取IP失败！！！", e);
		}
		return ip;
	}

}