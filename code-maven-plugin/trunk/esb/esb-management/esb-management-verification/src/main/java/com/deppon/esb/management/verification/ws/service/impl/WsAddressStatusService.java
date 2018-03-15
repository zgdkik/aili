package com.deppon.esb.management.verification.ws.service.impl;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.verification.ws.service.IWsAddressStatusService;

public class WsAddressStatusService implements IWsAddressStatusService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WsAddressStatusService.class);

	@Override
	public String[] getStatus(String... urls) {
		String[] result = new String[urls.length];
		int index = 0;
		for (String url2Invoke : urls) {
			URLConnection conn;
			String response = null;
			try {
				URL url = new URL(url2Invoke);
				conn = url.openConnection();
				conn.setConnectTimeout(1000);
				conn.setReadTimeout(500);
				Map<String, List<String>> headers = conn.getHeaderFields();
				List<String> header = headers.get(null);
				response = header.get(0);
			} catch (Exception e) {
				if(LOGGER.isDebugEnabled()){
					LOGGER.warn(e.getMessage(), e);
				}
			}
			result[index++] = response;
		}
		return result;
	}
	public String getStatus(String address){
		URLConnection conn;
		String response = null;
		try {
			URL url = new URL(address);
			conn = url.openConnection();
			conn.setConnectTimeout(1000);
			conn.setReadTimeout(500);
			Map<String, List<String>> headers = conn.getHeaderFields();
			List<String> header = headers.get(null);
			response = header.get(0);
		} catch (Exception e) {
			if(LOGGER.isDebugEnabled()){
				LOGGER.warn(e.getMessage(), e);
			}
		}
		return response;
	}
}
