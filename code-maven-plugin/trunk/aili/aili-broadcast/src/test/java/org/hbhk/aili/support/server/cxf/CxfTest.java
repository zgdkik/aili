package org.hbhk.aili.support.server.cxf;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;



import com.deppon.esb.header.ESBHeader;
import com.deppon.esb.inteface.domain.accounting.QueryAppWaybillInfosRequest;
import com.deppon.foss.accountservice.AccountService_Service;

public class CxfTest {

	public static void main(String[] args) throws Exception {
		AccountService_Service accountService = new AccountService_Service();
		ESBHeader esbHeader1 = new ESBHeader();
		Holder<ESBHeader> esbHeader = new Holder<ESBHeader>(esbHeader1);
		QueryAppWaybillInfosRequest request = new QueryAppWaybillInfosRequest();
		request.setMobilePhone("15821999948");
		request.setStatus("Y");
		XMLGregorianCalendar start =convertToXMLGregorianCalendar(new Date());
		request.setStartDate(start);
		XMLGregorianCalendar end =convertToXMLGregorianCalendar(new Date());
		request.setEndDate(end);
		request.setPageNum(1);
		request.setPageSize(10);
		request.setType("DELIVER");
		accountService.getAccountServiceSOAP().queryAppWaybillInfos(esbHeader, request);
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