package com.deppon.esb.management.job;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.job.cluster.support.EsbJob;
import com.deppon.esb.management.redo.service.IRedoService;

/**
 * 系统重发消息
 * 
 * @author qiancheng
 * 
 */
@Repository
public class RedoJob implements EsbJob {
	private static final Log log = LogFactory.getLog(RedoJob.class);
	private static final long serialVersionUID = -2674507854488295297L;
	@Resource(name = "redoService")
	private IRedoService redoService;

	public IRedoService getRedoService() {
		return redoService;
	}

	public void setRedoService(IRedoService redoService) {
		this.redoService = redoService;
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-5-3 下午8:10:25
	 * @see com.deppon.esb.management.job.cluster.support.EsbJob#executeInternal()
	 */
	@Override
	public void executeInternal() {
		try {
			log.debug(Thread.currentThread().getName() + " RedoJob begin:"
					+ new Date());
			redoService.send();
			log.debug(Thread.currentThread().getName() + " RedoJob end:"
					+ new Date());
		} catch (Exception e) {
			log.error("RedoJob fail", e);
		}
	}
}
