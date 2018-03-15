package org.hbhk.aili.esb.server.foss.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.processor.SpecificationsProcess;

/**
 * FOSS处理类.
 */
public class FossProcess extends SpecificationsProcess {

	/** The Constant ESB_FOSS2ESB_VOICEMESSAGE. */
	private static final String ESB_FOSS2ESB_VOICEMESSAGE = "ESB_FOSS2ESB_VOICEMESSAGE";
	
	/** The Constant ESB_FOSS2ESB_EDI_FILEUP_SUMBILL. */
	private static final String ESB_FOSS2ESB_EDI_FILEUP_SUMBILL = "ESB_FOSS2ESB_EDI_FILEUP_SUMBILL";
	
	private static final String SMS_TEMP_QUEUE = "QU_FOSS_REQUEST_SMS_OUT";
	private static final String EDI_TEMP_QUEUE = "QU_FOSS_REQUEST_EDI_OUT";
	
	/**
	 * 针对特殊的服务进行特殊处理.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-25 下午3:26:07
	 * @see org.hbhk.aili.esb.server.common.processor.SpecificationsProcess#processWithStatus(org.apache.camel.Exchange)
	 */
	@Override
	public void processWithStatus(Exchange exchange) throws Exception {
		// 此处可以根据服务编码做一些处理
		Map<String, Object> headers = exchange.getIn().getHeaders();
		// Object body = exchange.getIn().getBody();

		String esbServiceCode = (String) headers
				.get(ESBServiceConstant.ESB_SERVICE_CODE);
		
		if (ESB_FOSS2ESB_VOICEMESSAGE.equals(esbServiceCode)) {//短信语音
			String endpoint = getConfigLoader().getSvcPointInfo(esbServiceCode).getJmsComponent() + ":" + SMS_TEMP_QUEUE + "?disableReplyTo=true";
			exchange.setProperty("DPESB_FOSS_SYNC_ADDRESS_SMS", endpoint);
			exchange.getProperties().put(ESBServiceConstant.RT_REQUEST_OR_CALLBACK,ESBServiceConstant.RT_STOP);
		}else if (ESB_FOSS2ESB_EDI_FILEUP_SUMBILL.equals(esbServiceCode)) {// 合票清单 单向调用
			String endpoint = getConfigLoader().getSvcPointInfo(esbServiceCode).getJmsComponent() + ":" + EDI_TEMP_QUEUE + "?disableReplyTo=true";
			exchange.setProperty("DPESB_FOSS_SYNC_ADDRESS_SMS", endpoint);
			exchange.getProperties().put(ESBServiceConstant.RT_REQUEST_OR_CALLBACK,ESBServiceConstant.RT_STOP);
		}
		
	}


}
