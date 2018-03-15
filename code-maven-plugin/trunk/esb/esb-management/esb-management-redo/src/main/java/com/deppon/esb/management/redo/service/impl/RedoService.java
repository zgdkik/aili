package com.deppon.esb.management.redo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import com.deppon.esb.management.common.constant.ESBConstants;
import com.deppon.esb.management.common.jms.JmsSender;
import com.deppon.esb.management.common.ws.DynamicWSClient;
import com.deppon.esb.management.excptn.dao.IExceptionDao;
import com.deppon.esb.management.excptn.domain.ExceptionInfo;
import com.deppon.esb.management.redo.service.IRedoService;

/**
 * 消息重发服务类(JMS/webservice消息重发)
 * @author qiancheng
 *
 */
@Transactional
@Service
public class RedoService implements IRedoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedoService.class);
	
	private IExceptionDao exceptionDao;
	private JmsSender jmsSender ;
	private DynamicWSClient dynamicWSClient; 
	@SuppressWarnings("static-access")
	@Override
	public void send() {
		//load数据
		List<ExceptionInfo> list = exceptionDao.queryRedo();
		if(list.size()==0){
			LOGGER.info("没有加载到数据，本次发送结束");
			return;
		}
		List<String> successList = new ArrayList<String>();
		List<String> idList = new ArrayList<String>();
		//重发
		for(ExceptionInfo info:list){
			LOGGER.info("处理异常：" + info.getId());
			if ( LOGGER.isDebugEnabled() ) {
				LOGGER.debug("\t" + info);
			}
			idList.add(info.getId());
			/**
			 *判断是否反序列化成功,失败则记录日志
			 */
			Serializable ser = null;
			try{
				ser = (Serializable)SerializationUtils.deserialize(info.getOrgnMsg());
			}
			catch(Exception e)
			{
				LOGGER.error("error occur when deserialize ,requestId is "+info.getRequestId());
				LOGGER.error(e.getMessage(), e);
			}
			try{
				if(ESBConstants.REDO_TYPE_JMS.equals(info.getRedoType()) ){
					LOGGER.info("重发类型为JMS，目标队列：" + info.getFromEndpointURI());
					
					jmsSender.sendJmsMessageWithProperty(info.getFromEndpointURI(), ser);
					
					LOGGER.info("成功重发JMS消息！");
					
					successList.add(info.getId());
				}
				else if(ESBConstants.REDO_TYPE_WEBSERVICE.equals(info.getRedoType())){
					LOGGER.info("重发类型为WEBSERVICE，目标地址：" + info.getFromEndpointURI());
					MessageContentsList requestList = (MessageContentsList)ser;
					Object[] result = dynamicWSClient.callWS(info.getFromEndpointURI(), info.getOperationNamespace(), info.getMethod(), requestList.toArray());
					//获取状态值
			        DirectFieldAccessor accessor = new DirectFieldAccessor(result[0]);
			        LOGGER.info("重发结果：" + accessor.getPropertyValue("status"));
			        
					if(ESBConstants.SUCCESS.equals(accessor.getPropertyValue("status"))){
						successList.add(info.getId());
					}
				}
			}
			catch(Exception e){
				LOGGER.error("error occur when redo ,requestId is "+info.getRequestId());
				LOGGER.error(e.getMessage(), e);
			}
		}
		//更新重发次数
		if(idList.size()>0){
			exceptionDao.updateRedoCount(idList);
		}
		//更新数据库状态为重发成功
		if(successList.size()>0){			
			exceptionDao.updateRedoSuccess(successList);
		}
	}
	public JmsSender getJmsSender() {
		return jmsSender;
	}
	public void setJmsSender(JmsSender jmsSender) {
		this.jmsSender = jmsSender;
	}
	public DynamicWSClient getDynamicWSClient() {
		return dynamicWSClient;
	}
	public void setDynamicWSClient(DynamicWSClient dynamicWSClient) {
		this.dynamicWSClient = dynamicWSClient;
	}
	public IExceptionDao getExceptionDao() {
		return exceptionDao;
	}
	public void setExceptionDao(IExceptionDao exceptionDao) {
		this.exceptionDao = exceptionDao;
	}
}
