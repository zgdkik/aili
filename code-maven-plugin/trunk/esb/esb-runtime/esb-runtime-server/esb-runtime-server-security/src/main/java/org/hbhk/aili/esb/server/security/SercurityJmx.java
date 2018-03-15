package org.hbhk.aili.esb.server.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hbhk.aili.esb.server.security.authorize.IUserConfigLoader;
import org.hbhk.aili.esb.server.security.authorize.IUserInterfaceRelationLoader;
import org.hbhk.aili.esb.server.security.domain.UserInfo;
import org.hbhk.aili.esb.server.security.domain.UserInterfaceRelation;
import org.hbhk.aili.esb.server.security.flowcontrol.container.impl.EsbInterfaceConcurrentContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.container.impl.EsbInterfaceCountContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.container.impl.EsbUserInterfaceConcurrentContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.container.impl.EsbUserInterfaceCountContainer;
import org.hbhk.aili.esb.server.security.flowcontrol.counter.ICounter;

/**
 * 用于Jmx刷新配置信息以及查看各种配置信息；
 */
public class SercurityJmx {
	private IUserConfigLoader userConfigLoader;
	private IUserInterfaceRelationLoader userInterfaceConfigLoader;
	private EsbInterfaceCountContainer esbInterfaceCountContainer;
	private EsbUserInterfaceCountContainer esbUserInterfaceCountContainer;
	private EsbInterfaceConcurrentContainer esbInterfaceConcurrentContainer;
	private EsbUserInterfaceConcurrentContainer esbUserInterfaceConcurrentContainer;
	private ObjectMapper mapper = new ObjectMapper();
	private static Logger LOG = Logger.getLogger(SercurityJmx.class);
	
	//刷并发接口并发控制信息
	public synchronized void refresh_Itf_concurrent(){
		esbInterfaceConcurrentContainer.init();
	}
	//查看并发接口并发控制信息
	public String print_Itf_concurrent(){
		List<ICounter> list =  esbInterfaceConcurrentContainer.traversal();
		return java2json(list);
	}
	
	//刷用户接口并发控制信息

	public synchronized void refresh_User_Itf_concurrent(){
		esbUserInterfaceConcurrentContainer.init();
	}
	
	//查看用户接口并发信息
	public String print_User_Itf_concurrent(){
		List<ICounter> list =  esbUserInterfaceConcurrentContainer.traversal();
		return java2json(list);
	}
	
	//=====================
	/**
	 * 刷新用户配置信息
	 */
	public synchronized void refreshUserConfigLoader(){
		userConfigLoader.refresh();
	}
	
	/**
	 * 查看用户配置信息
	 */
	public String printUserConfig(String user){
		if(user==null){
			return "请输入用户";
		}
		UserInfo info  = userConfigLoader.getUser(user);
		if(info == null){
			return "找不用户名为："+user+"的信息";
		}
		return java2json(info);
	}

	/**
	 * 查看所有用户配置信息
	 */
	public String print_All_UserConfig(){
			Collection<UserInfo> collection = userConfigLoader.getAll().values();
			Iterator<UserInfo> iterator = collection.iterator();
			List<UserInfo> list = new ArrayList<UserInfo>();
			while (iterator.hasNext()) {
				UserInfo info = iterator.next();
				list.add(info);
			}
			return java2json(list);
	}
	
	//=====================
	/**
	 * 刷新用户接口关联信息
	 */
	public synchronized void refreshUser_Itf(){
		userInterfaceConfigLoader.refresh();
	}

	/**
	 * 查看用户接口关联信息
	 */
	public String print_User_Itf(String user,String esbServiceCode){
			UserInterfaceRelation relation = userInterfaceConfigLoader.getRelation(user, esbServiceCode);
			return java2json(relation);
	}
	/**
	 * 查看所有用户接口关联信息
	 */
	public String print_All_User_Itf(){
			Collection<UserInterfaceRelation> collection = userInterfaceConfigLoader.getAll().values();
			Iterator<UserInterfaceRelation> iterator = collection.iterator();
			List<UserInterfaceRelation> list = new ArrayList<UserInterfaceRelation>();
			while (iterator.hasNext()) {
				UserInterfaceRelation relation = iterator.next();
				list.add(relation);
			}
			return java2json(list);
	}
	

