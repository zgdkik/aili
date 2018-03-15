/*
 * PROJECT NAME: esb-runtime-server-foss
 * PACKAGE NAME: com.deppon.esb.server.foss.edi
 * FILE    NAME: Foss2EdiProcessor.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.esb.server.foss.edi;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;

import com.deppon.esb.inteface.domain.air.SumBillRequest;

/**
 * 
 * @author HuangHua
 * @date 2013-6-1 下午4:05:37
 */
public class Foss2EdiProcessor implements Processor {

	/** The Constant ESB_FOSS2ESB_EDI_FILEUP_SUMBILL. */
	private static final String ESB_FOSS2ESB_EDI_FILEUP_SUMBILL = "ESB_FOSS2ESB_EDI_FILEUP_SUMBILL";

	/** The sum bill service. */
	private SumBillService sumBillService;

	@Override
	public void process(Exchange exchange) throws Exception {
		// 此处可以根据服务编码做一些处理
		Map<String, Object> headers = exchange.getIn().getHeaders();
		// Object body = exchange.getIn().getBody();

		String esbServiceCode = (String) headers
				.get(ESBServiceConstant.ESB_SERVICE_CODE);

		if (ESB_FOSS2ESB_EDI_FILEUP_SUMBILL.equals(esbServiceCode)) {// 合票清单
																		// 单向调用
			String xml = exchange.getIn().getBody(String.class);
			SumBillRequest rq = SumBillConverter.xmlToObject(xml);
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_RESP_SENT, new Date().getTime());
			
			sumBillService.addSumbill(rq);
			
			exchange.getProperties().put(
					ESBServiceConstant.RT_REQUEST_OR_CALLBACK,
					ESBServiceConstant.RT_STOP);
		}
		
		exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_FINISHED, new Date().getTime());
		
		// 把header放在exchange的property里.
				// camel的header不是普通的HashMap，不能使用beanUtils
				Map<String, Object> headers1 = new HashMap<String, Object>();
				Map<String, Object> map = exchange.getIn().getHeaders();
				Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, Object> entry = iter.next();
					String key = entry.getKey();
					Object value = entry.getValue();
					headers1.put(key, value);
				}
		
		exchange.setProperty(ESBServiceConstant.RT_HEADERS, headers1);
		
	}

	public SumBillService getSumBillService() {
		return sumBillService;
	}

	public void setSumBillService(SumBillService sumBillService) {
		this.sumBillService = sumBillService;
	}
}
