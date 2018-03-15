package com.deppon.esb.management.svccfg.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.deppon.esb.management.common.exception.ESBConvertException;
import com.deppon.esb.management.svccfg.dao.IAgentUserDao;
import com.deppon.esb.management.svccfg.dao.IEsbConfigurationDao;
import com.deppon.esb.management.svccfg.dao.IUserInterfaceCountDao;
import com.deppon.esb.management.svccfg.dao.IUserInterfaceRelationDao;
import com.deppon.esb.management.svccfg.dao.extend.IExtendConfigurationDao;
import com.deppon.esb.management.svccfg.dao.impl.InterfaceCountDao;
import com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration;
import com.deppon.esb.management.svccfg.domain.EsbSvcPoint;
import com.deppon.esb.management.svccfg.domain.extend.ESBServiceConfiguration;
import com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration;
import com.deppon.esb.management.svccfg.domain.view.EsbRuntimeConfigAddrBean;
import com.deppon.esb.management.svccfg.service.IConfigurationService;
import com.deppon.esb.security.domain.InterfaceCount;
import com.deppon.esb.security.domain.UserInfo;
import com.deppon.esb.security.domain.UserInterfaceCount;
import com.deppon.esb.security.domain.UserInterfaceRelation;

/**
 * 配置信息服务类.
 * 
 * @author HuangHua
 * @date 2012-12-29 下午1:48:58
 */
