package com.deppon.esb.management.web.action.verification;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.common.exception.ESBParamException;
import com.deppon.esb.management.svccfg.domain.view.EsbRuntimeConfigAddrBean;
import com.deppon.esb.management.svccfg.service.IConfigurationService;
import com.deppon.esb.management.verification.ws.service.IWsAddressStatusService;

@Controller("wsAddressStatusAction")
@Scope("prototype")
public class WsAddressStatusAction extends DefaultActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private IWsAddressStatusService wsAddressStatusService;
	
	@Resource
	private IConfigurationService configurationService;
	
	private List<EsbRuntimeConfigAddrBean> addrStatusList;
	private String url;
	private String urlStatus;
	private String message;
	
	private final static String suffix="?wsdl";
	
	public String queryWsAddressStatus() {
		List<EsbRuntimeConfigAddrBean> beans = configurationService.selectBackendWsAddr();
		EsbRuntimeConfigAddrBean[] result = new EsbRuntimeConfigAddrBean[beans.size()];
		String[] urls = new String[beans.size()];
		int index = 0;
		for (EsbRuntimeConfigAddrBean esbRuntimeConfigAddrBean : beans) {
			int i = index++;
			LOG.info(i+"");
			urls[i]=esbRuntimeConfigAddrBean.getServiceAddr();
			result[i] = esbRuntimeConfigAddrBean;
			result[i].setStatus("未检测");
		}
		/*
		String[] rsl = wsAddressStatusService.getStatus(urls);
		for (int i = 0; i < result.length; i++) {
			EsbRuntimeConfigAddrBean esbRuntimeConfigAddrBean = result[i];
			esbRuntimeConfigAddrBean.setStatus("-1");
		}*/
		addrStatusList = Arrays.asList(result);
		return SUCCESS;
	}

	public String querySingleWsAddressStatus(){
		try {
			//地址判断
			if(url ==null ||url.length()==0){
				throw new ESBParamException("参数不正确"); 
			}
			//如果地址没有添加后缀?wsdl，则添加后缀
			String lowerUrl = url.toLowerCase();
			if(!lowerUrl.equals(suffix)){
				url= url+suffix;
			}
			urlStatus = wsAddressStatusService.getStatus(url);
			if(urlStatus == null||urlStatus.length()==0){
				urlStatus="无法连接服务器";
			}
			return SUCCESS;
		} catch (Exception e) {
			message= e.getMessage();
			LOG.error(e.getMessage(), e);
			return ERROR;
		}
	}
	
	
	public List<EsbRuntimeConfigAddrBean> getAddrStatusList() {
		return addrStatusList;
	}

	public void setAddrStatusList(List<EsbRuntimeConfigAddrBean> addrStatusList) {
		this.addrStatusList = addrStatusList;
	}

	public IWsAddressStatusService getWsAddressStatusService() {
		return wsAddressStatusService;
	}

	public void setWsAddressStatusService(
			IWsAddressStatusService wsAddressStatusService) {
		this.wsAddressStatusService = wsAddressStatusService;
	}

	public IConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlStatus() {
		return urlStatus;
	}

	public void setUrlStatus(String urlStatus) {
		this.urlStatus = urlStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String getSuffix() {
		return suffix;
	}

	
}
