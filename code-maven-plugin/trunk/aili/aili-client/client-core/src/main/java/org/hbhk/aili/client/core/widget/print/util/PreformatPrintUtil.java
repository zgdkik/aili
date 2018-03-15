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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.widget.print.exception.PrintException;
import org.hbhk.aili.client.core.widget.print.ui.PreformatViewer;

/**
 * Description: 套打工具类，基于JasperReport开源类库实现
 *
 */
public class PreformatPrintUtil<T extends Collection<?>> {
	private static final Log log = LogFactory.getLog(PreformatPrintUtil.class);
	private final String suffixXML = ".jrxml";
	private final String suffixJasper = ".jasper";
	@SuppressWarnings("unused")
	private final String suffixPrint = ".jrprint";

	private JasperDesign jasperDesign;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;             // 有背景的打印对象
	private JasperPrint preformatJasperPrint;    // 没有背景的打印对象
	private PreformatViewer jasperViewer;	 // 预览界面
	
	private JRBeanCollectionDataSource jrBean;	 // JavaBean 数据源，即要套打的数据对象
	private Map params;  // 打印参数
	private boolean printBg = true;      // 是否打印背景
	
	
	/**
	 * 给定报表模板文件的路径，背景图片，套打JavaBean数据，展现预览界面
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

			// 在JasperReport对象中填充数据并预览
			this.fillReportAndView(jasperReport, bgImage, beanList);
			
			// 生成只有打印数据的JasperPrint对象
			this.setPreformatJasperPrint(beanList);
		} else if (suffix.equalsIgnoreCase(this.suffixJasper)) {
			// 在jasper文件中填充数据
			this.fillReportAndView(templatePath, bgImage, beanList);
			
			// 生成只有打印数据的JasperPrint对象
			this.setPreformatJasperPrint(beanList);
		} else {
			throw new PrintException("不支持的文件类型，只接受.jrxml或.jasper文件");
		}
	}
	
	/**
	 * 给定报表模板文件的路径，背景图片，套打JavaBean数据，展现预览界面
	 */
/*	public void viewFromFile(String templatePath, InputStream bgImage, T beanList) throws PrintException {
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
				throw new PrintException("加载模板文件出错");
			}
			
			try {
				jasperReport = JasperCompileManager.compileReport(jasperDesign);
			} catch (JRException e) {
				e.printStackTrace();
				throw new PrintException("编译模板出错");
			}

			// 在JasperReport对象中填充数据并预览
			this.fillReportAndView(jasperReport, bgImage, beanList);
			
			// 生成只有打印数据的JasperPrint对象
			this.setPreformatJasperPrint(beanList);
		} else if (suffix.equalsIgnoreCase(this.suffixJasper)) {
			// 在jasper文件中填充数据
			this.fillReportAndView(templatePath, bgImage, beanList);			
			
			// 生成只有打印数据的JasperPrint对象
			this.setPreformatJasperPrint(beanList);
		} else {
			throw new PrintException("不支持的文件类型，只接受.jrxml或.jasper文件");
		}
	}*/
	
	/**
	 * 给定报表模板文件流，文件流类型，背景图片，套打JavaBean数据，展现预览界面
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
			
			// 在JasperReport对象中填充数据并预览
			this.fillReportAndView(jasperReport, bgImage, beanList);
			
			// 生成只有打印数据的JasperPrint对象
			this.setPreformatJasperPrint(beanList);
		} else if (type == TemplateType.JASPER) {
			// 在JasperReport文件中填充数据并预览
			this.fillReportAndView(templateStream, bgImage, beanList);
			
			// 生成只有打印数据的JasperPrint对象
			this.setPreformatJasperPrint(beanList);		
		} else {
			throw new PrintException("不支持的文件类型");
		}
	}	
	
	/**
	 * 在给定的已编译模板文件中填充数据并预览
	 * @param beanList
	 * @throws JRException
	 */
	public void fillReportAndView(String templatePath, String bgImage, T beanList) throws PrintException {
		try {
			printBg = true;
			params = new HashMap<Object, Object>();
			params.put("bgImage", bgImage);
			params.put("printBg", printBg);
			jrBean = new JRBeanCollectionDataSource(beanList);
			jasperPrint = JasperFillManager.fillReport(templatePath, params, jrBean);
		} catch (JRException e) {
			e.printStackTrace();
			throw new PrintException("填充数据出错",e);
		}
		
		// 预览报表
		this.viewReport(jasperPrint);
	}

	/**
	 * 在编译完的模板文件流中填充数据并预览
	 * @param beanList
	 * @throws JRException
	 */
	public void fillReportAndView(InputStream templateStream, InputStream bgImage, T beanList) throws PrintException {
		try {
			// 在jasper文件中填充数据
			printBg = true;
			params = new HashMap<Object, Object>();
			params.put("bgImage", bgImage);
			params.put("printBg", printBg);
			jrBean = new JRBeanCollectionDataSource(beanList);
			jasperPrint = JasperFillManager.fillReport(templateStream, params, jrBean);
		} catch (JRException e) {
			e.printStackTrace();
			throw new PrintException("填充数据出错",e);
		}
		
		// 预览报表
		this.viewReport(jasperPrint);
	}
	
	/**
	 * 在模板对象中填充数据并预览
	 * @param beanList
	 * @throws JRException
	 */
	public void fillReportAndView(JasperReport jasperReport, InputStream bgImage, T beanList) throws PrintException {
		try {
			// 在.jasper文件中填充数据
			printBg = true;
			params = new HashMap<Object, Object>();
			params.put("bgImage", bgImage);
			params.put("printBg", printBg);
			jrBean = new JRBeanCollectionDataSource(beanList);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBean);
		} catch (JRException e) {
			e.printStackTrace();
			throw new PrintException("填充数据出错",e);
		}
		
		// 预览报表
		this.viewReport(jasperPrint);
	}

	
	/**
	 * 在模板对象中填充数据并预览
	 * @param jasperReport
	 * @param bgImage
	 * @param beanList
	 * 
	 * @throws JRException
	 */
	public void fillReportAndView(JasperReport jasperReport, String bgImage, T beanList) throws PrintException {
		try {
			// 在.jasper文件中填充数据
			printBg = true;
			params = new HashMap<Object, Object>();
			params.put("bgImage", bgImage);
			params.put("printBg", printBg);
			jrBean = new JRBeanCollectionDataSource(beanList);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBean);
		} catch (JRException e) {
			e.printStackTrace();
			throw new PrintException("填充数据出错",e);
		}
		
		// 预览报表
		this.viewReport(jasperPrint);
	}
	
	
	/**
	 * 显示预览界面，预览报表
	 */
	public void viewReport(JasperPrint jasperPrint) throws PrintException {
		try {
			jasperViewer = new PreformatViewer(jasperPrint, false);
			jasperViewer.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PrintException("预览表单出错",e);
		}
	}
	
	/**
	 * 设置只有打印数据的套打数据对象
	 * @param beanList
	 * @throws JRException
	 */
	public void setPreformatJasperPrint(T beanList) throws PrintException {
		try {
			// 在jasper文件中填充数据,设置不显示背景图片
			printBg = false;
			params = new HashMap<Object, Object>();
			params.put("printBg", printBg);
			jrBean = new JRBeanCollectionDataSource(beanList);
			// 生成只要打印数据的JasperPrint对象
			preformatJasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBean);
			jasperViewer.setPreformatJasperPrint(preformatJasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
			throw new PrintException("填充数据出错",e);
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
