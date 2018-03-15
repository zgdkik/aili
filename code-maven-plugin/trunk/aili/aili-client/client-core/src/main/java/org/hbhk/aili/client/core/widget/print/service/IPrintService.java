/**
 * 
 */
package org.hbhk.aili.client.core.widget.print.service;

import java.io.InputStream;

import org.hbhk.aili.client.core.widget.print.exception.PrintException;

/**
 * Description: 打印服务接口
 */
public interface IPrintService {	
	/**
	 * 
	 * <p>Title: viewFromFile</p>
	 * <p>Description: 从文件来源预览报表或清单</p>
	 * @param filePath 文件路径
	 * @throws PrintException 打印异常
	 */
	public void viewFromFile(String filePath) throws PrintException;

	/**
	 * 
	 * <p>Title: viewFromInputStream</p>
	 * <p>Description: 从输入流来源预览报表或清单</p>
	 * @param inputStream 输入流
	 * @throws PrintException 打印异常
	 */
	public void viewFromInputStream(InputStream inputStream) throws PrintException;
	
	/**
	 * 
	 * <p>Title: viewFromInputStream</p>
	 * <p>Description: 从输入流来源预览报表或清单
	 * type : 文件类别； 1：jrxml；2：jasper；3：jrprint；</p>
	 * @param inputStream 输入流
	 * @param type 文件类别
	 * @throws PrintException 打印异常
	 */
	public void viewFromInputStream(InputStream inputStream, int type) throws PrintException;
}
