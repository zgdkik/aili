package org.hbhk.aili.support.server.cxf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.esb.header.ESBHeader;
import com.deppon.esb.inteface.domain.accounting.AppWayBillDetailInfo;
import com.deppon.esb.inteface.domain.accounting.QueryAppWaybillInfosRequest;
import com.deppon.esb.inteface.domain.accounting.QueryAppWaybillInfosResponse;
import com.deppon.foss.accountservice.AccountService;
import com.deppon.foss.accountservice.CommonException;

public class SpringWsClient {

	public static void main(String[] args) throws CommonException, ParseException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"client-beans.xml");
		AccountService service = ctx.getBean("userWsClient",
				AccountService.class);
		ESBHeader esbHeader1 = new ESBHeader();
		esbHeader1.setEsbServiceCode("ESB_DPAPP2ESB_FOSS_RECEIVED_LIST");
		esbHeader1.setMessageFormat("SOAP");
		esbHeader1.setExchangePattern(3);
		esbHeader1.setRequestId("1");
		esbHeader1.setBusinessId("1");
		esbHeader1.setVersion("1");
		esbHeader1.setSourceSystem("hbhk");
		Holder<ESBHeader> esbHeader = new Holder<ESBHeader>(esbHeader1);
		QueryAppWaybillInfosRequest request = new QueryAppWaybillInfosRequest();
		request.setMobilePhone("18688558808");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = sdf.parse("2015-10-14 15:23:00") ;
		Date endTime = sdf.parse("2015-11-03 15:23:00") ;
		XMLGregorianCalendar start =convertToXMLGregorianCalendar(startTime);
		request.setStartDate(start);
		XMLGregorianCalendar end =convertToXMLGregorianCalendar(endTime);
		request.setEndDate(end);
		request.setPageNum(1);
		request.setPageSize(5);
		request.setType("DELIVER");
		QueryAppWaybillInfosResponse  response= service.queryAppWaybillInfos(esbHeader, request);
		List<AppWayBillDetailInfo>  reInfos = response.getWayBillList();
		System.out.println(reInfos);
	}
	private static  XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		if (date != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			XMLGregorianCalendar gc = null;
			try {
				gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			} catch (Exception e) {
			}
			return gc;
		} else {
			return null;
		}
	}
}
