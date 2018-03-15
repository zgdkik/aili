package com.deppon.esb.management.svccfg.service;

import java.util.List;

import com.deppon.esb.management.common.exception.ESBConvertException;
import com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration;
import com.deppon.esb.management.svccfg.domain.EsbSvcPoint;
import com.deppon.esb.management.svccfg.domain.extend.ESBServiceConfiguration;
import com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration;
import com.deppon.esb.management.svccfg.domain.view.EsbRuntimeConfigAddrBean;
import com.deppon.esb.security.domain.InterfaceCount;
import com.deppon.esb.security.domain.UserInfo;
import com.deppon.esb.security.domain.UserInterfaceCount;
import com.deppon.esb.security.domain.UserInterfaceRelation;

/**
 * ESB配置信息服务类
 * 
 * @author HuangHua
 * 
 */
public interface IConfigurationService {
	
	/**
	 * 加载系统的所有通道信息,供扩展用.
	 * 
	 * @param systemCode
	 *            the system code
	 * @return the ESB system configuration
	 */
	public ESBSystemConfiguration loadAllSystemChannel(String systemCode);

	/**
	 * 加载系统的所有服务配置信息,供扩展用.
	 * 
	 * @param systemCode
	 *            the system code
	 * @return the list< esb service configuration>
	 */
	public List<ESBServiceConfiguration> loadServiceConfiguration(String systemCode);
	

	/**
	 * 加载系统的所有配置信息，包括系统通道信息和服务配置信息.
	 * 
	 * @param systemCode
	 *            the system code
	 * @return the string
	 * @throws ESBConvertException
	 *             the ESB convert exception
	 */
	public String loadAllSystemConfiguration(String systemCode) throws ESBConvertException;

	/**
	 * 查询所有的服务配置信息，方便ESB运行时使用
	 * 
	 * @return List<ESBRuntimeConfiguration>
	 */
	public ESBRuntimeConfiguration[] loadESBRuntimeConfiguration();
	
	/**
	 * 查询所有后端服务编码和地址
	 * 
	 * @return List<EsbRuntimeConfigAddrBean>
	 */
	public List<EsbRuntimeConfigAddrBean> selectBackendWsAddr();
	
	/**
	 * 查询所有的ESB用户信息 
	 * @author k
	 * @return the ESB USER configuration[]
	 */
	public UserInfo[] loadAgentUserConfiguration() ;
	
	/**
	 * 查询所有用户与接口绑定信息
	 * 
	 * @return UserInterfaceRelation[]
	 */
	public UserInterfaceRelation[] loadUserInterfaceRelation();
	
	/**
	 * 查询所有用户与接口绑定调用次数
	 * 
	 * @return UserInterfaceCount[]
	 */
	public UserInterfaceCount[] loadUserInterfaceCount();
	
	/**
	 * 查询接口总调用次数
	 * 
	 * @return InterfaceCount[]
	 */
	public InterfaceCount[] loadInterfaceCount();
	
	
	public List<EsbSvcPoint> selectEsbSvcPoint();
	
}
