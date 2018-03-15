package org.hbhk.aili.client.core.widget.print.util;


import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.widget.print.exception.PrintException;

/**
 * 
 * Description: 普通报表打印工具类，基于JasperReport开源类库实现
 * 
 */
public class PlainPrintUtil<T extends Collection<?>> {
	private static final Log log = LogFactory.getLog(PlainPrintUtil.class);
	private final String suffixXML = ".jrxml";
	private final String suffixJasper = ".jasper";
	@SuppressWarnings("unused")
	private final String suffixPrint = ".jrprint";
	
	private JasperDesign jasperDesign;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	
	private JRBeanCollectionDataSource jrBean;	 // JavaBean 数据源，即要套打的数据对象
	private Map params;          // 打印参数	
	
	/**
	 * 给定报表模板文件的路径，背景图片，打印数据，展现预览界面
	 */
	public void viewFromFile(String templatePath, String bgImage, T beanList) throws PrintException {
		if (templatePath == null || templatePath.trim().length() == 0) {
			throw new PrintException("文件名为空");
		}
		
		String suffix = this.parseSuffix(templatePath);
		log.info("templatePath: " + templatePath + ", suffix: " + suffix);
		
		if (suffix.equalsIgnoreCase(this.suffixXML)) {
			try {
				jasperDesign = JRXmlLoader.load(templatePath);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("加载模板文件出错",e);
			}
			
			try {
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("编译模板出错",e);
			}
			
			try {
				// 在jasper文件中填充数据
				params = new HashMap<Object, Object>();
				params.put("bgImage", bgImage);
				params.put("printBg", true);
				jrBean = new JRBeanCollectionDataSource(beanList);
				jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBean);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("填充数据出错",e);
			}
			
			// 预览报表
			try {
				JasperViewer.viewReport(jasperPrint);
			} catch (Exception e) {
				e.printStackTrace();
				throw new PrintException("预览表单出错",e);
			}
		} else if (suffix.equalsIgnoreCase(this.suffixJasper)) {
			try {
				// 在jasper文件中填充数据
				params = new HashMap<Object, Object>();
				params.put("bgImage", bgImage);
				params.put("printBg", true);
				jrBean = new JRBeanCollectionDataSource(beanList);
				jasperPrint = JasperFillManager.fillReport(templatePath, params, jrBean);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("填充数据出错",e);
			}
			
			// 预览报表
			try {
				JasperViewer.viewReport(jasperPrint);
			} catch (Exception e) {
				e.printStackTrace();
				throw new PrintException("预览表单出错",e);
			}
		} else {
			throw new PrintException("不支持的文件类型，只接受.jrxml或.jasper文件");
		}
	}
	
	/**
	 * 从输入流中接受模板，生成预览视图
	 * @param inputStream 
	 * @param type 
	 * @throws PrintException 
	 */
	public void viewFromInputStream(InputStream templateStream, TemplateType type, InputStream bgImage, T beanList) throws PrintException {
		if (templateStream == null) {
			throw new PrintException("报表模板为空");
		}
		
		if (type == TemplateType.JRXML) {
			// 编译jrxml模板文件 并在jrxml所在的目录中生成jasper文件
			try {
				jasperReport = JasperCompileManager.compileReport(templateStream);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("编译模板出错",e);
			}
			
			try {
				// 在jasper文件中填充数据
				params = new HashMap<Object, Object>();
				params.put("bgImage", bgImage);
				params.put("printBg", true);
				jrBean = new JRBeanCollectionDataSource(beanList);
				jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBean);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("填充数据出错",e);
			}
			
			// 预览报表
			try {
				JasperViewer.viewReport(jasperPrint);
			} catch (Exception e) {
				e.printStackTrace();
				throw new PrintException("预览表单出错");
			}
		} else if (type == TemplateType.JASPER) {
			try {
				// 在jasper文件中填充数据
				params = new HashMap<Object, Object>();
				params.put("bgImage", bgImage);
				params.put("printBg", true);
				jrBean = new JRBeanCollectionDataSource(beanList);
				jasperPrint = JasperFillManager.fillReport(templateStream, params, jrBean);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("填充数据出错",e);
			}
			
			// 预览报表
			try {
				JasperViewer.viewReport(jasperPrint);
			} catch (Exception e) {
				e.printStackTrace();
				throw new PrintException("预览表单出错",e);
			}
		} else {
			throw new PrintException("不支持的文件类型");
		}
	}
	
	/**
	 * 解析一个文件路径名称，
	 * 从中取出不带后缀名的文件路径
	 */
	public String parsePath(String path) {
		if (path == null || path.trim().length() == 0) {
			return null;
		}
		
		String fileName = null;
		if (path.lastIndexOf(".") != -1) {
			fileName = path.substring(0, path.lastIndexOf(".")).trim();
		}
		
		return fileName;
	}

	/**
	 * 解析一个文件路径名称，
	 * 从中取出文件的后缀名
	 */
	public String parseSuffix(String path) {
		if (path == null || path.trim().length() == 0) {
			return null;
		}

		String suffix = null;
		if (path.lastIndexOf(".") != -1) {
			suffix = path.substring(path.lastIndexOf(".")).trim();
		}

		return suffix;
	}

}
