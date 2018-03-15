package com.deppon.esb.management.svccfg.rest.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.deppon.esb.management.svccfg.constant.ServiceConstant;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceConfig;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceConfigResult;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceRoute;
import com.deppon.esb.management.svccfg.domain.rest.ServiceAccessConfigEntity;
import com.deppon.esb.management.svccfg.rest.IServiceConfiguration;
import com.deppon.esb.management.svccfg.service.IServiceConfigurationService;

@Controller("serviceConfiguration")
@Scope("prototype") 
public class ServiceConfiguration implements IServiceConfiguration{

	@Context HttpServletRequest request;  
	@Context HttpServletResponse response;
	
	@Resource
	private IServiceConfigurationService serviceConfigurationService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfiguration.class);
	
	static{
		LOGGER.info("==================ServiceConfiguration==================");
	}
	
	
	@Override
	public String addServiceConfig(String json) throws JsonGenerationException, JsonMappingException, IOException  {
		LOGGER.info("........接收请求........" + json);
		ObjectMapper objectMapper = new ObjectMapper();
		ESBServiceConfigResult esbServiceConfigResult = new ESBServiceConfigResult();
		try {
			ServiceAccessConfigEntity svAccessConfig = 
					objectMapper.readValue(json, ServiceAccessConfigEntity.class);
			
			List<ESBServiceConfig> esbServiceConfigs = getESBServiceConfig(svAccessConfig);
			List<ESBServiceRoute> esbServiceRoutes = getESBServiceRoute(svAccessConfig);
			
			//LOGGER.info(objectMapper.writeValueAsString(esbServiceConfigs));
			//LOGGER.info(objectMapper.writeValueAsString(esbServiceRoutes));
			boolean flag = serviceConfigurationService.addServiceConfig(esbServiceConfigs, esbServiceRoutes);
			
			if(flag){
				esbServiceConfigResult.setResultCode(flag);
				esbServiceConfigResult.setResultMessage("ESB服务配置添加成功!");
				response.addHeader("ESB-ResultCode", "1");
			}else{
				esbServiceConfigResult.setResultCode(flag);
				esbServiceConfigResult.setResultMessage("ESB服务配置添加失败!");
				response.addHeader("ESB-ResultCode", "0");
			}
		} catch (JsonParseException e) {
			esbServiceConfigResult.setResultCode(false);
			esbServiceConfigResult.setResultMessage("请求json转换失败!");
			response.addHeader("ESB-ResultCode", "0");
			return objectMapper.writeValueAsString(esbServiceConfigResult);
		} catch (JsonMappingException e) {
			esbServiceConfigResult.setResultCode(false);
			esbServiceConfigResult.setResultMessage("请求json转换失败!");
			response.addHeader("ESB-ResultCode", "0");
			return objectMapper.writeValueAsString(esbServiceConfigResult);
		} catch (IOException e) {
			esbServiceConfigResult.setResultCode(false);
			esbServiceConfigResult.setResultMessage("json转换失败!");
			response.addHeader("ESB-ResultCode", "0");
			return objectMapper.writeValueAsString(esbServiceConfigResult);
		} catch (Exception e) {
			esbServiceConfigResult.setResultCode(false);
			esbServiceConfigResult.setResultMessage("未知异常!");
			response.addHeader("ESB-ResultCode", "0");
			return objectMapper.writeValueAsString(esbServiceConfigResult);
		}
		
		
		return objectMapper.writeValueAsString(esbServiceConfigResult);
	}
	
	/**
	 * 通过服务访问信息生成ESB的路由配置信息
	 * @return
	 * 		List<ESBServiceConfig>
	 */
	public List<ESBServiceRoute> getESBServiceRoute(ServiceAccessConfigEntity svAccessConfig){
		List<ESBServiceRoute> esbServiceRoutes = new ArrayList<ESBServiceRoute>();
		//创建前端路由配置
		ESBServiceRoute frontServiceRoute = getFrontServiceRoute(svAccessConfig);
		
		//创建后端路由配置
		ESBServiceRoute backServiceRoute = getBackServiceRoute(svAccessConfig);
		
		esbServiceRoutes.add(frontServiceRoute);
		esbServiceRoutes.add(backServiceRoute);
		
		return esbServiceRoutes;
	}
	
	/**
	 * 封装前端服务配置FrontServiceRoute
	 * @return
	 * 		ESBServiceRoute
	 */
	public ESBServiceRoute getFrontServiceRoute(ServiceAccessConfigEntity svAccessConfig){
		ESBServiceRoute esbServiceRoute = new ESBServiceRoute();
		esbServiceRoute.setEsbSvcCode(svAccessConfig.getEsbServiceCode());
		esbServiceRoute.setTargetSvcCode(svAccessConfig.getBackServiceCode());
		return esbServiceRoute;
	}
	
	/**
	 * 封装前端服务配置FrontServiceRoute
	 * @return
	 * 		ESBServiceRoute
	 */
	public ESBServiceRoute getBackServiceRoute(ServiceAccessConfigEntity svAccessConfig){
		ESBServiceRoute esbServiceRoute = new ESBServiceRoute();
		esbServiceRoute.setEsbSvcCode(svAccessConfig.getBackServiceCode());
		esbServiceRoute.setTargetSvcCode(svAccessConfig.getEsbServiceCode());
		return esbServiceRoute;
	}
	
	
	/**
	 * 通过服务访问信息生成ESB的服务配置信息
	 * @return
	 * 		List<ESBServiceConfig>
	 */
	public List<ESBServiceConfig> getESBServiceConfig(ServiceAccessConfigEntity svAccessConfig){
		List<ESBServiceConfig> esbServiceConfigs = new ArrayList<ESBServiceConfig>();
		
		if(svAccessConfig.getDistribute() != null && svAccessConfig.getDistribute() == ServiceConstant.Distribute_ESB_YES){
			//创建后端服务配置
			ESBServiceConfig backServiceConfig = getBackServiceConfig(svAccessConfig);
			
			esbServiceConfigs.add(backServiceConfig);
		}else{
			//创建前端服务配置
			ESBServiceConfig frontServiceConfig = getFrontServiceConfig(svAccessConfig);
			
			//创建后端服务配置
			ESBServiceConfig backServiceConfig = getBackServiceConfig(svAccessConfig);
			
			esbServiceConfigs.add(frontServiceConfig);
			esbServiceConfigs.add(backServiceConfig);
		}
		
		return esbServiceConfigs;
	}
	
	/**
	 * 封装前端服务配置FrontServiceConfig
	 * @return
	 * 		ESBServiceConfig
	 */
	public ESBServiceConfig getFrontServiceConfig(ServiceAccessConfigEntity svAccessConfig){
		ESBServiceConfig esbServiceConfig = new ESBServiceConfig();
		//设置服务名称
		esbServiceConfig.setName(svAccessConfig.getServiceName());
		//设置前端服务编码
		esbServiceConfig.setCode(svAccessConfig.getEsbServiceCode());
		//设置前端系统sysId
		esbServiceConfig.setSysId(svAccessConfig.getApplySysCode());
		//设置交互方式
		esbServiceConfig.setAgrmt(svAccessConfig.getServiceAgreementValue());
		//设置前后端标志
		esbServiceConfig.setFrntOrback("F");
		//设置交互模式
		esbServiceConfig.setExpattern(svAccessConfig.getServicePattern().intValue());
		
		//JMS需要设置esbResponseAddr
		if(svAccessConfig.getServiceAgreement() 
				== ServiceConstant.SERVICE_AGREEMENT_STATUS_JMS){
			//设置ESB发送响应队列
			esbServiceConfig.setEsbResponseAddr("RQ"+svAccessConfig.getClientReceiveQueue().substring(2));
		}
		
		//设置消息格式
		esbServiceConfig.setMessageFormat(svAccessConfig.getMessageFormatValue());
		//暂时默认60S
		esbServiceConfig.setTimeOut(60000);
		//设置是否记录审计日志,默认记录
		esbServiceConfig.setNeedAudit(1);
		//设置是否记录异常日志,默认记录
		esbServiceConfig.setNeedexpn(1);
		//设置是否记录状态日志,默认记录
		esbServiceConfig.setNeedStatus(1);
		
		return esbServiceConfig;
	}
	
	/**
	 * 封装后端服务配置BackServiceConfig
	 * @return
	 * 		ESBServiceConfig
	 */
	public ESBServiceConfig getBackServiceConfig(ServiceAccessConfigEntity svAccessConfig){
		ESBServiceConfig esbServiceConfig = new ESBServiceConfig();
		//设置服务名称
		esbServiceConfig.setName(svAccessConfig.getServiceName());
		//设置后端服务编码
		esbServiceConfig.setCode(svAccessConfig.getBackServiceCode());
		//设置后端系统sysId
		esbServiceConfig.setSysId(svAccessConfig.getSysCode());
		//设置交互方式
		esbServiceConfig.setAgrmt(svAccessConfig.getServiceAgreementValue());
		//设置前后端标志
		esbServiceConfig.setFrntOrback("B");
		//设置交互模式
		esbServiceConfig.setExpattern(svAccessConfig.getServicePattern().intValue());
				
		//JMS需要设置esbRequestAddr
		if(svAccessConfig.getServiceAgreement() 
				== ServiceConstant.SERVICE_AGREEMENT_STATUS_JMS){
			//设置ESB发送请求队列
			esbServiceConfig.setEsbRequestAddr("RQ"+svAccessConfig.getServiceReceiveQueue().substring(2));
		}else{
			//设置服务访问地址
			esbServiceConfig.setEsbRequestAddr(svAccessConfig.getServiceUrl());
		}
		//设置消息格式
		esbServiceConfig.setMessageFormat(svAccessConfig.getMessageFormatValue());
		//暂时默认60S
		esbServiceConfig.setTimeOut(60000);
		//设置是否记录审计日志,默认记录
		esbServiceConfig.setNeedAudit(1);
		//设置是否记录异常日志,默认记录
		esbServiceConfig.setNeedexpn(1);
		//设置是否记录状态日志,默认记录
		esbServiceConfig.setNeedStatus(1);
		
		return esbServiceConfig;
	}
	
}
