package org.hbhk.aili.rpc.server.hessian.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.context.IDynamicBeanReader;
import org.hbhk.aili.core.server.properties.AiliPropertyPlaceholderConfigurer;
import org.hbhk.aili.core.share.entity.DynamicBean;
import org.hbhk.aili.core.share.entity.Property;
import org.hbhk.aili.core.share.util.ClassScanerUtil;
import org.hbhk.aili.rpc.server.hessian.IHessianRemoting;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

public class HessianDynamicBeanReader implements IDynamicBeanReader {
	
	private Log log = LogFactory.getLog(getClass());

	/**
	 * 多个以,逗号分隔
	 */
	private String basePackage;
	
	private final Class<?> hessianCls = HessianProxyFactoryBean.class;
	
	
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
				String host ="http://"+ AiliPropertyPlaceholderConfigurer.properties.getProperty("server-host");
				String port = AiliPropertyPlaceholderConfigurer.properties.getProperty("server-port");
				if(host.endsWith("/")){
					 host = host.substring(0, host.length()-1);
				}
				String context =AiliPropertyPlaceholderConfigurer.properties.getProperty("service-path");
				if(!context.endsWith("/")){
					context = context+"/";
				}
				if(!context.startsWith("/")){
					context = "/"+context;
				}
				String urlPrefix =  host +":"+port+context;
				for (Class cls : clsList) {
					if(cls.isInterface()){
						Class<?>[] clsArr =  cls.getInterfaces();
						if(clsArr==null || clsArr.length==0){
							continue;
						}
						boolean isIHessianRemoting = false;
						for (Class<?> clz : clsArr) {
							if(clz.equals(IHessianRemoting.class)){
								isIHessianRemoting = true;
							}
						}
						if(!isIHessianRemoting){
							continue;
						}
						DynamicBean db = new DynamicBean();
						
						List<Property> properties = new ArrayList<Property>();
						String clsName = cls.getName();
						log.debug("扫描hessian接口:"+clsName);
						String id = getSpringId(cls);
						db.setId(id);
						db.setCls(hessianCls);
						Property serviceUrl = new Property();
						serviceUrl.setName("serviceUrl");
						serviceUrl.setValue(urlPrefix+id);
						
						Property serviceInterface= new Property();
						serviceInterface.setName("serviceInterface");
						serviceInterface.setValue(clsName);
						
						db.setProperties(properties);;
						properties.add(serviceUrl);
						properties.add(serviceInterface);
						dynamicBeans.add(db);
					}
				}
			}
			
		} catch (Exception e) {
			log.error("扫描自定义bean出错", e);
		} 
		return dynamicBeans;
	}
	
	
	private String getSpringId(Class<?> cls){
		String pkgName = cls.getName();
   		String preFix = "";
		if(pkgName.indexOf(".server")>-1){
			String tmpStr = pkgName.substring(0,pkgName.indexOf(".server"));
       		preFix = tmpStr.substring(tmpStr.lastIndexOf(".")+1,tmpStr.length());
		}else{
			return preFix = computeDefaultServiceName(cls.getSimpleName(), null);		
		}
   		return computeDefaultServiceName(cls.getSimpleName(), preFix);
	}
	private String computeDefaultServiceName(String clsName,String moduleName) {
		String returnName = clsName.substring(1);
		if(StringUtils.isEmpty(moduleName)){
			return returnName.substring(0, 1).toLowerCase()
					.concat(returnName.substring(1));
		}
		
		return moduleName+"/"+(returnName.substring(0, 1).toLowerCase()
				.concat(returnName.substring(1)));
	}

	public String getBasePackage() {
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	

}
