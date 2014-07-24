package org.hbhk.aili.springmvc.server.web;

import org.hbhk.aili.core.share.pojo.ResponseEntity;

public abstract class BaseController {
	public ResponseEntity returnSuccess() {
		return new ResponseEntity();
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
