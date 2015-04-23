package org.hbhk.aili.report.server.creater;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import org.hbhk.aili.report.server.print.ReportPrint;
import org.hbhk.aili.report.share.ex.ReportException;

public class ReportCreater {
	private static final Log logger = LogFactory.getLog(ReportCreater.class);
    /**
     * jasperDesignMap作为一个缓存来存储编译后的JasperReport模板
     */
    private Map<String, JasperReport> jasperDesignMap = new ConcurrentHashMap<String, JasperReport>();
     
    public void resetJasperDesignCache() {
        jasperDesignMap.clear();
    }
     
    /**
     * controller调用该方法来产生ReportPrint对象
     */
    public ReportPrint createReport(String reportKey, List<Object> datas, Map<String, Object> reportParams,String jasperReportPath) throws ReportException {
        try {
        	JasperReport jasperReport = getJasperReport(reportKey,jasperReportPath);     
            ReportPrint reportPrint = new ReportPrint();
            JRBeanCollectionDataSource javaBeanDataSource = new JRBeanCollectionDataSource(datas);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, javaBeanDataSource);
            reportPrint.setJasperPrint(jasperPrint);
            return reportPrint;
        } catch (JRException e) {
            logger.error(null, e);
            throw new ReportException("产生报表出错" + reportKey);
        }
    }
     
    private JasperReport getJasperReport(String reportKey,String jasperReportPath) {
        try {
        	JasperReport jasperReport = null;
            if (jasperDesignMap.containsKey(reportKey)) {
                jasperReport = jasperDesignMap.get(reportKey);
            } else {
                jasperReport = getJasperReportFromFile(reportKey,jasperReportPath);
                jasperDesignMap.put(reportKey, jasperReport);
            }
            return jasperReport;
        } catch (IOException e) {
            logger.error(null, e);
            throw new ReportException("关闭文件流异常:" + reportKey);
        } catch (JRException e) {
            logger.error(null, e);
            throw new ReportException("产生报表异常:" + reportKey);
        }
    }
     
    /**
     * 从模板文件编译获得模板对象
     */
    private JasperReport getJasperReportFromFile(String reportKey,String jasperReportPath)  throws IOException, JRException {
    	//图省事只支持jrxml的
        String filePath = jasperReportPath + reportKey + ".jrxml";
        InputStream jasperFileIS = null;
        JasperReport jasperReport = null;
         
        try {
            jasperFileIS = this.getClass().getClassLoader().getResourceAsStream(filePath);
            if (jasperFileIS == null) {
                throw new ReportException("报表文件不存在:" + filePath);
            }
             
            JasperDesign jasperDesign = JRXmlLoader.load(jasperFileIS);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
        } finally {
            if (jasperFileIS != null) {
                jasperFileIS.close();
            }
        }
         
        return jasperReport;
    }
     
}
