package org.hbhk.aili.client.core.widget.identify;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.widget.identify.impl.WindowsX86CpuIdGenerator;
import org.hbhk.aili.client.core.widget.identify.impl.WindowsX86MacGenerator;

/**
 * Description: 客户机认证码生成器工具类
 */
public final class IdentifyUtil {
	private static final Log log = LogFactory.getLog(IdentifyUtil.class);
	private static final String X86_IDENTIFY_IMPL = WindowsX86CpuIdGenerator.class.getName();
	private static final String IDENTIFY_IMPL = "framework.client.widget.identify.impl";
	
	
	private IdentifyUtil(){
		
	}
	/**
	 * 
	 * <p>Title: getIdentifyCpuId</p>
	 * <p>Description: 获取 CPU序列号认证码</p>
	 * @return
	 * @throws IdentifyException 异常
	 */
	public static String getIdentifyCpuId(){
		String implCls = System.getProperty(IDENTIFY_IMPL);
		if (null == implCls) {
			implCls = X86_IDENTIFY_IMPL;
		}
		try {
			WindowsX86CpuIdGenerator generate = (WindowsX86CpuIdGenerator)
			Thread.currentThread().getContextClassLoader().loadClass(implCls).newInstance();
			return generate.generateCpuId();
		} catch(Exception t) {
			throw new RuntimeException(t);
		}
	}
	
	/**
	 * 
	 * <p>Title: getIdentifyMacList</p>
	 * <p>Description: 获取 MAC地址认证码</p>
	 * @return
	 * @throws IdentifyException 异常
	 */
	public static List<String> getIdentifyMacList() {
		WindowsX86MacGenerator generator = new WindowsX86MacGenerator();
		return generator.generateMacList();
	}

	/**
	 * 
	 * <p>Title: main</p>
	 * <p>Description: 测试函数</p>
	 * @param args 参数
	 */
	public static void main(String[] args) {
		String cpuIdCode = null;
		List<String> macList;
		try {
			cpuIdCode = IdentifyUtil.getIdentifyCpuId();
			System.out.println("cpuIdCode : " + cpuIdCode);
			
			macList = IdentifyUtil.getIdentifyMacList();
			if(macList != null) {
				for(int i = 0; i< macList.size(); i++) {
					System.out.println("MAC " + i + ": " + macList.get(i));
				}
			}
		} catch (Exception e) {
			System.err.println("获取认证信息失败");
			e.printStackTrace();
		}
	}
}