@Service
public class ConfigurationService implements IConfigurationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationService.class);

	/** The configuration dao. */
	@Resource
	private IEsbConfigurationDao esbConfigurationDao;
	@Resource
	private IAgentUserDao agentUserDao;
	@Resource
	private IUserInterfaceRelationDao userInterfaceRelationDao;
	@Resource
	private IUserInterfaceCountDao userInterfaceCountDao;
	@Resource
	private InterfaceCountDao interfaceCountDao;
	@Resource
	private IExtendConfigurationDao extendConfigurationDao;
	
	@Override
	public ESBSystemConfiguration loadAllSystemChannel(String systemCode) {
		return extendConfigurationDao.loadAllSystemChannel(systemCode);
	}

	@Override
	public List<ESBServiceConfiguration> loadServiceConfiguration(String systemCode) {
		return extendConfigurationDao.loadServiceConfiguration(systemCode);
	}
	
	@Override
	public String loadAllSystemConfiguration(String systemCode) throws ESBConvertException {
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		if ("ESB".equals(systemCode)) {
			ESBRuntimeConfiguration[] runtimeCfgArray = loadESBRuntimeConfiguration();
			try {
				result = mapper.writeValueAsString(runtimeCfgArray);
			} catch (JsonGenerationException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (JsonMappingException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (IOException e) {
				throw new ESBConvertException(e.getMessage(),e);
			}
		}
		//判断ESBUSER
		else if("ESBUSER".equals(systemCode)){
			UserInfo[] userCfgArray = loadAgentUserConfiguration();
			try {
				result = mapper.writeValueAsString(userCfgArray);
			} catch (JsonGenerationException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (JsonMappingException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (IOException e) {
				throw new ESBConvertException(e.getMessage(),e);
			}
		}
		//判断ESB_USER_INTERFACE_RELATION
		else if("ESB_USER_INTERFACE_RELATION".equals(systemCode)){
			UserInterfaceRelation[] userInterfaceRelationArray = loadUserInterfaceRelation();
			try {
				result = mapper.writeValueAsString(userInterfaceRelationArray);
			} catch (JsonGenerationException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (JsonMappingException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (IOException e) {
				throw new ESBConvertException(e.getMessage(),e);
			}
		}
		//判断ESB_INTERFACE_COUNT_CONFIG
		else if("ESB_USER_INTERFACE_COUNT".equals(systemCode)){
			UserInterfaceCount[] userInterfaceCountnArray = loadUserInterfaceCount();
			try {
				result = mapper.writeValueAsString(userInterfaceCountnArray);
			} catch (JsonGenerationException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (JsonMappingException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (IOException e) {
				throw new ESBConvertException(e.getMessage(),e);
			}
		}
		//判断ESB_INTERFACE_COUNT_CONFIG
		else if("ESB_INTERFACE_COUNT".equals(systemCode)){
			InterfaceCount[] interfaceCountnArray = loadInterfaceCount();
			try {
				result = mapper.writeValueAsString(interfaceCountnArray);
			} catch (JsonGenerationException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (JsonMappingException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (IOException e) {
				throw new ESBConvertException(e.getMessage(),e);
			}
		}
		else {
			ESBSystemConfiguration cfg = loadAllSystemChannel(systemCode);
			if (cfg == null) {
				return null;
			}
			cfg.setUpdateTime(new Date());
			cfg.setSystemCode(systemCode);
			if("FOSS-CRM".equals(systemCode) || "FOSS-SMS".equals(systemCode)){
				systemCode = "FOSS";
			}
			List<ESBServiceConfiguration> serviceList = loadServiceConfiguration(systemCode);
			cfg.setServiceList(serviceList);
			try {
				result = mapper.writeValueAsString(cfg);
			} catch (JsonGenerationException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (JsonMappingException e) {
				throw new ESBConvertException(e.getMessage(),e);
			} catch (IOException e) {
				throw new ESBConvertException(e.getMessage(),e);
			}
		}
		LOGGER.debug("load config :" + result);
		return result;
	}
	
	
	

	/**
	 * 查询所有的ESB用户信息 
	 * @author k
	 * @return the ESB USER configuration[]
	 */
	public UserInfo[] loadAgentUserConfiguration() {
		List<UserInfo> list = agentUserDao.queryAllAgentUser();
		return list.toArray(new UserInfo[list.size()]);
	}

	
	/**
	 * 查询所有的用户接口绑定信息 
	 * @author k
	 * @return the ESB USER INTERFACE RELATION UserInterfaceRelation[]
	 */
	public UserInterfaceRelation[] loadUserInterfaceRelation() {
		List<UserInterfaceRelation> list = userInterfaceRelationDao.queryAllUserInterfaceRelation();
		return list.toArray(new UserInterfaceRelation[list.size()]);
	}
	
	/**
	 * 查询所有的用户接口绑定访问次数 
	 * @author k
	 * @return the ESB USER INTERFACE COUNT UserInterfaceCount[]
	 */
	public UserInterfaceCount[] loadUserInterfaceCount() {
		List<UserInterfaceCount> list = userInterfaceCountDao.queryAllUserInterfaceCount();
		return list.toArray(new UserInterfaceCount[list.size()]);
	}
	
	/**
	 * 查询接口总调用次数
	 * @author k
	 * @return the ESB INTERFACE COUNT InterfaceCount[]
	 */
	public InterfaceCount[] loadInterfaceCount() {
		List<InterfaceCount> list = interfaceCountDao.queryAllInterfaceCount();
		return list.toArray(new InterfaceCount[list.size()]);
	}
	
	
	/**
	 * 查询所有的服务配置信息，方便ESB运行时使用.
	 * 
	 * @return the ESB runtime configuration[]
	 * @author HuangHua
	 * @date 2012-12-29 下午1:49:03
	 * @see com.deppon.esb.management.svccfg.service.IConfigurationService#loadESBRuntimeConfiguration()
	 */
	@Override
	public ESBRuntimeConfiguration[] loadESBRuntimeConfiguration() {
		List<ESBRuntimeConfiguration> list = esbConfigurationDao.loadESBRuntimeConfiguration();
		return list.toArray(new ESBRuntimeConfiguration[list.size()]);
	}

	/* (non-Javadoc)
	 * @see com.deppon.esb.management.svccfg.service.IConfigurationService#selectBackendWsAddr()
	 */
	@Override
	public List<EsbRuntimeConfigAddrBean> selectBackendWsAddr() {
		return esbConfigurationDao.selectBackendWsAddr();
	}

	@Override
	public List<EsbSvcPoint> selectEsbSvcPoint() {
		return esbConfigurationDao.selectEsbSvcPoint();
	}

}
