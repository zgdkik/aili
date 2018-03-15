/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils.jms
 * FILE    NAME: TransformThread.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.route.jms.server.common.utils.jms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author HuangHua
 * @date 2013-4-16 下午2:45:09
 */
public class TransformThread extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransformThread.class);

	private BlockingQueue<EsbLogMessage> queueToTrans;
	private BlockingQueue<EsbLogMessage> queueToSend;

	private int size = 20;
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * 创建一个新的实例 TransformThread
	 * 
	 * @author HuangHua
	 * @date 2013-4-16 下午3:10:45
	 */
	public TransformThread(BlockingQueue<EsbLogMessage> queueToTrans, BlockingQueue<EsbLogMessage> queueToSend,
			int size, String threadName) {
		if (queueToSend == null || queueToTrans == null) {
			throw new IllegalArgumentException("parameter 'queueToTrans' and 'queueToSend' is required");
		}
		if (threadName == null) {
			this.setName("TRANSFORM-THREAD" + this.getId());
		} else {
			this.setName("TRANSFORM-" + threadName + "-" + this.getId());
		}
		this.queueToSend = queueToSend;
		this.queueToTrans = queueToTrans;
		if (size > 0) {
			this.size = size;
		}
		this.setDaemon(true);
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-4-16 下午2:47:50
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				EsbLogMessage esbMsg = queueToTrans.take();
				ArrayList<EsbLogMessage> messages = new ArrayList<EsbLogMessage>();
				queueToTrans.drainTo(messages, size - 1);
				messages.add(esbMsg);
				String body = object2String(messages);
				esbMsg.setHeader(null);// 此处不用重新new对象,减少创建对象的消耗.直接set值进去即可.
				esbMsg.setBody(body);
				queueToSend.add(esbMsg);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	private String object2String(ArrayList<EsbLogMessage> obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
