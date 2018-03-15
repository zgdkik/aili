package org.hbhk.aili.route.http.processor;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Headers;
import org.hbhk.aili.route.common.exception.URLSupportException;
import org.hbhk.aili.route.http.support.HttpSupport;
import org.hbhk.aili.route.jms.common.constant.EsbRouteConstant;
import org.hbhk.aili.route.jms.common.constant.ExpressConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLProcesscor {

	private static final Logger LOGGER = LoggerFactory.getLogger(URLProcesscor.class);

	private List<HttpSupport> httpSupports;

	public List<HttpSupport> getHttpSupports() {
		return httpSupports;
	}

	public void setHttpSupports(List<HttpSupport> httpSupports) {
		this.httpSupports = httpSupports;
	}

	public void supportURL(@Headers Map<String, Object> headers) {
		String code = (String) headers.get(ExpressConstant.EXPRESS_ESB_SERVICE_CODE);
		String httpURL = (String) headers.get(EsbRouteConstant.HTTP_REMOTE_URL);
		if (support(code)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("request url [" + httpURL + "] is not ESB RestFul service!!");
			}
			//headers.put(EsbRouteConstant.IS_LOGGING, false);
			throw new URLSupportException("Problem accessing " + headers.get(Exchange.HTTP_URI)
					+ ". Reason: Not Found Service");
		}
	}

	private boolean support(String code) {
		if (httpSupports == null) {
			return true;
		}
		for (HttpSupport httpSupport : httpSupports) {
			if (!httpSupport.support(code) || code == null || "".equals(code)) {
				return true;
			}
		}
		return false;
	}
}
