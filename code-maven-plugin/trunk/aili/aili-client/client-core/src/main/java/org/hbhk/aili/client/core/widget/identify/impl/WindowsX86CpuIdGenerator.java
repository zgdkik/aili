package org.hbhk.aili.client.core.widget.identify.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import org.hbhk.aili.client.core.component.remote.IOUtils;

/**
 * Description: WindowsX86 型客户端 CPU序列号生成器类
 */
public class WindowsX86CpuIdGenerator {

	private static String dllMsvcr = "msvcr100.dll";
	private static String dllCpu = "CPU_Reader.dll";

	// 静态方法加载动态链接库
	static {
		try {
			loadDll2System(dllMsvcr); // 首先加载依赖项msvcr100.dll
			loadDll0(dllCpu); // 加载CPU_Reader.dll
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	/**
	 * 默认构造器
	 */
	public WindowsX86CpuIdGenerator() {
	}

	/**
	 * JNI 本地方法定义 该方法通过运行DLL中C++方法读取CPU序列号
	 */
	public native String readCpuId();

	/**
	 * @throws IOException 
	 * 
	 * @Title:loadDll0
	 * @Description:加载dll文件
	 * @param @param dllName
	 * @param @throws Throwable
	 * @return void
	 * @throws
	 */
	static void loadDll0(final String dllName) throws IOException {
		URL url = WindowsX86CpuIdGenerator.class.getResource(dllName);

		File temporaryDll = File.createTempFile("jacob", ".dll");
		OutputStream os = null;
		try {
			InputStream inputStream = url.openStream();

			os = new FileOutputStream(temporaryDll);
			byte[] array = new byte[8192];
			for (int i = inputStream.read(array); i != -1; i = inputStream.read(array)) {
				os.write(array, 0, i);
			}
		} finally {
			if (os != null) {
				os.close();
			}
		}

		temporaryDll.deleteOnExit();
		System.load(temporaryDll.getPath());
	}

	/**
	 * @throws IOException 
	 * 
	 * @Title:loadDll2System
	 * @Description:把dll文件加载到系统路径下
	 * @param @param dllName
	 * @param @throws Throwable
	 * @return void
	 * @throws
	 */
	static void loadDll2System(final String dllName) throws IOException  {
		URL url = WindowsX86CpuIdGenerator.class.getResource(dllName);
		String system32Path = getSystemPath();
		File file = new File(system32Path + File.separator + dllName);
		InputStream inputStream = url.openStream();
		FileOutputStream outputStream = null;
		try{
			outputStream = new FileOutputStream(file);
			byte[] array = new byte[8192];
			for (int i = inputStream.read(array); i != -1; i = inputStream.read(array)) {
				outputStream.write(array, 0, i);
			}
		}finally{
			IOUtils.close(outputStream);
		}
		
		
	}

	/**
	 * 客户端CPU序列号生成
	 */
	public String generateCpuId(){
		String cpuId = null;
		try {
			cpuId = this.readCpuId();
		} catch (UnsatisfiedLinkError error) {
			error.printStackTrace();
			throw new RuntimeException("生成CPU序列号出错",error);
		}
		return cpuId;
	}

	/**
	 * 
	 * @Title:getSystemPath
	 * @Description:此方法只适用于windows操作系统
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getSystemPath() {
		String systemPath = "";
		BufferedReader reader = null;
		try {
			Process proc = Runtime.getRuntime().exec("cmd /c echo %SystemRoot%");
			reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String lineContent = null;
			while ((lineContent = reader.readLine()) != null) {
				systemPath = lineContent.trim();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return systemPath;
	}

}
