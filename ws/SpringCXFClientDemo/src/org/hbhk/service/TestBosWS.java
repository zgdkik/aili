package org.hbhk.service;

import java.rmi.RemoteException;
import java.util.Date;

import javax.jws.WebService;

@WebService(targetNamespace = "http://schemas.xmlsoap.org/soap/encoding/")
public interface TestBosWS {
	public String[] returnBankPayRefund(String batchNumber, String refundType,
			Date refundExportDate, double totalAmount, long totalVotes,
			String[] refundInfo) throws RemoteException;
}
