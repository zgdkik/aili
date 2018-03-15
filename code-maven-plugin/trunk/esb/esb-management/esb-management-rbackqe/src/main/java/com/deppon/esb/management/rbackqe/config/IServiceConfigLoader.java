package com.deppon.esb.management.rbackqe.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.deppon.esb.management.svccfg.domain.EsbSvcPoint;


/**
 * 服务配置信息接口类.
 * 
 * @author HuangHua
 * @date 2012-12-25 下午4:26:50
 */
public interface IServiceConfigLoader extends InitializingBean{

	/**
	 * 获取所有的配置信息.
	 * 
	 * @return the all
	 * @author HuangHua
	 * @date 2012-12-25 下午4:26:59
	 */
	Map<String, List<EsbSvcPoint>> getAll();


	/**
	 * 通过服务编码获取配置信息.
	 * 
	 * @param code
	 *            the code
	 * @return the list
	 * @author HuangHua
	 * @date 2012-12-25 下午4:27:08
	 */
	List<EsbSvcPoint> get(String code);

	void refresh();

}
