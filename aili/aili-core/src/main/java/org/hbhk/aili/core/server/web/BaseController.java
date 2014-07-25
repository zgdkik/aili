package org.hbhk.aili.core.server.web;

import org.hbhk.aili.core.share.pojo.ResponseEntity;

public abstract class BaseController {
	public ResponseEntity returnSuccess() {
		return new ResponseEntity();
	}

	public ResponseEntity returnSuccess(String msg, Object result,
			String dealUrl) {
		ResponseEntity response = new ResponseEntity();
		response.setMsg(msg);
		response.setResult(result);
		response.setDealUrl(dealUrl);
		return response;
	}

	public ResponseEntity returnSuccess(Object result) {
		ResponseEntity response = new ResponseEntity();
		response.setResult(result);
		return response;
	}

	public ResponseEntity returnSuccess(String msg) {
		ResponseEntity response = new ResponseEntity();
		response.setMsg(msg);
		return response;
	}

	public ResponseEntity returnException() {
		ResponseEntity response = new ResponseEntity();
		response.setException(true);
		response.setSuccess(false);
		return response;
	}

	public ResponseEntity returnException(String msg) {
		ResponseEntity response = new ResponseEntity();
		response.setException(true);
		response.setSuccess(false);
		response.setMsg(msg);
		return response;
	}
}