	//=====================
	/**
	 *刷新接口计数器配置
	 */
	public synchronized void refresh_Itf_Limit(){
		esbInterfaceCountContainer.init();
	}

	/**
	 * 查看接口计数器的值
	 */
	public String print_Itf_Limit(String key){
		ICounter counter = esbInterfaceCountContainer.get(key);
		if(counter ==null){
			return "未找到计数器";
		}
		return counter.toString();
	}
	/**
	 * 查询所有接口计数器信息
	 * @return
	 */
	public String print_All_Itf_Limit(){
		List<ICounter> list = esbInterfaceCountContainer.traversal();
		StringBuffer buffer = new StringBuffer();
		for(ICounter counter:list){
			buffer.append(counter.toString());
		}
		return buffer.toString();
	}
	
	
	//=====================
	/**
	 * 刷新用户接口计数器配置
	 */
	public synchronized void refresh_User_Itf_Limit(){
		esbUserInterfaceCountContainer.init();
	}
	/**
	 * 查看用户接口计数器信息
	 */
	public String print_User_Itf_Counter(String key){
		if(key ==null ||"".equals(key)){
			return "请输入key:用户名+接口编码";
		}
		ICounter counter = esbUserInterfaceCountContainer.get(key);
		if(counter ==null){
			return "未找到key:"+key+"对应的计数器";
		}
		return counter.toString();
	}
	/**
	 * 查看所有用户接口计数器信息
	 * @return
	 */
	public String print_ALL_User_Itf_Limit(){
		List<ICounter> list = esbUserInterfaceCountContainer.traversal();
		StringBuffer buffer = new StringBuffer();
		for(ICounter counter:list){
			buffer.append(counter.toString());
		}
		return buffer.toString();
	}	

	public String java2json(Object obj){
		if(null == obj){
			return null;
		}
		String msg = null;
		try {
			msg = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			LOG.error(e);
		} catch (JsonMappingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
		return msg;
	}
	public IUserConfigLoader getUserConfigLoader() {
		return userConfigLoader;
	}
	public void setUserConfigLoader(IUserConfigLoader userConfigLoader) {
		this.userConfigLoader = userConfigLoader;
	}
	public IUserInterfaceRelationLoader getUserInterfaceConfigLoader() {
		return userInterfaceConfigLoader;
	}
	public void setUserInterfaceConfigLoader(
			IUserInterfaceRelationLoader userInterfaceConfigLoader) {
		this.userInterfaceConfigLoader = userInterfaceConfigLoader;
	}

	public EsbInterfaceCountContainer getEsbInterfaceCountContainer() {
		return esbInterfaceCountContainer;
	}

	public void setEsbInterfaceCountContainer(
			EsbInterfaceCountContainer esbInterfaceCountContainer) {
		this.esbInterfaceCountContainer = esbInterfaceCountContainer;
	}

	public EsbUserInterfaceCountContainer getEsbUserInterfaceCountContainer() {
		return esbUserInterfaceCountContainer;
	}

	public void setEsbUserInterfaceCountContainer(
			EsbUserInterfaceCountContainer esbUserInterfaceCountContainer) {
		this.esbUserInterfaceCountContainer = esbUserInterfaceCountContainer;
	}

	public EsbInterfaceConcurrentContainer getEsbInterfaceConcurrentContainer() {
		return esbInterfaceConcurrentContainer;
	}

	public void setEsbInterfaceConcurrentContainer(
			EsbInterfaceConcurrentContainer esbInterfaceConcurrentContainer) {
		this.esbInterfaceConcurrentContainer = esbInterfaceConcurrentContainer;
	}

	public EsbUserInterfaceConcurrentContainer getEsbUserInterfaceConcurrentContainer() {
		return esbUserInterfaceConcurrentContainer;
	}

	public void setEsbUserInterfaceConcurrentContainer(
			EsbUserInterfaceConcurrentContainer esbUserInterfaceConcurrentContainer) {
		this.esbUserInterfaceConcurrentContainer = esbUserInterfaceConcurrentContainer;
	}
	
}
