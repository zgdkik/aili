package org.hbhk.aili.core.server.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.share.entity.DynamicBean;
import org.hbhk.aili.core.share.util.ClassScanerUtil;

public class DefaultDynamicBeanReader implements IDynamicBeanReader {
	
	private Log log = LogFactory.getLog(getClass());

	/**
	 * 多个以,逗号分隔
	 */
	private String basePackage;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<DynamicBean> loadBean() {
		List<DynamicBean> dynamicBeans = new ArrayList<DynamicBean>();
		try {
			Set<Class> clsList = new HashSet<Class>();
			if(StringUtils.isNotEmpty(basePackage)){
				String[] bps = basePackage.split(",");
				for (String str : bps) {
					log.debug("扫描包: "+str+" 开始");
					clsList.addAll(ClassScanerUtil.doScan(str));
					log.debug("扫描包: "+str+" 结束");
				}
			}
			if(clsList.size()>0){
				for (Class cls : clsList) {
					DynamicBean db = new DynamicBean();
					db.setId(cls.getSimpleName());
					db.setCls(cls);
					dynamicBeans.add(db);
				}
			}
			
		} catch (Exception e) {
			log.error("扫描自定义bean出错", e);
		} 
		return dynamicBeans;
	}
	
	
	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	

}
