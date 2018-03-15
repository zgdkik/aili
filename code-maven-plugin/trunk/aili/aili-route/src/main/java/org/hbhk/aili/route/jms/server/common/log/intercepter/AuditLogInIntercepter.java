package org.hbhk.aili.route.jms.server.common.log.intercepter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 审计日志拦截器，用于拦截webservice请求，获取审计报文 
 */
public class AuditLogInIntercepter extends AbstractPhaseInterceptor<Message> {
	/** logger. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuditLogInIntercepter.class);

	/**
	 * Instantiates a new audit log in intercepter.
	 */
	public AuditLogInIntercepter() {
		super(Phase.RECEIVE);
	}

	/**
	 * 重写handlerMessage ，获取审计报文
	 * 
	 * @param message
	 *            the message
	 * @author qiancheng
	 * @date 2012-12-20 下午4:49:06
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	public void handleMessage(Message message) {
		ByteArrayOutputStream bos = null;
		try {
			InputStream ios = message.getContent(InputStream.class);
			if(ios != null){				
				bos = new ByteArrayOutputStream();
				IOUtils.copy(ios, bos);
				bos.flush();
				// 读取IO流后切记将流写回message里。
				message.setContent(InputStream.class,
						new ByteArrayInputStream(bos.toByteArray()));
				String soapMsg = IOUtils.toString(new ByteArrayInputStream(bos
						.toByteArray()));
				LOGGER.debug("soap:" + soapMsg);
				//当在浏览器访问wsdl地址时，也会触发这个拦截器,这个时候soap报文为空,故需要进行空值判断
				if (StringUtils.isNotEmpty(soapMsg)) {
					message.put(ESBServiceConstant.REQUEST_SOAP_BODY, soapMsg);
				}
			}else{
				LOGGER.info("client invoke error because of inputStream is null on com.deppon.esb.server.common.log.intercepter.AuditLogInIntercepter.handleMessage");
			}
		} catch (Exception e) {
			LOGGER.error("error occur when process audit log,"+e.getMessage(),e);
		} finally {
			try {
				if(bos != null){
					bos.close();
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
}
